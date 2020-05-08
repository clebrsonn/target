package br.com.hyteck.platform.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Plataforma Target")
                        .version("1.0.0")
                        .description("Api usada para o desenvolvimento do carrinho de compras para a Plataforma Target"))
                .externalDocs(new ExternalDocumentation()
                        .description("")
                        .url("/v3"));
    }
}
