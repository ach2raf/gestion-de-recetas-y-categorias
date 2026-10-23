package com.salesianos.dam.gestion_de_recetas_y_categorias.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NombreRecetaUnicoValidator.class)
@Documented
public @interface NombreRecetaUnico {

    String message() default "Ya existe una receta con ese nombre";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
