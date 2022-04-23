package com.cubicfox.service.impl;

import com.cubicfox.client.api.UserIntegrationService;
import com.cubicfox.model.Address;
import com.cubicfox.model.Company;
import com.cubicfox.model.Geo;
import com.cubicfox.model.User;
import com.cubicfox.service.api.IntegrationModule;
import com.cubicfox.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntegrationModuleImpl implements IntegrationModule {
    @Autowired
    private UserService userService;
    @Autowired
    private UserIntegrationService userIntegrationService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> saveIntegrationUsers() {
        List<User> integrationUsers = userIntegrationService.getUsers();
        try {
            List<User> savedUsers = userService.saveUsers(integrationUsers);
            List<com.cubicfox.dto.User> dtoUsers = getDtoUsers(savedUsers);
            if (integrationUsers.size() == savedUsers.size()) {
                return new ResponseEntity<>(dtoUsers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("The size of saved and integrated users not the same",
                        HttpStatus.OK);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("You have already saved users", HttpStatus.BAD_REQUEST);
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
