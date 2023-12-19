package com.plexus.plextalent.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.plexus.plextalent.mapper")
public class SwaggerConfig {

	@Bean
	GroupedOpenApi api() {
		return GroupedOpenApi.builder().group("apiGroup").packagesToScan("com.plexus.plextalent").build();
	}
}
