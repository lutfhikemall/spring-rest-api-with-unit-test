package com.test.indivara.test_indivara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class TestIndivaraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestIndivaraApplication.class, args);
	}

}
