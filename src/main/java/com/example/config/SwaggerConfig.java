package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
//				.select().apis(RequestHandlerSelectors.any())
				.select().apis(RequestHandlerSelectors.basePackage("com.example.controllers"))
				.paths(PathSelectors.any()).build()
				.useDefaultResponseMessages(false)
				;
//				.paths(PathSelectors.ant("/users/*")).build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Titulo de la API")
				.version("1.0.0")
				.license("My Licence")
				.contact(new Contact("@raiden", "http://devs4j.com", "contanto@devs4j.com"))
				.build();
	}

}
