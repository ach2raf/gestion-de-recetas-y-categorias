package com.salesianos.dam.gestion_de_recetas_y_categorias.service;

import org.springframework.stereotype.Service;

import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.EditIngredienteDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Ingrediente;
import com.salesianos.dam.gestion_de_recetas_y_categorias.repository.IngredienteRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor

public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public Ingrediente addIngrediente(EditIngredienteDto editIngredienteDto){
        return ingredienteRepository.save(editIngredienteDto.to());
    }
}
