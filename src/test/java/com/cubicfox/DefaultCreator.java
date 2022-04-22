package com.cubicfox;

import com.cubicfox.model.Address;
import com.cubicfox.model.Company;
import com.cubicfox.model.Geo;
import com.cubicfox.model.User;

public class DefaultCreator {
    public User createDefaultUser() {
        User user = new User();
        user.setId(3L);
        user.setName("Clementine Bauch");
        user.setUsername("Samantha");
        user.setEmail("Nathan@yesenia.net");
        user.setAddress(createDefaultAddress());
        user.setPhone("1-463-123-4447");
        user.setWebsite("ramiro.info");
        user.setCompany(createDefaultCompany());
        return user;
    }

    public Address createDefaultAddress() {
        Address address = new Address();
        address.setId(3L);
        address.setStreet("Douglas Extension");
        address.setSuite("Suite 847");
        address.setCity("McKenziehaven");
        address.setZipcode("59590-4157");
        address.setGeo(createDefaultGeo());
        return address;
    }

    public Geo createDefaultGeo() {
        Geo geo = new Geo();
        geo.setId(3L);
        geo.setLat(-68.6102);
        geo.setLng(-47.0653);
        return geo;
    }

    public Company createDefaultCompany() {
        Company company = new Company();
        company.setId(3L);
        company.setName("Romaguera-Jacobson");
        company.setCatchPhrase("Face to face bifurcated interface");
        company.setBs("e-enable strategic applications");
        return company;
    }
}
