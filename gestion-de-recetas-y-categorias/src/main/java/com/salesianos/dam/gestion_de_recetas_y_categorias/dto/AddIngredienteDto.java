package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.UnidadMedida;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddIngredienteDto(
        @NotNull(message = "El id del ingrediente es obligatorio")
        Long ingredienteId,
        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor que 0")
        Double cantidad,
        @NotNull(message = "La unidad de medida es obligatoria")
        UnidadMedida unidad) {

}
