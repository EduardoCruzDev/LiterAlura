package com.eduardocruzdev.literalura.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "autores")
@Data
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String anioNacimiento;
    private String anioFallecimiento;

    @ManyToMany(mappedBy = "autores")
    private List<LibroR> libros;
}
