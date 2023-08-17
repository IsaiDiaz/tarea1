package com.example.tarea1.dao.repositories;

import com.example.tarea1.dao.entities.Categoria;
import com.example.tarea1.dao.entities.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {
    List<Imagen> findByCategoria(Categoria categoria);
}
