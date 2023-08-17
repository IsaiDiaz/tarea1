package com.example.tarea1.dao.repositories;

import com.example.tarea1.dao.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
