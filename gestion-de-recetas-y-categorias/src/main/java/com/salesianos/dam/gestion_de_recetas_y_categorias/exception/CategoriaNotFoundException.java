package com.salesianos.dam.gestion_de_recetas_y_categorias.exception;

import jakarta.persistence.EntityNotFoundException;

public class CategoriaNotFoundException extends EntityNotFoundException{

    public CategoriaNotFoundException(){
        super("Categoria not found");
    }
    public CategoriaNotFoundException(Long id){
        super("Categoria not found with id:" +id);
    }

    public CategoriaNotFoundException(String message){
        super(message);
    }
}
