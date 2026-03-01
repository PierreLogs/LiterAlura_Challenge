package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libros, Long> {
    List<Libros> findByIdiomas(String idioma);
    List<Libros> findTop10ByOrderByNumeroDescargasDesc();
}