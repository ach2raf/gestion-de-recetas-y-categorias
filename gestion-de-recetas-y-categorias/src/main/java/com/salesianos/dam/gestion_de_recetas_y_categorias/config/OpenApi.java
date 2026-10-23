package com.salesianos.dam.gestion_de_recetas_y_categorias.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi {

    @Bean
    public OpenAPI recetasOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API de Gestión de Recetas")
                .version("1.0.0")
                .description("API REST para gestionar recetas, categorías e ingredientes")
                .contact(new Contact().name("Achraf Faleh")));
    }
}
