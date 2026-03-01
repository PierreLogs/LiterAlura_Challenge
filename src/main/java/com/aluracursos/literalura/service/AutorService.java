/*package com.aluracursos.literalura.service;

import com.aluracursos.literalura.model.Autor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AutorService {

    // Simulamos la base de datos con una lista en memoria
    private List<Autor> autoresMemoria = new ArrayList<>();

    public void guardar(Autor autor) {
        autoresMemoria.add(autor);
    }

    public List<Autor> obtenerAutores() {
        return autoresMemoria;
    }

    public List<Autor> obtenerAutoresVivosEnAnio(int anio) {
        // Implementamos la lógica de filtrado manualmente con Streams
        return autoresMemoria.stream()
                .filter(a -> {
                    // Aquí asumo que tus métodos en Autor son getAnioNacimiento y getAnioFallecimiento
                    // Ajusta los nombres según tu clase Autor
                    Integer nac = a.getAnioNacimiento();
                    Integer fall = a.getAnioFallecimiento();
                    return nac != null && nac <= anio && (fall == null || fall >= anio);
                })
                .collect(Collectors.toList());
    }
}*/

package com.aluracursos.literalura.service;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutorService {
    private List<Autor> autoresMemoria = new ArrayList<>();
    @Autowired
    private AutorRepository autorRepository;

    public void guardar(Autor autor) {
        boolean existe = autoresMemoria.stream()
                .anyMatch(a -> a.getNombre().equalsIgnoreCase(autor.getNombre()));
        if (!existe) {
            autoresMemoria.add(autor);
        }
    }

    public List<Autor> obtenerAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> obtenerAutoresVivosEnAnio(int anio) {
        return autorRepository.findAutoresVivosEnDeterminadoAnio(anio);
    }
}