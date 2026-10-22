package com.salesianos.dam.gestion_de_recetas_y_categorias.service;

import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.AddIngredienteDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.EditRecetaDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.exception.CategoriaNotFoundException;
import com.salesianos.dam.gestion_de_recetas_y_categorias.exception.IngredienteAlreadyAddedException;
import com.salesianos.dam.gestion_de_recetas_y_categorias.exception.IngredienteNotFoundException;
import com.salesianos.dam.gestion_de_recetas_y_categorias.exception.RecetaNotFoundException;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Categoria;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Ingrediente;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Receta;
import com.salesianos.dam.gestion_de_recetas_y_categorias.repository.CategoriaRepository;
import com.salesianos.dam.gestion_de_recetas_y_categorias.repository.IngredienteRepository;
import com.salesianos.dam.gestion_de_recetas_y_categorias.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecetaService {
    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;
    private final IngredienteRepository ingredienteRepository;

    public Receta addReceta(EditRecetaDto editRecetaDto){

        Receta c=editRecetaDto.to();
        Categoria categoria=categoriaRepository.findById(editRecetaDto.categoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException());
        c.setCategoria(categoria);
        return recetaRepository.save(c);
    }

    public Receta getRecetaById(Long id){
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException(id));
    }

    public Receta updateReceta(Long id,EditRecetaDto editRecetaDto){
        return recetaRepository.findById(id)
                .map(r -> {
                    r.setNombre(editRecetaDto.nombre());
                    r.setTiempoPreparacionMin(editRecetaDto.tiempoPreparacionMin());
                    r.setDificultad(editRecetaDto.dificultad());
                    return recetaRepository.save(r);
                })
                .orElseThrow(() -> new RecetaNotFoundException(id));
    }

    public List<Receta> getAllRecetas(){
        List<Receta> result= recetaRepository.findAll();
        if(result.isEmpty()){
            throw new RecetaNotFoundException();
        }
        return result;
    }

    public void deleteReceta(Long id){
        if(!recetaRepository.existsById(id)){
            throw new RecetaNotFoundException(id);
        }
        recetaRepository.deleteById(id);
    }


    public Receta addIngredienteToReceta(Long recetaId, AddIngredienteDto addIngredienteDto){
    Receta receta = getRecetaById(recetaId);
    Ingrediente ingrediente = ingredienteRepository.findById(addIngredienteDto.ingredienteId())
            .orElseThrow(() -> new IngredienteNotFoundException(addIngredienteDto.ingredienteId()));

    boolean yaAnadido = receta.getIngredientes().stream()
            .anyMatch(i -> i.getId().equals(ingrediente.getId()));
    if(yaAnadido){
        throw new IngredienteAlreadyAddedException(recetaId, ingrediente.getId());
    }

    receta.getIngredientes().add(ingrediente);
    return recetaRepository.save(receta);
}
}
