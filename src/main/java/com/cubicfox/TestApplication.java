package com.cubicfox;

import com.cubicfox.configuration.RepositoryConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.cubicfox.controller",
		"com.cubicfox.client",
		"com.cubicfox.dao",
		"com.cubicfox.service",
},
		basePackageClasses = RepositoryConfiguration.class)
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
