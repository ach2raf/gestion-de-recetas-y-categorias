package com.salesianos.dam.gestion_de_recetas_y_categorias.service;

import org.springframework.stereotype.Service;

import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.EditCategoriaDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Categoria;
import com.salesianos.dam.gestion_de_recetas_y_categorias.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor

public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categoria addCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }


}
