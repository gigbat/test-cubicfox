package com.cubicfox.util;

import com.cubicfox.DefaultCreator;
import com.cubicfox.model.Address;
import com.cubicfox.model.Company;
import com.cubicfox.model.Geo;
import com.cubicfox.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.web.client.ResourceAccessException;

import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class JsonConverterUtilTests {

    private static final String URL_CONNECTION = "jsonplaceholder.typicode.com";

    @Autowired
    private Environment environment;

    @Test
    public void convertSuccessfullyTest() {
        List<User> users = JsonConverterUtil.convertToPojo(User.class, environment.getProperty(URL_CONNECTION));
        Assertions.assertEquals(10, users.size());
        List<Address> address = users.stream().map(User::getAddress).collect(Collectors.toList());
        Assertions.assertEquals(10, address.size());
        List<Geo> geos = address.stream().map(Address::getGeo).collect(Collectors.toList());
        Assertions.assertEquals(10, geos.size());
        List<Company> companies = users.stream().map(User::getCompany).collect(Collectors.toList());
        Assertions.assertEquals(10, companies.size());

        Assertions.assertEquals(new DefaultCreator().createDefaultUser().getPhone(), users.get(2).getPhone());
        Assertions.assertEquals(new DefaultCreator().createDefaultAddress().getCity(),
                users.get(2).getAddress().getCity());
        Assertions.assertEquals(new DefaultCreator().createDefaultGeo().getLat(),
                users.get(2).getAddress().getGeo().getLat());
        Assertions.assertEquals(new DefaultCreator().createDefaultCompany().getCatchPhrase(),
                users.get(2).getCompany().getCatchPhrase());
    }

    @Test
    public void checkIfCallingHaveSameCountTest() {
        Assertions.assertEquals(10,
                JsonConverterUtil.convertToPojo(User.class, environment.getProperty(URL_CONNECTION)).size());
        Assertions.assertEquals(10,
                JsonConverterUtil.convertToPojo(User.class, environment.getProperty(URL_CONNECTION)).size());
        Assertions.assertEquals(10,
                JsonConverterUtil.convertToPojo(User.class, environment.getProperty(URL_CONNECTION)).size());
        Assertions.assertEquals(10,
                JsonConverterUtil.convertToPojo(User.class, environment.getProperty(URL_CONNECTION)).size());
        Assertions.assertEquals(10,
                JsonConverterUtil.convertToPojo(User.class, environment.getProperty(URL_CONNECTION)).size());
        Assertions.assertEquals(10,
                JsonConverterUtil.convertToPojo(User.class, environment.getProperty(URL_CONNECTION)).size());
        Assertions.assertEquals(10,
                JsonConverterUtil.convertToPojo(User.class, environment.getProperty(URL_CONNECTION)).size());
    }

    @Test
    public void convertSuccessfullyNegativeFlowTest() {
        List<User> users = JsonConverterUtil.convertToPojo(User.class, environment.getProperty(URL_CONNECTION));
        Assertions.assertNotEquals(3, users.size());
        List<Address> address = users.stream().map(User::getAddress).collect(Collectors.toList());
        Assertions.assertNotEquals(1, address.size());
        List<Geo> geos = address.stream().map(Address::getGeo).collect(Collectors.toList());
        Assertions.assertNotEquals(7, geos.size());
        List<Company> companies = users.stream().map(User::getCompany).collect(Collectors.toList());
        Assertions.assertNotEquals(5, companies.size());

        Assertions.assertNotEquals(new DefaultCreator().createDefaultUser().getPhone(),
                users.get(3).getPhone());
        Assertions.assertNotEquals(new DefaultCreator().createDefaultAddress().getCity(),
                users.get(5).getAddress().getCity());
        Assertions.assertNotEquals(new DefaultCreator().createDefaultGeo().getLat(),
                users.get(8).getAddress().getGeo().getLat());
        Assertions.assertNotEquals(new DefaultCreator().createDefaultCompany().getCatchPhrase(),
                users.get(1).getCompany().getCatchPhrase());
    }

    @Test
    public void convertSuccessfullyCheckIfEmptyTest() {
        List<User> users = JsonConverterUtil.convertToPojo(User.class, environment.getProperty(URL_CONNECTION));
        Assertions.assertFalse(users.isEmpty());
        List<Address> address = users.stream().map(User::getAddress).collect(Collectors.toList());
        Assertions.assertFalse(address.isEmpty());
        List<Geo> geos = address.stream().map(Address::getGeo).collect(Collectors.toList());
        Assertions.assertFalse(geos.isEmpty());
        List<Company> companies = users.stream().map(User::getCompany).collect(Collectors.toList());
        Assertions.assertFalse(companies.isEmpty());
    }

    @Test
    public void notConvertibleClassTest() {
        Assertions.assertThrowsExactly(RuntimeException.class,
                () -> JsonConverterUtil.convertToPojo(Geo.class, environment.getProperty(URL_CONNECTION)));
        Assertions.assertThrowsExactly(RuntimeException.class,
                () -> JsonConverterUtil.convertToPojo(Address.class, environment.getProperty(URL_CONNECTION)));
        Assertions.assertThrowsExactly(RuntimeException.class,
                () -> JsonConverterUtil.convertToPojo(Company.class, environment.getProperty(URL_CONNECTION)));

    }

    @Test
    public void urlIsNotCorrectTest() {
        Assertions.assertThrowsExactly(ResourceAccessException.class,
                () -> JsonConverterUtil.convertToPojo(User.class, "https://localhost"));
        Assertions.assertThrowsExactly(ResourceAccessException.class,
                () -> JsonConverterUtil.convertToPojo(User.class, "https://asdqwe"));
        Assertions.assertThrowsExactly(HttpClientErrorException.NotFound.class,
                () -> JsonConverterUtil.convertToPojo(User.class, environment.getProperty(URL_CONNECTION)
                        + "asdasdqwe"));
        Assertions.assertThrowsExactly(ResourceAccessException.class,
                () -> JsonConverterUtil.convertToPojo(User.class, "asdasdqwe"
                        + environment.getProperty(URL_CONNECTION)));
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> JsonConverterUtil.convertToPojo(User.class, URL_CONNECTION));
        Assertions.assertThrowsExactly(HttpClientErrorException.NotFound.class,
                () -> JsonConverterUtil.convertToPojo(User.class, environment.getProperty(URL_CONNECTION) + "qq"));
    }

}
