package org.swss.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@EnableEurekaClient
public class AuthServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext cac = SpringApplication.run(AuthServerApplication.class, args);
		System.out.println(cac.getEnvironment().getProperty("DB_SERVER"));
	}
}
