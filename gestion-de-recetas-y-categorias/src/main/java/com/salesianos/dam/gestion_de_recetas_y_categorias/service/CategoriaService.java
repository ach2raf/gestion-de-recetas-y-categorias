package com.salesianos.dam.gestion_de_recetas_y_categorias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.EditCategoriaDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.exception.CategoriaNotFoundException;
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

    public Categoria getCategoriaById(Long id){
        return categoriaRepository.findById(id)
        .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria updateCategoria(Long id,EditCategoriaDto editCategoriaDto){
        return  categoriaRepository.findById(id)
        .map(c -> {
            c.setNombre(editCategoriaDto.nombre());
            c.setDescripcion(editCategoriaDto.descripcion());
            return categoriaRepository.save(c);
        })
        .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public List<Categoria> getAllCategorias(){
        List<Categoria> result= categoriaRepository.findAll();
        if(result.isEmpty()){
            throw new CategoriaNotFoundException();
        }
        return result;
    }

    public void deleteCategoria(Long id){
        if(!categoriaRepository.existsById(id)){
            throw new CategoriaNotFoundException(id);
        }
        categoriaRepository.deleteById(id);
    }

}
