package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "GetCategoriaDetail",
        description = "Detalle de una categoria"
)
public record GetCategoriaDetail(
        @Schema(
                description = "Identificador único de la categoria",
                example = "30"
        )
        Long id,
        @Schema(
                description = "Nombre de una categoria",
                example = "Entrantes y Aperitivos"
        )
        String nombre,
        @Schema(
                description = "Descripcion de una categoria",
                example = "Bocados ligeros para abrir el apetito. Se sirven antes del plato principal."
        )
        String descripcion
) {

    public static GetCategoriaDetail of(Categoria c) {
        return new GetCategoriaDetail(
            c.getId(), 
            c.getNombre(), 
            c.getDescripcion()
        );
    }
}
