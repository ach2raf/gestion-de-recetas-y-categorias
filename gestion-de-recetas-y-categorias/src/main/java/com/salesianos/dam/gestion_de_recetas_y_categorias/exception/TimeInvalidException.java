package com.salesianos.dam.gestion_de_recetas_y_categorias.exception;

public class TimeInvalidException extends RuntimeException {

    public TimeInvalidException(Integer tiempo){
        super("El tiempo de preparación debe ser mayor que 0 (valor recibido: " + tiempo + ")");
    }
}
