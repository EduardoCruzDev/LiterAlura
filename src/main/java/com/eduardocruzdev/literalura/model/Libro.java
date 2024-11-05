package com.eduardocruzdev.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(@JsonAlias("id") Long identificador,
                         @JsonAlias("title") String titulo,
                         @JsonAlias("subjects") List<String> materias,
                         @JsonAlias("authors") List<Author> autores,
                         @JsonAlias("download_count") Integer descargas,
                         @JsonAlias("formats")Map<String,String> formatos){
}