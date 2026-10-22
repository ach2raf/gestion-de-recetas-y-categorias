package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Dificultad;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Receta;

public record EditRecetaDto(
        String nombre,
        Integer tiempoPreparacionMin,
        Dificultad dificultad,
        Long categoriaId

) {
    public Receta to(){
        return Receta.builder()
                .nombre(nombre)
                .tiempoPreparacionMin(tiempoPreparacionMin)
                .dificultad(dificultad)
                .build();
    }
}
