package com.yakuza.backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .groupName("Users")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/user/.*"))
                .build();
    }

    @Bean
    public Docket helloApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .groupName("Hello")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/hello"))
                .build();
    }

    @Bean Docket conferenceApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .groupName("Conferences")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/conference/.*"))
                .build();
    }

    @Bean Docket organizerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .groupName("Organizers")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/organizer/.*"))
                .build();
    }

    @Bean Docket paperApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .groupName("Papers")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/paper/.*"))
                .build();
    }

    private ApiKey apiKey(){
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }
}