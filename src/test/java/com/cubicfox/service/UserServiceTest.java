package com.cubicfox.service;

import com.cubicfox.client.api.UserIntegrationService;
import com.cubicfox.model.Address;
import com.cubicfox.model.Company;
import com.cubicfox.model.Geo;
import com.cubicfox.model.User;
import com.cubicfox.service.api.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    @Autowired
    private UserIntegrationService userIntegrationService;
    @Autowired
    private UserService userService;

    @AfterAll
    public void clearAll() {
        userService.clearAll();
    }

    @Test
    public void compareJsonAfterReadingTest() {
        List<User> users = userIntegrationService.getUsers();
        List<com.cubicfox.dto.User> dtoUsers = getDtoUsers(users);
        ObjectMapper mapper = new ObjectMapper();
        byte[] actual = getDtoActualContent(dtoUsers, mapper);
        File expected = getExpectedContent("./tests/expected-dto.json");
        try {
            JsonNode jsonNodeActual = mapper.readTree(actual);
            JsonNode jsonNodeExpected = mapper.readTree(expected);
            Assertions.assertEquals(jsonNodeExpected, jsonNodeActual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void compareJsonAfterSavingTest() {
        List<User> integrationUsers = userIntegrationService.getUsers();
        try {
            List<User> users = userService.saveUsers(integrationUsers);
            ObjectMapper mapper = new ObjectMapper();
            byte[] actual = getActualContent(users, mapper);
            File expected = getExpectedContent("./tests/expected-database.json");
            JsonNode jsonNodeActual = mapper.readTree(actual);
            JsonNode jsonNodeExpected = mapper.readTree(expected);
            Assertions.assertEquals(jsonNodeExpected, jsonNodeActual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getActualContent(List<User> users, ObjectMapper mapper) {
        try {
            String value = mapper.writeValueAsString(users);
            return value.getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getDtoActualContent(List<com.cubicfox.dto.User> users, ObjectMapper mapper) {
        try {
            String value = mapper.writeValueAsString(users);
            return value.getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private File getExpectedContent(String url) {
        URL resource = Thread.currentThread()
                .getContextClassLoader().getResource(url);
        try {
            return new File(Objects.requireNonNull(resource).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private List<com.cubicfox.dto.User> getDtoUsers(List<User> savedUsers) {
        return savedUsers.stream().map(this::getDtoUser).collect(Collectors.toList());
    }

    private com.cubicfox.dto.User getDtoUser(User user) {
        return new com.cubicfox.dto.User(user.getName(), user.getUsername(),
                user.getEmail(), getDtoAddress(user.getAddress()), user.getPhone(),
                user.getWebsite(), getDtoCompany(user.getCompany()));
    }

    private com.cubicfox.dto.Address getDtoAddress(Address address) {
        return new com.cubicfox.dto.Address(address.getStreet(), address.getSuite(),
                address.getCity(), address.getZipcode(), getDtoGeo(address.getGeo()));
    }

    private com.cubicfox.dto.Geo getDtoGeo(Geo geo) {
        return new com.cubicfox.dto.Geo(geo.getLat(), geo.getLng());
    }

    private com.cubicfox.dto.Company getDtoCompany(Company company) {
        return new com.cubicfox.dto.Company(company.getName(), company.getCatchPhrase(), company.getBs());
    }
}
