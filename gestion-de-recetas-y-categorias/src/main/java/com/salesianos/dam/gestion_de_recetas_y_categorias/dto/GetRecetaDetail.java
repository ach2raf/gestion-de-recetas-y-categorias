package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Dificultad;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Receta;

public record GetRecetaDetail(
        Long id,
        String nombre,
        Integer tiempoPreparacionMin,
        Dificultad dificultad,
        Long categoriaId,
        String categoriaNombre,
        String categoriaDescripcion

) {
    public static GetRecetaDetail of(Receta c){
        return new GetRecetaDetail(
                c.getId(),
                c.getNombre(),
                c.getTiempoPreparacionMin(),
                c.getDificultad(),
                c.getCategoria() != null ? c.getCategoria().getId() : null,
                c.getCategoria() != null ? c.getCategoria().getNombre() : null,
                c.getCategoria() != null ? c.getCategoria().getDescripcion() : null

                );
    }
}
