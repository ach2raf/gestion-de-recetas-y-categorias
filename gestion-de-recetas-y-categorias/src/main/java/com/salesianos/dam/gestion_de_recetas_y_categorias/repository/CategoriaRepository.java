package com.salesianos.dam.gestion_de_recetas_y_categorias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
