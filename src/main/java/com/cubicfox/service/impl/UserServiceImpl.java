package com.cubicfox.service.impl;

import com.cubicfox.dao.api.AddressDao;
import com.cubicfox.dao.api.CompanyDao;
import com.cubicfox.dao.api.GeoDao;
import com.cubicfox.dao.api.UserDao;
import com.cubicfox.model.Address;
import com.cubicfox.model.Company;
import com.cubicfox.model.Geo;
import com.cubicfox.model.User;
import com.cubicfox.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private GeoDao geoDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> saveUsers(List<User> users) throws Exception {
        return users.stream().peek(user -> {
            Geo geo = geoDao.saveGeo(user);
            LOG.info("Saved geo data.");
            Address address = addressDao.saveAddress(user);
            LOG.info("Saved address data in the city " + address.getCity()
                    + " by street " + address.getStreet() + ".");
            address.setGeo(geo);
            Company company = companyDao.saveCompany(user);
            LOG.info("Saved company data named like " + company.getName() + ".");
            user.setAddress(address);
            user.setCompany(company);
            userDao.saveUser(user);
            LOG.info("Saved user data under username " + user.getUsername() + ".");
        }).collect(Collectors.toList());
    }

    @Override
    public void clearAll() {
        userDao.clearAll();
    }
}
