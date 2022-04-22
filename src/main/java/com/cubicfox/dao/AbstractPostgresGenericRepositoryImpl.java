package com.cubicfox.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractPostgresGenericRepositoryImpl {

    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    public NamedParameterJdbcOperations getOperations() {
        return namedParameterJdbcOperations;
    }

    @Autowired
    public void setNamedParameterJdbcOperations(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }
}
