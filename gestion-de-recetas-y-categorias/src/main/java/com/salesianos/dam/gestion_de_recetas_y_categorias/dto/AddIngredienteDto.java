package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.UnidadMedida;

public record AddIngredienteDto(
        Long ingredienteId,
        Double cantidad,
        UnidadMedida unidad) {

}
