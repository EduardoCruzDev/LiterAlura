package com.eduardocruzdev.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "autores")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Ignorar propiedades desconocidas durante la deserialización
public class DatosAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    @Column(name = "birth_year")
    private Integer anoNacimiento;

    @JsonAlias("death_year")
    @Column(name = "death_year")
    private Integer anoFallecimiento;

    // Relación many-to-many con DatosLibro

    @ManyToMany(mappedBy = "autores")
    private List<DatosLibro> libros;

    // Constructor que permite inicializar desde un record Author
    public DatosAuthor(Author author) {
        this.nombre = author.nombre();
        this.anoNacimiento = author.anoNacimiento();
        this.anoFallecimiento = author.anoFallecimiento();
    }
}