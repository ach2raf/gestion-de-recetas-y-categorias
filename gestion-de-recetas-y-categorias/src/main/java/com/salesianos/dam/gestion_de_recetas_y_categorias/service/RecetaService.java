package com.salesianos.dam.gestion_de_recetas_y_categorias.service;

import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.EditRecetaDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.exception.CategoriaNotFoundException;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Categoria;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Receta;
import com.salesianos.dam.gestion_de_recetas_y_categorias.repository.CategoriaRepository;
import com.salesianos.dam.gestion_de_recetas_y_categorias.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecetaService {
    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;

    public Receta addReceta(EditRecetaDto editRecetaDto){

        Receta c=editRecetaDto.to();
        Categoria categoria=categoriaRepository.findById(editRecetaDto.categoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException());
        c.setCategoria(categoria);
        return recetaRepository.save(c);
    }
}
