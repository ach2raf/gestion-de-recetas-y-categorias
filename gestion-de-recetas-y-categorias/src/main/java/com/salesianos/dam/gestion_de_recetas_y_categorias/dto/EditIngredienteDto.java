package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Ingrediente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EditIngredienteDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    String nombre
) {

    public Ingrediente  to(){
        return  Ingrediente.builder()
        .nombre(nombre)
        .build();
    }
}
