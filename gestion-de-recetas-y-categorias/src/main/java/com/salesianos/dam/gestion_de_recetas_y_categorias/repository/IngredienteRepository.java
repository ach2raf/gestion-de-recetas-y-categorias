package com.salesianos.dam.gestion_de_recetas_y_categorias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Ingrediente;

@Repository 
public interface IngredienteRepository extends JpaRepository<Ingrediente,Long>{

}
