package com.salesianos.dam.gestion_de_recetas_y_categorias.controller;

import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.AddIngredienteDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.EditCategoriaDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.EditRecetaDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.GetCategoriaDetail;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.GetCategoriaList;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.GetRecetaDetail;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.GetRecetaList;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Categoria;
import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Receta;
import com.salesianos.dam.gestion_de_recetas_y_categorias.service.RecetaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recetas")
@Validated

public class RecetaController {
    private final RecetaService recetaService;

    @PostMapping
    public ResponseEntity<GetRecetaDetail> addReceta(
            @Valid @RequestBody EditRecetaDto receta) {
        return ResponseEntity.status(201)
                .body(GetRecetaDetail.of(recetaService.addReceta(receta)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRecetaDetail> getRecetaById(@PathVariable @Min(value = 1) Long id) {
        return ResponseEntity.ok(GetRecetaDetail.of(recetaService.getRecetaById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetRecetaDetail> updateReceta(@PathVariable Long id, @RequestBody EditRecetaDto receta) {
        return ResponseEntity.ok(
                GetRecetaDetail.of(recetaService.updateReceta(id, receta)));
    }

    @GetMapping
    public ResponseEntity<List<GetRecetaList>> getAllRecetas() {
        List<Receta> result = recetaService.getAllRecetas();
        return ResponseEntity.ok(
                result
                        .stream()
                        .map(GetRecetaList::of)
                        .toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Long id) {
        recetaService.deleteReceta(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{recetaId}/ingredientes")
    public ResponseEntity<GetRecetaDetail> addIngredienteToReceta(
            @PathVariable Long recetaId,
            @RequestBody AddIngredienteDto addIngredienteDto) {
        return ResponseEntity.status(201)
                .body(GetRecetaDetail.of(recetaService.addIngredienteToReceta(recetaId, addIngredienteDto)));
    }

}
