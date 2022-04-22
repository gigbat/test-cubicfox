package com.cubicfox.dao.api;

import com.cubicfox.model.Address;
import com.cubicfox.model.User;

public interface AddressDao {
    String ADDRESS_CITY = "address_city";
    String ADDRESS_GEO_ID = "address_geo_id";
    String ADDRESS_STREET = "address_street";
    String ADDRESS_SUITE = "address_suite";
    String ADDRESS_ZIPCODE = "address_zipcode";
    String TABLE_NAME = "cubicfox_address";
    Address saveAddress(User user);
}
