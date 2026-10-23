package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Ingrediente;

public record GetIngredienteList(
    Long id,
    String nombre
) {

    public static GetIngredienteList of(Ingrediente i){
        return new GetIngredienteList(
            i.getId(),
            i.getNombre()
        );
    }
}
