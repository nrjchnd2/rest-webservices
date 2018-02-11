package com.neeraj.rest.webservices.restwebservices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@SuppressWarnings("deprecation")
	private static final ApiInfo MY_API_INFO = new ApiInfo("My First SpringBoot Rest Api", "This Is My First SpringBoot Rest Api", 
			                                        "1.0", "www.gontu.com", 
			                                        "Neeraj Chand", "My License", "XYZ");
	
	private static final Set<String> DEFAULT_PRODUCES = new HashSet<String>(Arrays.asList("application/json","application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(MY_API_INFO)
				.produces(DEFAULT_PRODUCES)
				.consumes(DEFAULT_PRODUCES); 
	}

}
