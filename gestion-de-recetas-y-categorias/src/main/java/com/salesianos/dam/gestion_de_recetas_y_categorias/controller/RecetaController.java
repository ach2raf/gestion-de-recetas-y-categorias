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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Recetas", description = "Gestión de recetas y de sus ingredientes")
public class RecetaController {
    private final RecetaService recetaService;

    @Operation(summary = "Crear una receta", description = "Requiere el id de una categoría existente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Receta creada"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos, nombre repetido o tiempo de preparación menor o igual a 0"),
            @ApiResponse(responseCode = "404", description = "La categoría indicada no existe"),
            @ApiResponse(responseCode = "409", description = "Ya existe una receta con ese nombre")
    })
    @PostMapping
    public ResponseEntity<GetRecetaDetail> addReceta(
            @Valid @RequestBody EditRecetaDto receta) {
        return ResponseEntity.status(201)
                .body(GetRecetaDetail.of(recetaService.addReceta(receta)));
    }



    @Operation(summary = "Obtener una receta con sus ingredientes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receta encontrada, incluida la lista de ingredientes"),
            @ApiResponse(responseCode = "400", description = "Id no válido"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetRecetaDetail> getRecetaById(@PathVariable @Min(value = 1) Long id) {
        return ResponseEntity.ok(GetRecetaDetail.of(recetaService.getRecetaById(id)));
    }


    @Operation(summary = "Actualizar una receta")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receta actualizada"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos o tiempo de preparación menor o igual a 0"),
            @ApiResponse(responseCode = "404", description = "Receta o categoría no encontrada"),
            @ApiResponse(responseCode = "409", description = "Ya existe otra receta con ese nombre")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetRecetaDetail> updateReceta(@PathVariable Long id, @RequestBody EditRecetaDto receta) {
        return ResponseEntity.ok(
                GetRecetaDetail.of(recetaService.updateReceta(id, receta)));
    }


    @Operation(summary = "Listar todas las recetas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de recetas"),
            @ApiResponse(responseCode = "404", description = "No hay recetas")
    })
    @GetMapping
    public ResponseEntity<List<GetRecetaList>> getAllRecetas() {
        List<Receta> result = recetaService.getAllRecetas();
        return ResponseEntity.ok(
                result
                        .stream()
                        .map(GetRecetaList::of)
                        .toList());
    }



    @Operation(summary = "Borrar una receta")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Receta borrada"),
            @ApiResponse(responseCode = "400", description = "Id no válido"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Long id) {
        recetaService.deleteReceta(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Añadir un ingrediente a una receta",
            description = "Indica el ingrediente, la cantidad y la unidad de medida (ej.: 200 GRAMOS, 3 CUCHARADA_SOPERA)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ingrediente añadido; devuelve la receta actualizada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "404", description = "Receta o ingrediente no encontrado"),
            @ApiResponse(responseCode = "409", description = "El ingrediente ya está añadido a la receta")
    })
    @PostMapping("/{recetaId}/ingredientes")
    public ResponseEntity<GetRecetaDetail> addIngredienteToReceta(
            @PathVariable Long recetaId,
            @Valid @RequestBody AddIngredienteDto addIngredienteDto) {
        return ResponseEntity.status(201)
                .body(GetRecetaDetail.of(recetaService.addIngredienteToReceta(recetaId, addIngredienteDto)));
    }

}
