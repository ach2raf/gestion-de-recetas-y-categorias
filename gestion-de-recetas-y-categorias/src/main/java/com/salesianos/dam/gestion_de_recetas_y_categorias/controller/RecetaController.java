package com.salesianos.dam.gestion_de_recetas_y_categorias.controller;

import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.EditCategoriaDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.EditRecetaDto;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.GetCategoriaDetail;
import com.salesianos.dam.gestion_de_recetas_y_categorias.dto.GetRecetaDetail;
import com.salesianos.dam.gestion_de_recetas_y_categorias.service.RecetaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
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
            @Valid @RequestBody EditRecetaDto receta
    ){
        return ResponseEntity.status(201)
                .body(GetRecetaDetail.of(recetaService.addReceta(receta)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRecetaDetail> getRecetaById(@PathVariable @Min(value=1) Long id){
        return ResponseEntity.ok(GetRecetaDetail.of(recetaService.getRecetaById(id)));
    }
}
