package com.salesianos.dam.gestion_de_recetas_y_categorias.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receta {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private int tiempoPreparacionMin;

    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;

    @ManyToOne
    private Categoria categoria;

    @ManyToMany
    @JoinTable(name = "receta_ingrediente", joinColumns = @JoinColumn(name = "receta_id"), inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
    @Builder.Default
    private List<Ingrediente> ingredientes = new ArrayList<>();
}
