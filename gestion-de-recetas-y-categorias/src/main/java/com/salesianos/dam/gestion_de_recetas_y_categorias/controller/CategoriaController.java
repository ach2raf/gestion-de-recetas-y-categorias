package com.salesianos.dam.gestion_de_recetas_y_categorias.controller;

import java.util.List;

import org.springframework.http.ProblemDetail;
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

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categorias")
@Validated
@Tag(name="Categoria Controller", description = "Un controlador de categorias")

public class CategoriaController {

    private final CategoriaRepository cataCategoriaRepository;
    private final CategoriaService categoriaService;

    @Operation(summary = "Crear una categoría")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoría creada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "409", description = "Ya existe una categoría con ese nombre")
    })
    @PostMapping 
    public ResponseEntity<GetCategoriaDetail> addCategoria(
        @Valid @RequestBody  EditCategoriaDto categoria
    ){
        return ResponseEntity.status(201)
        .body(GetCategoriaDetail.of(categoriaService.addCategoria(categoria)));
    }


    @Operation(summary = "Obtener una categoría por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "400", description = "Id no válido"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping ("/{id}")
    public ResponseEntity<GetCategoriaDetail> getCategoriaById(@PathVariable @Min(value=1) Long id){
        return ResponseEntity.ok(GetCategoriaDetail.of(categoriaService.getCategoriaById(id)));
    }

    @Operation(summary = "Actualizar una categoría")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría actualizada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @ApiResponse(responseCode = "409", description = "Ya existe una categoría con ese nombre")
    })
    @PutMapping ("/{id}")
    public ResponseEntity<GetCategoriaDetail> updateCategoria(@PathVariable Long id,@RequestBody EditCategoriaDto categoria){
        return ResponseEntity.ok(
            GetCategoriaDetail.of(categoriaService.updateCategoria(id, categoria))
        );
    }

    @Operation(summary = "Listar todas las categorías")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de categorías"),
            @ApiResponse(responseCode = "404", description = "No hay categorías")
    })
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

    @Operation(summary = "Borrar una categoría")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoría borrada"),
            @ApiResponse(responseCode = "400", description = "Id no válido"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @ApiResponse(responseCode = "409", description = "La categoría tiene recetas asociadas")
    })
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id){
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
