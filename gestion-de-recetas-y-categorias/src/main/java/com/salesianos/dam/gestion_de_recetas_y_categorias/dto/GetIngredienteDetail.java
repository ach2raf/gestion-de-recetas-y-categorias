package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Ingrediente;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "GetIngredienteDetail",
        description = "Detalle de un ingrediente"
)
public record GetIngredienteDetail(
        @Schema(
                description = "Identificador de un ingrediente",
                example = "12"
        )
    Long id,
    @Schema(
            description = "Nombre de un ingrediente",
            example = "Aceite"
    )
    String nombre
) {

    public static GetIngredienteDetail of(Ingrediente i){
      return new GetIngredienteDetail(
        i.getId(),
        i.getNombre()
        )  ;
    }
}
