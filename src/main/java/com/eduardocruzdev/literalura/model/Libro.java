package com.eduardocruzdev.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Libro(   Long id,
        @JsonAlias("id") Long codigoLibro,
        @JsonAlias("title") String titulo,
        @JsonAlias("subjects") List<String> materias,
        @JsonAlias("authors") List<Author> autores,
        @JsonAlias("download_count") Integer descargas,
        @JsonAlias("languages") List<String> lenguajes,
        @JsonAlias("formats") Map<String, String> formatos) {
}