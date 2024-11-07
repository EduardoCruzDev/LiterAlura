package com.eduardocruzdev.literalura.dto;

import com.eduardocruzdev.literalura.model.DatosLibro;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
    private Long id;
    private String nombre;
    private Integer anoNacimiento;
    private Integer anoFallecimiento;
    private List<DatosLibro> libros;

    public AuthorDTO(Long id, String nombre, Integer anoNacimiento, Integer anoFallecimiento, List<DatosLibro> libros) {
        this.id = id;
        this.nombre = nombre;
        this.anoNacimiento = anoNacimiento;
        this.anoFallecimiento = anoFallecimiento;
        this.libros = libros;
    }

    @Override
    public String toString() {
        return  "Id=" + id + "\n"+
                "Nombre=" + nombre ;

    }
}