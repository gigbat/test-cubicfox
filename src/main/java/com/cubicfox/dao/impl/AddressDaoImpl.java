package com.cubicfox.dao.impl;

import com.cubicfox.dao.AbstractGenericPostgresExecutor;
import com.cubicfox.dao.api.AddressDao;
import com.cubicfox.model.Address;
import com.cubicfox.model.User;
import com.cubicfox.util.ColumnsConverterUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl extends AbstractGenericPostgresExecutor implements AddressDao {
    @Override
    public Address saveAddress(User user) {
        String columns = ColumnsConverterUtil
                .convertColumnsToString(",", null,
                        ADDRESS_CITY, ADDRESS_GEO_ID, ADDRESS_STREET, ADDRESS_SUITE, ADDRESS_ZIPCODE);
        String values = ColumnsConverterUtil
                .convertColumnsToString(",", ":",
                        ADDRESS_CITY, ADDRESS_GEO_ID, ADDRESS_STREET, ADDRESS_SUITE, ADDRESS_ZIPCODE);

        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue(ADDRESS_CITY, user.getAddress().getCity());
        source.addValue(ADDRESS_GEO_ID, user.getAddress().getGeo().getId());
        source.addValue(ADDRESS_STREET, user.getAddress().getStreet());
        source.addValue(ADDRESS_SUITE, user.getAddress().getSuite());
        source.addValue(ADDRESS_ZIPCODE, user.getAddress().getZipcode());

        long id = executeInsert(String.format(INSERT_QUERY, TABLE_NAME, columns, values), source);
        Address address = user.getAddress();
        address.setId(id);
        return address;
    }
}
