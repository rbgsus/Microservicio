package com.plexus.plextalent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.plexus.plextalent.repository" // Especifica el paquete raíz que contiene
																			// los repositorios
})

@EntityScan(basePackages = { "com.plexus.plextalent.model" // Especifica el paquete raíz que contiene las entidades
})

public class PlextalentApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlextalentApplication.class, args);
	}

}
