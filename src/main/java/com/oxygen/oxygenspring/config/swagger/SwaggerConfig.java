package com.oxygen.oxygenspring.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title("Oxygen")
                .description("항해커톤 AI 멘토 서비스")
                .version("v1");

        String jwtAuth = "jwtAuth";

        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtAuth);

        Components components = new Components().addSecuritySchemes(jwtAuth, new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .name("TOKEN")
                .in(SecurityScheme.In.HEADER)
                .scheme("Bearer")
                .bearerFormat("JWT"));

        OpenAPI openAPI = new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);

        Server prodServer = new Server().url("https://api.oxygen-ai.site")
                .description("운영 서버");

        Server localServer = new Server().url("http://localhost:8080")
                .description("로컬 서버 - 개발자용");

        openAPI.setServers(List.of(prodServer, localServer));

        return openAPI;
    }
}
