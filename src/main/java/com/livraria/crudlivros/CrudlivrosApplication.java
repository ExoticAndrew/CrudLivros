package com.livraria.crudlivros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrudlivrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudlivrosApplication.class, args);
	}

}
