package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Categoria;

public record GetCategoriaDetail(
        Long id,
        String nombre,
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
