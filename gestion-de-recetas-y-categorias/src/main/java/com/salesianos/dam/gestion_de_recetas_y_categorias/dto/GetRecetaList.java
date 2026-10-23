package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Dificultad;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Receta;

public record GetRecetaList(
        Long id,
        String nombre,
        Integer tiempoPreparacionMin,
        Dificultad dificultad,
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
