package com.aluracursos.literalura.controller;

import com.aluracursos.literalura.model.Libros;
import com.aluracursos.literalura.service.AutorService;
import com.aluracursos.literalura.service.LibroService;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner lectura = new Scanner(System.in);
    private LibroService libroService;
    private AutorService autorService;

    public Principal(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                \n--- LITERALURA ---
                1- Buscar libro por título (API)
                2- Mostrar historial de libros buscados
                3- Listar autores registrados
                4- Listar autores vivos en determinado año
                5- Listar libros por idioma
                6- Top 10 Libros
                7- Estadísticas de descargas
                8- Buscar autor por nombre
                
                0- Salir
                Elija una opción:""";
            System.out.println(menu);

            try {
                opcion = Integer.parseInt(lectura.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> buscarLibro();
                case 2 -> mostrarHistorial();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivos();
                case 5 -> listarPorIdioma(); // <--- Nueva opción
                case 6 -> mostrarTop10();
                case 7 -> mostrarEstadisticas();
                case 8 -> buscarAutorPorNombre();

                case 0 -> System.out.println("Cerrando aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibro() {
        System.out.println("Ingrese el titulo del libro:");
        var titulo = lectura.nextLine();
        libroService.buscarYGuardarLibro(titulo);
        System.out.println("Operación realizada con éxito.");
    }

    private void mostrarHistorial() {
        var libros = libroService.obtenerTodosLosLibros();
        libros.forEach(System.out::println);
    }

    private void listarAutores() {
        var autores = autorService.obtenerAutores();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año:");
        try {
            var anio = Integer.parseInt(lectura.nextLine());
            var vivos = autorService.obtenerAutoresVivosEnAnio(anio);
            vivos.forEach(System.out::println);
        } catch (NumberFormatException e) {
            System.out.println("Año no válido.");
        }
    }

    private void listarPorIdioma() {
        System.out.println("""
            Ingrese el código del idioma para buscar:
            es - Español
            en - Inglés
            fr - Francés
            pt - Portugués
            """);
        var codigoIdioma = lectura.nextLine().toLowerCase();

        List<Libros> librosFiltrados = libroService.listarLibrosPorIdioma(codigoIdioma);
        if (librosFiltrados.isEmpty()) {
            System.out.println("\nNo hay libros registrados en el idioma: " + codigoIdioma);
        } else {
            long cantidad = librosFiltrados.stream().count();

            System.out.println("\n========================================");
            System.out.println("ESTADÍSTICAS POR IDIOMA");
            System.out.println("Idioma buscado: " + codigoIdioma.toUpperCase());
            System.out.println("Cantidad de libros en la BD: " + cantidad);
            System.out.println("========================================\n");

            System.out.println("Detalle de los libros encontrados:");
            librosFiltrados.forEach(System.out::println);

            var stats = librosFiltrados.stream()
                    .mapToDouble(Libros::getNumeroDescargas)
                    .summaryStatistics();

            System.out.println("Promedio de descargas en este idioma: " + String.format("%.2f", stats.getAverage()));
            System.out.println("Libro más descargado (máximo): " + stats.getMax());
        }
    }

    private void mostrarTop10() {
        System.out.println("\n--- TOP 10 LIBROS MÁS DESCARGADOS ---");
        var topLibros = libroService.obtenerTop10Libros();
        topLibros.forEach(l ->
                System.out.println(String.format("Libro: %s | Descargas: %.0f", l.getTitulo(), l.getNumeroDescargas()))
        );
    }

    private void mostrarEstadisticas() {
        System.out.println("\n--- ESTADÍSTICAS DE DESCARGAS EN LA BASE DE DATOS ---");
        var est = libroService.obtenerEstadisticasDescargas();
        System.out.println("Cantidad media de descargas: " + String.format("%.2f", est.getAverage()));
        System.out.println("Cantidad máxima de descargas: " + est.getMax());
        System.out.println("Cantidad mínima de descargas: " + est.getMin());
        System.out.println("Total de libros evaluados: " + est.getCount());
    }

    private void buscarAutorPorNombre() {
        System.out.println("Ingrese el nombre del autor que desea buscar:");
        var nombre = lectura.nextLine();
        var autores = libroService.buscarAutorPorNombre(nombre);

        if (autores.isEmpty()) {
            System.out.println("No se encontró ningún autor con ese nombre en la base de datos.");
        } else {
            autores.forEach(System.out::println);
        }
    }
}