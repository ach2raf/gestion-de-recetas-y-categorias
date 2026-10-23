package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Dificultad;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Receta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "GetRecetaList",
        description = "Item de una lista de recetas"
)
public record GetRecetaList(
        @Schema(
                description = "Identificador único de la receta",
                example = "290"
        )
        Long id,
        @Schema(
                description = "Nombre de la receta",
                example = "Albóndigas de la abuela"
        )
        String nombre,
        @Schema(
                description = "Tiempo de preparacion de la receta",
                example = "89"

        )
        Integer tiempoPreparacionMin,
        @Schema(
                description = "Dificultad de la receta",
                example = "DEFICIL"
        )
        Dificultad dificultad,
        @Schema(
                description = "Nombre de la categoría",
                example =  "Carnes"
        )
        String categoriaNombre
) {
    public static GetRecetaList of(Receta c) {
        return new GetRecetaList(
                c.getId(),
                c.getNombre(),
                c.getTiempoPreparacionMin(),
                c.getDificultad(),
                c.getCategoria() != null ? c.getCategoria().getNombre() : null
        );
    }
}
