package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Ingrediente;

public record GetIngredienteDetail(
    Long id,
    String nombre
) {

    public static GetIngredienteDetail of(Ingrediente i){
      return new GetIngredienteDetail(
        i.getId(),
        i.getNombre()
        )  ;
    }
}
