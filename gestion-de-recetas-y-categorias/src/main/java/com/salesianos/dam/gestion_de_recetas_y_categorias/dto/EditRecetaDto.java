package com.salesianos.dam.gestion_de_recetas_y_categorias.dto;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Dificultad;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Receta;
import com.salesianos.dam.gestion_de_recetas_y_categorias.validation.NombreRecetaUnico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EditRecetaDto(
        @NombreRecetaUnico(message = "{editRecetaDto.nombre.NombreRecetaUnico}")
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
        String nombre,
        @NotNull(message = "El tiempo de preparación es obligatorio")
        Integer tiempoPreparacionMin,
        @NotNull(message = "La dificultad es obligatoria (FACIL, MEDIA o DIFICIL)")
        Dificultad dificultad,
        @NotNull(message = "La categoría es obligatoria")
        Long categoriaId

) {
    public Receta to(){
        return Receta.builder()
                .nombre(nombre)
                .tiempoPreparacionMin(tiempoPreparacionMin)
                .dificultad(dificultad)
                .build();
    }
}
