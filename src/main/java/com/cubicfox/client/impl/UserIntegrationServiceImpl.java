package com.cubicfox.client.impl;

import com.cubicfox.util.JsonConverterUtil;
import com.cubicfox.client.api.UserIntegrationService;
import com.cubicfox.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserIntegrationServiceImpl implements UserIntegrationService {
    private final Logger LOG = LoggerFactory.getLogger(UserIntegrationServiceImpl.class);
    private static final String URL_CONNECTION = "jsonplaceholder.typicode.com";
    private static final String REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    @Autowired
    private Environment environment;

    @Override
    public List<User> getUsers() {
        return validatedUsers();
    }

    private List<User> validatedUsers() {
        LOG.info("The attempt to get data from an integration system");
        List<User> users = JsonConverterUtil.convertToPojo(User.class,
                environment.getProperty(URL_CONNECTION));
        LOG.info("The data have a size: " + users.size());
        return users.stream()
                .filter(user -> user.getEmail().matches(REGEX))
                .collect(Collectors.toList());
    }
}
