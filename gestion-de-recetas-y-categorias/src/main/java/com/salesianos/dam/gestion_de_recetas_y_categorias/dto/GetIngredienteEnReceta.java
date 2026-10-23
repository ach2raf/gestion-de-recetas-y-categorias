package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.RecetaIngrediente;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.UnidadMedida;
import io.swagger.v3.oas.annotations.media.Schema;


public record GetIngredienteEnReceta(
        Long id,
        String nombre,
        Double cantidad,
        UnidadMedida unidad
    ) {

        public static GetIngredienteEnReceta of(RecetaIngrediente ri) {
        return new GetIngredienteEnReceta(
                ri.getIngrediente().getId(),
                ri.getIngrediente().getNombre(),
                ri.getCantidad(),
                ri.getUnidad()
        );
    }
}
