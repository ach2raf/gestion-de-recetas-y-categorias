package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import java.util.List;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Dificultad;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Receta;

public record GetRecetaDetail(
        Long id,
        String nombre,
        Integer tiempoPreparacionMin,
        Dificultad dificultad,
        Long categoriaId,
        String categoriaNombre,
        String categoriaDescripcion,
        List<GetIngredienteDetail> ingredientes

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
                        .map(GetIngredienteDetail::of)
                        .toList()

        );
    }
}
