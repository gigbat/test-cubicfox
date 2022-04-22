package com.cubicfox.util;

import com.cubicfox.client.impl.UserIntegrationServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.http.HTTPException;
import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class JsonConverterUtil {
    private final Logger LOG = LoggerFactory.getLogger(UserIntegrationServiceImpl.class);
    private final ObjectMapper MAPPER = new ObjectMapper();

    public <T> List<T> convertToPojo(Class<T> clazz, String url) {
        String json = getUsersJsonAsString(url);
        try {
            Object[] objects = MAPPER.readValue(json, Object[].class);
            return Arrays.stream(objects).map(e -> {
                try {
                    String jsonOfObject = MAPPER.writeValueAsString(e);
                    return MAPPER.readValue(jsonOfObject, clazz);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            }).collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            LOG.error("Can't convert the JSON string to an object.");
            throw new RuntimeException(e);
        }
    }

    private String getUsersJsonAsString(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(
                Objects.requireNonNull(url),
                HttpMethod.GET, entity, String.class);
        return getResponseBody(response);
    }

    private String getResponseBody(ResponseEntity<String> response) {
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.value() == HttpStatus.OK.value()) {
            LOG.info("The request has been completed successfully.");
            LOG.info("Status code is " + statusCode.value());
            return response.getBody();
        } else if (statusCode.is3xxRedirection()) {
            LOG.warn("Something wrong with request. Please, double check your sending content");
            LOG.warn("Status code is " + statusCode.value());
            return null;
        } else if (statusCode.is4xxClientError()) {
            LOG.error("The page can't be found on the client side");
            LOG.error("Status code is " + statusCode.value());
            throw new HTTPException(statusCode.value());
        } else {
            LOG.error("The server doesn't work as expected");
            LOG.error("Status code is " + statusCode.value());
            throw new HTTPException(statusCode.value());
        }
    }
}
