package com.eduardocruzdev.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroRespuesta(@JsonAlias("count") Long total,
                             @JsonAlias("next") String siguiente,
                             @JsonAlias("previous") String anterior,
                             @JsonAlias("results") List<DatosLibro> libros) {
}
