package com.salesianos.dam.gestion_de_recetas_y_categorias.repository;

import com.salesianos.dam.gestion_de_recetas_y_categorias.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta,Long> {

    boolean existsByName(String name);

}
