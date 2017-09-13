package com.user.SpringBootEclipse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringBootMvcCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcCrudApplication.class, args);
	}
}
