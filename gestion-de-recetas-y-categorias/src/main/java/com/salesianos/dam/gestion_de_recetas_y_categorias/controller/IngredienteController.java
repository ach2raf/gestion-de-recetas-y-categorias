package com.salesianos.dam.gestion_de_recetas_y_categorias.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.EditIngredienteDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.GetIngredienteDetail;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.GetIngredienteList;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Ingrediente;
import com.salesianos.dam.gestion_de_recetas_y_categorias.service.IngredienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ingrediente")
@Validated
@Tag(name = "Ingredientes", description = "Gestión de ingredientes")

public class IngredienteController {

    private final IngredienteService ingredienteService;

    @Operation(summary = "Crear un ingrediente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ingrediente creado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "409", description = "Ya existe un ingrediente con ese nombre")
    })
    @PostMapping
    public ResponseEntity<GetIngredienteDetail> addIngrediente(@Valid @RequestBody EditIngredienteDto ingrediente) {
        return ResponseEntity.status(201)
                .body(GetIngredienteDetail.of(ingredienteService.addIngrediente(ingrediente)));
    }

    @Operation(summary = "Listar todos los ingredientes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de ingredientes"),
            @ApiResponse(responseCode = "404", description = "No hay ingredientes")
    })
    @GetMapping
    public ResponseEntity<List<GetIngredienteList>> getAllIngredientes() {
        List<Ingrediente> result = ingredienteService.getAllIngredientes();
        return ResponseEntity.ok(
                result
                        .stream()
                        .map(GetIngredienteList::of)
                        .toList());
    }
}
