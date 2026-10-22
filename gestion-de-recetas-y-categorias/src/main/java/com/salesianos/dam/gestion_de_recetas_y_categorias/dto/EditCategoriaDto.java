package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EditCategoriaDto(
        String nombre,
        String descripcion
) {

    public Categoria to() {
        return Categoria.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .build();
    }
}
