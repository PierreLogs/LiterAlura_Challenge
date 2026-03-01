package com.aluracursos.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name="libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private String idiomas;
    private Double numeroDescargas;

    @ManyToOne
    @JoinColumn(name="autor_id")
    private Autor autor;

    public Libros(){}

    public Libros(DatosLibros datosLibros, Autor autor) {
        this.titulo = datosLibros.titulo();
        this.idiomas = datosLibros.idiomas().get(0); // Tomamos el primero
        this.numeroDescargas = datosLibros.numeroDescargas();
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return String.format("""
                ----- LIBRO -----
                Título: %s
                Autor: %s
                Idioma: %s
                Descargas: %.1f
                -----------------""", titulo,
                (autor != null ? autor.getNombre() : "Anónimo"),
                idiomas, numeroDescargas);
    }
}