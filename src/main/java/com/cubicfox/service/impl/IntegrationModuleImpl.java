package com.cubicfox.service.impl;

import com.cubicfox.client.api.UserIntegrationService;
import com.cubicfox.model.User;
import com.cubicfox.service.api.IntegrationModule;
import com.cubicfox.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IntegrationModuleImpl implements IntegrationModule {
    @Autowired
    private UserService userService;
    @Autowired
    private UserIntegrationService userIntegrationService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveIntegrationUsers() {
        List<User> integrationUsers = userIntegrationService.getUsers();
        userService.saveUsers(integrationUsers);
    }
}
