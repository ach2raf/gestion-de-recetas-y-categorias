package com.salesianos.dam.gestion_de_recetas_y_categorias.utils;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.*;
import com.salesianos.dam.gestion_de_recetas_y_categorias.repository.CategoriaRepository;
import com.salesianos.dam.gestion_de_recetas_y_categorias.repository.IngredienteRepository;
import com.salesianos.dam.gestion_de_recetas_y_categorias.repository.RecetaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.seed", havingValue = "true")
public class DataSeed {

    private final CategoriaRepository categoriaRepository;
    private final IngredienteRepository ingredienteRepository;
    private final RecetaRepository recetaRepository;

    @PostConstruct
    public void init() {
        if (categoriaRepository.count() > 0) {
            return;
        }

        Categoria carnes = categoriaRepository.save(Categoria.builder()
                .nombre("Carnes")
                .descripcion("Recetas basadas en pollo, ternera, cerdo o cordero.")
                .build());
        Categoria postres = categoriaRepository.save(Categoria.builder()
                .nombre("Postres")
                .descripcion("Dulces, tartas y bizcochos.")
                .build());
        Categoria ensaladas = categoriaRepository.save(Categoria.builder()
                .nombre("Ensaladas")
                .descripcion("Platos frescos y ligeros.")
                .build());

        Ingrediente harina = ingredienteRepository.save(Ingrediente.builder().nombre("Harina").build());
        Ingrediente huevos = ingredienteRepository.save(Ingrediente.builder().nombre("Huevos").build());
        Ingrediente azucar = ingredienteRepository.save(Ingrediente.builder().nombre("Azúcar").build());
        Ingrediente carne = ingredienteRepository.save(Ingrediente.builder().nombre("Carne picada").build());
        Ingrediente lechuga = ingredienteRepository.save(Ingrediente.builder().nombre("Lechuga").build());
        Ingrediente tomate = ingredienteRepository.save(Ingrediente.builder().nombre("Tomate").build());

        Receta albondigas = Receta.builder()
                .nombre("Albóndigas de la abuela")
                .tiempoPreparacionMin(45)
                .dificultad(Dificultad.FACIL)
                .categoria(carnes)
                .build();
        anadir(albondigas, carne, 500, UnidadMedida.GRAMOS);
        anadir(albondigas, huevos, 2, UnidadMedida.UNIDAD);

        Receta bizcocho = Receta.builder()
                .nombre("Bizcocho casero")
                .tiempoPreparacionMin(50)
                .dificultad(Dificultad.MEDIA)
                .categoria(postres)
                .build();
        anadir(bizcocho, harina, 250, UnidadMedida.GRAMOS);
        anadir(bizcocho, huevos, 3, UnidadMedida.UNIDAD);
        anadir(bizcocho, azucar, 200, UnidadMedida.GRAMOS);

        Receta ensalada = Receta.builder()
                .nombre("Ensalada de tomate")
                .tiempoPreparacionMin(10)
                .dificultad(Dificultad.FACIL)
                .categoria(ensaladas)
                .build();
        anadir(ensalada, tomate, 3, UnidadMedida.UNIDAD);
        anadir(ensalada, lechuga, 1, UnidadMedida.UNIDAD);

        recetaRepository.save(albondigas);
        recetaRepository.save(bizcocho);
        recetaRepository.save(ensalada);
    }

    private void anadir(Receta receta, Ingrediente ingrediente, double cantidad, UnidadMedida unidad) {
        receta.getIngredientes().add(
                RecetaIngrediente.builder()
                        .receta(receta)
                        .ingrediente(ingrediente)
                        .cantidad(cantidad)
                        .unidad(unidad)
                        .build()
        );
    }
}