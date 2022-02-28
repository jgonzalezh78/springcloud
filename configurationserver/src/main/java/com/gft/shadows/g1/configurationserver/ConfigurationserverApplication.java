package com.gft.shadows.g1.configurationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableConfigServer
@RefreshScope
public class ConfigurationserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationserverApplication.class, args);
	}

}
