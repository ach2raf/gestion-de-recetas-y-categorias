package com.salesianos.dam.gestion_de_recetas_y_categorias.exception;

import jakarta.persistence.EntityNotFoundException;

public class RecetaNotFoundException extends EntityNotFoundException {

    public RecetaNotFoundException(){
        super("Receta not found");
    }
    public RecetaNotFoundException(Long id){
        super("Receta not found with id:" +id);
    }
    public RecetaNotFoundException(String message) {
        super(message);
    }
}
