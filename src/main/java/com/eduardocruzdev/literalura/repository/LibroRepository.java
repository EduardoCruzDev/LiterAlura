package com.eduardocruzdev.literalura.repository;


import com.eduardocruzdev.literalura.model.DatosAuthor;
import com.eduardocruzdev.literalura.model.DatosLibro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<DatosLibro, Long> {

    List<DatosLibro> findAll();

    Optional<DatosLibro> findByTitulo(String titulo);

    List<DatosLibro> findDatosLibroByLenguajes(String lenguaje);


    @Query("SELECT a FROM DatosAuthor a")
    List<DatosAuthor> findAllAuthors();

    @Query("SELECT a FROM DatosAuthor a WHERE a.nombre = :nombre")
    List<DatosAuthor> findAuthorByName(String nombre);


}
