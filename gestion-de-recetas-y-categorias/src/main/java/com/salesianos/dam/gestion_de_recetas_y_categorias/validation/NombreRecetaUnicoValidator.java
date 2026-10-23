package com.salesianos.dam.gestion_de_recetas_y_categorias.validation;

import com.salesianos.dam.gestion_de_recetas_y_categorias.repository.RecetaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class NombreRecetaUnicoValidator implements ConstraintValidator<NombreRecetaUnico, String> {

    @Autowired
    private RecetaRepository recetaRepository;

    @Override
    public boolean isValid(String nombre, ConstraintValidatorContext context) {
        return StringUtils.hasText(nombre) && !recetaRepository.existsByName(nombre);

    }
}
