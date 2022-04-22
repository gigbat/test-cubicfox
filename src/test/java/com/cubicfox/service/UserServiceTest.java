package com.cubicfox.service;

import com.cubicfox.client.api.UserIntegrationService;
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
        ObjectMapper mapper = new ObjectMapper();
        byte[] actual = getActualContent(users, mapper);
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
        List<User> users = userService.saveUsers(integrationUsers);
        ObjectMapper mapper = new ObjectMapper();
        byte[] actual = getActualContent(users, mapper);
        File expected = getExpectedContent("./tests/expected-database.json");
        try {
            JsonNode jsonNodeActual = mapper.readTree(actual);
            JsonNode jsonNodeExpected = mapper.readTree(expected);
            Assertions.assertEquals(jsonNodeExpected, jsonNodeActual);
        } catch (IOException e) {
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

    private File getExpectedContent(String url) {
        URL resource = Thread.currentThread()
                .getContextClassLoader().getResource(url);
        try {
            return new File(Objects.requireNonNull(resource).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
