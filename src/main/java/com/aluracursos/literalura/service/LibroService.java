package com.aluracursos.literalura.service;


import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private ConsumoAPI consumoAPI;
    @Autowired
    private ConvierteDatos conversor;

    private final String URL_BASE = "https://gutendex.com/books/";

    public void buscarYGuardarLibro(String titulo) {
        var url = URL_BASE + "?search=" + titulo.replace(" ", "%20");
        var json = consumoAPI.obtenerDatos(url);
        var datos = conversor.obtenerDatos(json, Datos.class);

        if (datos.resultados() != null && !datos.resultados().isEmpty()) {
            DatosLibros primerLibro = datos.resultados().get(0);
            String nombreAutor = primerLibro.autor().get(0).nombre();
            List<Autor> autoresEncontrados = autorRepository.findByNombreContainsIgnoreCase(nombreAutor);

            Autor autor;
            if (!autoresEncontrados.isEmpty()) {
               autor = autoresEncontrados.get(0);
            } else {
                autor = autorRepository.save(new Autor(primerLibro.autor().get(0)));
            }

            Libros libro = new Libros(primerLibro, autor);
            libroRepository.save(libro);
        }
    }

    public List<Libros> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public List<Libros> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdiomas(idioma);
    }

    public List<Libros> obtenerTop10Libros() {
        return libroRepository.findTop10ByOrderByNumeroDescargasDesc();
    }

    public DoubleSummaryStatistics obtenerEstadisticasDescargas() {
        List<Libros> todosLosLibros = libroRepository.findAll();
        return todosLosLibros.stream()
                .filter(l -> l.getNumeroDescargas() > 0)
                .mapToDouble(Libros::getNumeroDescargas)
                .summaryStatistics();
    }

    public List<Autor> buscarAutorPorNombre(String nombre) {
        return autorRepository.findByNombreContainsIgnoreCase(nombre);
    }
}