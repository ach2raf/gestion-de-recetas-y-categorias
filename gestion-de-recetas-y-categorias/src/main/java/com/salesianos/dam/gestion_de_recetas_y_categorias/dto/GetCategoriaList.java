package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "GetCategoriaList",
        description = "Item de una lista de categorias"
)
public record GetCategoriaList(
        @Schema(
                description = "Identificador único de la categoria",
                example = "45"
        )
        Long id,
        @Schema(
                description = "Nombre del categoria",
                example = "Entrantes y Aperitivos"
        )
        String nombre
) {

    public static GetCategoriaList of(Categoria c) {
        return new GetCategoriaList(
            c.getId(),
            c.getNombre()
        );
    }
}