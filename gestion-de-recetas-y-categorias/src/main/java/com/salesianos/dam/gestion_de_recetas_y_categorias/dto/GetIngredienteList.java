package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Ingrediente;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "GetIngredienteList",
        description = "Item de una lista de ingredientes"
)
public record GetIngredienteList(
        @Schema(
                description = "Identificador único del ingrdiente",
                example = "78"
        )
    Long id,
    @Schema(
            description = "Nombre del ingrediente",
            example = "zanahoria"
    )
    String nombre
) {

    public static GetIngredienteList of(Ingrediente i){
        return new GetIngredienteList(
            i.getId(),
            i.getNombre()
        );
    }
}
