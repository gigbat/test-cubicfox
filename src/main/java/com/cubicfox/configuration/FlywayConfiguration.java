package com.cubicfox.configuration;

import com.cubicfox.configuration.constants.PropertiesConstants;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class FlywayConfiguration {

    @Autowired
    private Environment environment;

    @PostConstruct
    public void migrate() {
        FluentConfiguration fluentConfiguration = Flyway.configure()
                .dataSource(environment.getProperty(PropertiesConstants.POSTGRES_URL)
                                + environment.getProperty(PropertiesConstants.POSTGRES_DATABASE),
                        environment.getProperty(PropertiesConstants.POSTGRES_USERNAME),
                        environment.getProperty(PropertiesConstants.POSTGRES_PASSWORD))
                .locations(environment.getProperty(PropertiesConstants.FLYWAY_LOCATION_MIGRATION))
                .placeholderReplacement(false)
                .baselineOnMigrate(true);
        Flyway flyway = fluentConfiguration.load();
        flyway.migrate();
    }
}
