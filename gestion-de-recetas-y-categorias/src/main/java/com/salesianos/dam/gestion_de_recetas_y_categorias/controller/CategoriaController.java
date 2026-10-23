package com.salesianos.dam.gestion_de_recetas_y_categorias.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.EditCategoriaDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.GetCategoriaDetail;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.GetCategoriaList;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Categoria;
import com.salesianos.dam.gestion_de_recetas_y_categorias.repository.CategoriaRepository;
import com.salesianos.dam.gestion_de_recetas_y_categorias.service.CategoriaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categorias")
@Validated

public class CategoriaController {

    private final CategoriaRepository cataCategoriaRepository;
    private final CategoriaService categoriaService;

    @PostMapping 
    public ResponseEntity<GetCategoriaDetail> addCategoria(
        @Valid @RequestBody  EditCategoriaDto categoria
    ){
        return ResponseEntity.status(201)
        .body(GetCategoriaDetail.of(categoriaService.addCategoria(categoria)));
    }


    @GetMapping ("/{id}")
    public ResponseEntity<GetCategoriaDetail> getCategoriaById(@PathVariable @Min(value=1) Long id){
        return ResponseEntity.ok(GetCategoriaDetail.of(categoriaService.getCategoriaById(id)));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<GetCategoriaDetail> updateCategoria(@PathVariable Long id,@RequestBody EditCategoriaDto categoria){
        return ResponseEntity.ok(
            GetCategoriaDetail.of(categoriaService.updateCategoria(id, categoria))
        );
    }

    @GetMapping 
    public ResponseEntity<List<GetCategoriaList>> getAllCategorias(){
        List<Categoria> result= categoriaService.getAllCategorias();
        return ResponseEntity.ok(
            result
            .stream()
            .map(GetCategoriaList::of)
            .toList()
        );
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id){
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
