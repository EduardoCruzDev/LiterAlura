package com.eduardocruzdev.literalura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public record Author( @JsonAlias("name") String nombre,
                       @JsonAlias("birth_year") Integer anoNacimiento,
                       @JsonAlias("death_year") Integer anoFallecimiento) {
}
