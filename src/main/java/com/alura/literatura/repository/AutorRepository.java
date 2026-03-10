package com.alura.literatura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.literatura.model.Autor;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(
            Integer nacimiento,
            Integer fallecimiento
    );
}