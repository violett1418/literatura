package com.alura.literatura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.literatura.model.Libro;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByIdioma(String idioma);
}