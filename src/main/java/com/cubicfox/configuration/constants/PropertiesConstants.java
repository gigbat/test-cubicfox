package com.cubicfox.configuration.constants;

public interface PropertiesConstants {
    String POSTGRES_DRIVER_NAME = "postgres.driver";
    String POSTGRES_URL = "postgres.url";
    String POSTGRES_USERNAME = "postgres.username";
    String POSTGRES_PASSWORD = "postgres.password";
    String POSTGRES_DATABASE = "postgres.database";
    String POSTGRES_HIKARI_SCHEMA = "postgres.hikari.schema";
    String POSTGRES_HIKARI_IDLE_TIMEOUT = "postgres.hikari.idleTimeout";
    String POSTGRES_HIKARI_MAX_LIFE_TIME= "postgres.hikari.maxLifetime";
    String POSTGRES_HIKARI_MAXIMUM_POOL_SIZE = "postgres.hikari.maximumPoolSize";
    String POSTGRES_HIKARI_CONNECTION_INIT_SQL = "postgres.hikari.connectionInitSql";
    String POSTGRES_HIKARI_CONNECTION_TEST_QUERY = "postgres.hikari.connectionTestQuery";
    String POSTGRES_HIKARI_POOL_NAME = "postgres.hikari.poolName";
    String POSTGRES_HIKARI_AUTO_COMMIT = "postgres.hikari.autoCommit";

    String FLYWAY_LOCATION_MIGRATION = "postgres.flyway.location";
}
