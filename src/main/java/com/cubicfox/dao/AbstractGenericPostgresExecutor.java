package com.cubicfox.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public abstract class AbstractGenericPostgresExecutor extends AbstractPostgresGenericRepositoryImpl {
    private static final String PK_COLUMN = "id";
    protected static final String INSERT_QUERY = "insert into %s (%s) values (%s)";

    public long executeInsert(final String sql,
                              final MapSqlParameterSource params) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getOperations().update(sql, params, keyHolder, new String[]{PK_COLUMN});
        Number numberKey = keyHolder.getKey();

        return numberKey == null ? 0 : numberKey.longValue();
    }

}
