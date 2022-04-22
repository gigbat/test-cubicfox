package com.cubicfox.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = {
        DataSourceConfiguration.class, FlywayConfiguration.class
})
public class RepositoryConfiguration {

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(@Autowired DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
