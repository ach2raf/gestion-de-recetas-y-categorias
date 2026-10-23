package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import java.util.List;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Dificultad;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Receta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "GetRecetaDetail",
        description = "Detalle de una receta"
)
public record GetRecetaDetail(
        @Schema(
                description = "Identificador único de la receta",
                example = "23"
        )
        Long id,
        @Schema(
                description = "Nombre de la receta",
                example = "Albóndigas de la abuela"
        )
        String nombre,
        @Schema(
                description = "Tiempo de preparacion de la receta",
                example = "45"

        )
        Integer tiempoPreparacionMin,
        @Schema(
                description = "Dificultad de la receta",
                example = "FACIL"
        )
        Dificultad dificultad,
        @Schema(
                description = "Identificador de la categoria",
                example = "129"
        )
        Long categoriaId,
        @Schema(
                description = "Nombre de la categoría",
                example =  "Carnes"
        )
        String categoriaNombre,
        @Schema(
                description = "Descripcion de la categoria",
                example = "Recetas basadas en pollo, ternera, cerdo o cordero. Incluye asados, estofados y plancha."
        )
        String categoriaDescripcion,

        @Schema(
                description = "Ingredientes de la receta, cada uno con su cantidad y unidad de medida"
        )
        List<GetIngredienteEnReceta> ingredientes
    ) {
    public static GetRecetaDetail of(Receta c) {
        return new GetRecetaDetail(
                c.getId(),
                c.getNombre(),
                c.getTiempoPreparacionMin(),
                c.getDificultad(),
                c.getCategoria() != null ? c.getCategoria().getId() : null,
                c.getCategoria() != null ? c.getCategoria().getNombre() : null,
                c.getCategoria() != null ? c.getCategoria().getDescripcion() : null,
                c.getIngredientes().stream()
                        .map(GetIngredienteEnReceta::of)
                        .toList()

        );
    }
}
