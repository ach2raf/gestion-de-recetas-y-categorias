package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Ingrediente;

public record EditIngredienteDto(
    String nombre
) {

    public Ingrediente  to(){
        return  Ingrediente.builder()
        .nombre(nombre)
        .build();
    }
}
