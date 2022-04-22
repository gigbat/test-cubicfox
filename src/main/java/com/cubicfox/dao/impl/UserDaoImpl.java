package com.cubicfox.dao.impl;

import com.cubicfox.dao.AbstractGenericPostgresExecutor;
import com.cubicfox.dao.api.UserDao;
import com.cubicfox.model.User;
import com.cubicfox.util.ColumnsConverterUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractGenericPostgresExecutor implements UserDao {

    @Override
    public User saveUser(User user) {
        String columns = ColumnsConverterUtil
                .convertColumnsToString(",", null,
                        USER_ADDRESS_ID, USER_COMPANY_ID, USER_EMAIL, USER_NAME,
                        USER_PHONE, USER_USERNAME, USER_WEBSITE);
        String values = ColumnsConverterUtil
                .convertColumnsToString(",", ":",
                        USER_ADDRESS_ID, USER_COMPANY_ID, USER_EMAIL, USER_NAME,
                        USER_PHONE, USER_USERNAME, USER_WEBSITE);

        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue(USER_ADDRESS_ID, user.getAddress().getId());
        source.addValue(USER_COMPANY_ID, user.getCompany().getId());
        source.addValue(USER_EMAIL, user.getEmail());
        source.addValue(USER_NAME, user.getName());
        source.addValue(USER_PHONE, user.getPhone());
        source.addValue(USER_USERNAME, user.getUsername());
        source.addValue(USER_WEBSITE, user.getWebsite());

        long id = executeInsert(String.format(INSERT_QUERY, TABLE_NAME, columns, values), source);
        user.setId(id);
        return user;
    }

    @Override
    public void clearAll() {
        getOperations().getJdbcOperations().execute("DROP TABLE IF EXISTS cubicfox_geo CASCADE");
        getOperations().getJdbcOperations().execute("DROP TABLE IF EXISTS cubicfox_company CASCADE");
        getOperations().getJdbcOperations().execute("DROP TABLE IF EXISTS cubicfox_address CASCADE");
        getOperations().getJdbcOperations().execute("DROP TABLE IF EXISTS cubicfox_user CASCADE");
        getOperations().getJdbcOperations().execute("DROP TABLE IF EXISTS flyway_schema_history CASCADE");
    }
}
