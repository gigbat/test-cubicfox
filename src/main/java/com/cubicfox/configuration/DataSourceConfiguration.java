package com.cubicfox.configuration;

import com.cubicfox.configuration.constants.PropertiesConstants;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource(Environment environment) {
        HikariDataSource hikariDataSourceOriginal = new HikariDataSource();
        setDefaultHikariSettings(hikariDataSourceOriginal, environment);
        setAdditionalHikariSettings(hikariDataSourceOriginal, environment);

        return hikariDataSourceOriginal;
    }

    private void setAdditionalHikariSettings(HikariDataSource hikariDataSourceOriginal, Environment environment) {
        hikariDataSourceOriginal.setMaximumPoolSize(Integer.parseInt(Objects.requireNonNull(
                environment.getProperty(PropertiesConstants.POSTGRES_HIKARI_MAXIMUM_POOL_SIZE))));
        hikariDataSourceOriginal.setAutoCommit(
                Boolean.parseBoolean(environment.getProperty(PropertiesConstants.POSTGRES_HIKARI_AUTO_COMMIT)));
        hikariDataSourceOriginal.setConnectionInitSql(
                environment.getProperty(PropertiesConstants.POSTGRES_HIKARI_CONNECTION_INIT_SQL));
        hikariDataSourceOriginal.setConnectionTestQuery(
                environment.getProperty(PropertiesConstants.POSTGRES_HIKARI_CONNECTION_TEST_QUERY));
        hikariDataSourceOriginal.setPoolName(environment.getProperty(PropertiesConstants.POSTGRES_HIKARI_POOL_NAME));
        hikariDataSourceOriginal.setIdleTimeout(Long.parseLong(Objects.requireNonNull(
                environment.getProperty(PropertiesConstants.POSTGRES_HIKARI_IDLE_TIMEOUT))));
        hikariDataSourceOriginal.setMaxLifetime(Long.parseLong(Objects.requireNonNull(
                environment.getProperty(PropertiesConstants.POSTGRES_HIKARI_MAX_LIFE_TIME))));
        hikariDataSourceOriginal.setIdleTimeout(Long.parseLong(Objects.requireNonNull(
                environment.getProperty(PropertiesConstants.POSTGRES_HIKARI_IDLE_TIMEOUT))));
    }

    private void setDefaultHikariSettings(HikariDataSource hikariDataSourceOriginal, Environment environment) {
        hikariDataSourceOriginal.setDriverClassName(environment.getProperty(PropertiesConstants.POSTGRES_DRIVER_NAME));
        hikariDataSourceOriginal.setJdbcUrl(environment.getProperty(PropertiesConstants.POSTGRES_URL)
                + environment.getProperty(PropertiesConstants.POSTGRES_DATABASE));
        hikariDataSourceOriginal.setSchema(environment.getProperty(PropertiesConstants.POSTGRES_HIKARI_SCHEMA));
        hikariDataSourceOriginal.setUsername(environment.getProperty(PropertiesConstants.POSTGRES_USERNAME));
        hikariDataSourceOriginal.setPassword(environment.getProperty(PropertiesConstants.POSTGRES_PASSWORD));
    }

}
