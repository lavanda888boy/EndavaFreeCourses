package com.webapp.endavacourseproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Mentors API", description = "API for assigning mentors to users"))
@SecurityScheme(name = "averageSpringFan", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@SecurityScheme(name = "averageSpringEnjoyer", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class EndavaCourseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndavaCourseProjectApplication.class, args);
    }

}
