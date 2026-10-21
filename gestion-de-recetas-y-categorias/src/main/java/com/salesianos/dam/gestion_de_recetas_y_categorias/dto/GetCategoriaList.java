package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Categoria;

public record GetCategoriaList(
        Long id,
        String nombre
) {

    public static GetCategoriaList of(Categoria c) {
        return new GetCategoriaList(
            c.getId(),
            c.getNombre()
        );
    }
}