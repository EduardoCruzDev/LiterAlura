package com.eduardocruzdev.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "libros")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonProperty("id")
    @Column(unique = true)
    private Long codigoLibro;

    @JsonAlias("title")
    private String titulo;


    @JsonAlias("subjects")
    @ElementCollection // Para almacenar la lista de materias
    private List<String> materias;

    @JsonAlias("authors")
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(
            name = "libros_autores", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "libro_id"), // Columna de la tabla libro
            inverseJoinColumns = @JoinColumn(name = "autor_id") // Columna de la tabla autor
    )
    private List<DatosAuthor> autores; // Relación con autores

    @JsonAlias("download_count")
    private Integer descargas;

    @JsonAlias("languages")
    @ElementCollection
    private List<String> lenguajes;

    @ElementCollection // Para almacenar formatos como pares clave-valor
    @MapKeyColumn(name = "formato") // Nombre de la columna para las claves del mapa. No utilizado por el momento
    @Column(name = "url") // Nombre de la columna para los valores del mapa
    private Map<String, String> formatos; // Mapa de formatos

    // Constructor que permite inicializar desde un record Libro
    public DatosLibro(Libro libro) {

        this.codigoLibro = libro.codigoLibro();
        this.titulo = libro.titulo();
        this.materias = libro.materias();
        this.autores = libro.autores().stream()
                .map(DatosAuthor::new) // Convertir Author a DatosAuthor
                .toList();
        this.descargas = libro.descargas();
        this.lenguajes = libro.lenguajes();
        this.formatos = libro.formatos();
    }
}