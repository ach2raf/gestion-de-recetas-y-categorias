package com.salesianos.dam.gestion_de_recetas_y_categorias.exception;

public class IngredienteAlreadyAddedException extends RuntimeException {

    public IngredienteAlreadyAddedException(Long recetaId, Long ingredienteId){
        super("Ingrediente " + ingredienteId + " ya añadido a la receta " + recetaId);
    }
}