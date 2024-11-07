package com.eduardocruzdev.literalura.dto;


import com.eduardocruzdev.literalura.model.DatosAuthor;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record LibroDTO(Long id,
                       Long codigoLibro,
                       String titulo,
                       List<String> materias,
                       List<DatosAuthor> autores,
                       Integer descargas,
                       List<String> lenguajes,
                       Map<String, String> formatos) {

    @Override
    public String toString() {
        String autoresFormat = (autores != null && !autores.isEmpty())
                ? autores.stream().map(DatosAuthor::getNombre).collect(Collectors.joining(", "))
                : "Sin autores";

        return  "ID: " + id + "\n"+
                ", Título: '" + titulo + '\'' + "\n"+
                ", Autores: [" + autoresFormat + "]" +"\n"+
                ", Descargas: " + (descargas != null ? descargas : "0") +"\n"+
                "********************************************************\n";
    }
}
