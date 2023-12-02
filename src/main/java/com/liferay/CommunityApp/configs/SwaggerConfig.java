package com.liferay.CommunityApp.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Liferay Community API")
                        .description("Esta é a API para a comunidade Liferay, oferecendo uma variedade de serviços e recursos.")
                        .version("1.0.0")
                        .termsOfService("http://terms.of.service.url/")
                        .contact(new Contact()
                                .name("Lucas Davi")
                                .email("lucasdavi99@hotmial.com")
                                .url("http://url.do.contato/")
                        )
                        .license(new License()
                                .name("Licença API")
                                .url("http://url.da.licenca/")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }

    @Bean
    CommandLineRunner openBrowser() {
        return args -> {
            String os = System.getProperty("os.name").toLowerCase();
            String swaggerUrl = "http://localhost:8080/swagger-ui.html";
            try {
                if (os.contains("win")) {
                    // Windows
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", swaggerUrl});
                } else if (os.contains("mac")) {
                    // MacOS
                    Runtime.getRuntime().exec(new String[]{"open", swaggerUrl});
                } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                    // Unix or Linux
                    Runtime.getRuntime().exec(new String[]{"xdg-open", swaggerUrl});
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
