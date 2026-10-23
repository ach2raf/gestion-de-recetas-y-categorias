package com.salesianos.dam.gestion_de_recetas_y_categorias.exception;

import jakarta.persistence.EntityNotFoundException;

public class IngredienteNotFoundException extends EntityNotFoundException {

    public IngredienteNotFoundException(){
        super("Ingrediente not found");
    }
    public IngredienteNotFoundException(Long id){
        super("Ingrediente not found with id:" +id);
    }

    public IngredienteNotFoundException(String message){
        super(message);
    }
}
