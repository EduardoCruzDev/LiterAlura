package com.eduardocruzdev.literalura.service;


import com.eduardocruzdev.literalura.dto.AuthorDTO;
import com.eduardocruzdev.literalura.dto.LibroDTO;
import com.eduardocruzdev.literalura.model.DatosAuthor;
import com.eduardocruzdev.literalura.model.DatosLibro;
import com.eduardocruzdev.literalura.model.Lenguajes;
import com.eduardocruzdev.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;


    @Transactional
    public List<LibroDTO> listar() {
        // Obtener todos los libros de la base de datos
        List<DatosLibro> datosLibros = libroRepository.findAll();

        // Convertir a DTO
        List<LibroDTO> libros = convierteDatos(datosLibros);

        // Imprimir la lista de libros DTO
        System.out.println("Lista de libros: " + libros);

        // Retornar la lista de libros DTO
        return libros;
    }

    @Transactional
    public List<LibroDTO> listarPorLenguaje() {
        // Mostrar mensaje de entrada al usuario
        System.out.println("Ingresa el idioma en el que quieres buscar (ej. Español, Inglés, etc.):");

        Scanner sc = new Scanner(System.in);
        String lenguaje = sc.nextLine().trim();  // Obtener la entrada del usuario y eliminar espacios al principio y final

        // Normalizar la entrada del usuario para eliminar acentos
        String lenguajeNormalizado = normalizarString(lenguaje);

        // Buscar el idioma correspondiente en el enum
        Lenguajes idiomaSeleccionado = null;

        for (Lenguajes lenguajeEnum : Lenguajes.values()) {
            // Normalizamos los nombres de los idiomas en el enum y comparamos sin tildes
            if (normalizarString(lenguajeEnum.getNombre()).equalsIgnoreCase(lenguajeNormalizado)) {
                idiomaSeleccionado = lenguajeEnum;
                break;
            }
        }

        if (idiomaSeleccionado == null) {
            // Si no se encuentra el idioma, mostrar un mensaje de error
            System.out.println("Idioma no válido. Por favor ingresa un valor correcto.");
            return List.of();  // Retornar una lista vacía si el idioma no es válido
        }

        // Usar el acrónimo del idioma seleccionado para buscar los libros
        List<DatosLibro> datosLibros = libroRepository.findDatosLibroByLenguajes(idiomaSeleccionado.getAcronimo());

        // Convertir los datos de los libros a DTO
        List<LibroDTO> libros = convierteDatos(datosLibros);

        // Mostrar la lista de libros
        System.out.println("Lista de libros en: " + idiomaSeleccionado.getNombre() + " " + libros);

        // Retornar la lista de libros DTO
        return libros;
    }

    // Método para normalizar cadenas y eliminar tildes
    private String normalizarString(String input) {
        // Normaliza la cadena (convierte caracteres acentuados a caracteres sin acento)
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Elimina los caracteres de acento (diacríticos)
        normalized = normalized.replaceAll("[^\\p{ASCII}]", "");
        return normalized;
    }

    @Transactional
    public List<AuthorDTO> buscarTodosAuthores(){

        List<DatosAuthor> datosAuthors = libroRepository.findAllAuthors();

        // Filtrar duplicados por el campo 'nombre'
        Map<String, DatosAuthor> uniqueAuthorsMap = new LinkedHashMap<>();
        for (DatosAuthor author : datosAuthors) {
            uniqueAuthorsMap.put(author.getNombre(), author);  // El 'nombre' es la clave
        }

        // Convertir la lista filtrada a DTO
        List<AuthorDTO> authorDTOS = convierteDatosAuthor(new ArrayList<>(uniqueAuthorsMap.values()));

        authorDTOS.forEach(System.out::println);
        return authorDTOS;
    }

    @Transactional
    public List<AuthorDTO> buscarTodosAuthoresVivos() {
        // Solicitar el año de fallecimiento desde la consola
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un año para consultar los autores vivos: ");
        int deathActual = scanner.nextInt();  // Capturamos el año como un entero


        // Obtener todos los autores
        List<DatosAuthor> datosAuthors = libroRepository.findAllAuthors();

        // Filtrar autores cuyo año de fallecimiento coincida con el ingresado
        List<DatosAuthor> filteredAuthors = new ArrayList<>();
        for (DatosAuthor author : datosAuthors) {
            if (author.getAnoFallecimiento() != null && author.getAnoFallecimiento() >= deathActual && author.getAnoNacimiento()<= deathActual) {
                filteredAuthors.add(author);
            }
        }

        // Filtrar duplicados por el campo 'nombre'
        Map<String, DatosAuthor> uniqueAuthorsMap = new LinkedHashMap<>();
        for (DatosAuthor author : filteredAuthors) {
            uniqueAuthorsMap.put(author.getNombre(), author);  // El 'nombre' es la clave
        }

        // Convertir la lista filtrada a DTO
        List<AuthorDTO> authorDTOS = convierteDatosAuthor(new ArrayList<>(uniqueAuthorsMap.values()));

        // Mostrar los DTOs en consola para verificación (opcional)
        authorDTOS.forEach(System.out::println);

        // Retornar los resultados filtrados
        return authorDTOS;
    }

    @Transactional
    public List<AuthorDTO> listarAutor() {
        // Obtener el nombre del autor a buscar
        System.out.println("Indica el Autor a buscar");
        Scanner sc = new Scanner(System.in);
        String autorNombre = sc.nextLine().trim();

        // Buscar el autor por nombre
        List<DatosAuthor> datosAuthors = libroRepository.findAuthorByName(autorNombre);

        List<AuthorDTO> authorDTOs = convierteDatosAuthor(datosAuthors);

        // Imprimir la información del autor DTO
        if (!authorDTOs.isEmpty()) {
            System.out.println("Autores encontrados: ");
            for (AuthorDTO authorDTO : authorDTOs) {
                System.out.println(authorDTO);
                // Mostrar los libros del autor
                System.out.println("Libros de " + authorDTO.getNombre() + ":");
                if (authorDTO.getLibros() != null && !authorDTO.getLibros().isEmpty()) {
                    for (DatosLibro libro : authorDTO.getLibros()) {
                        System.out.println("- " + libro.getTitulo()); // Asegúrate de que DatosLibro tiene el método getTitulo()
                    }
                } else {
                    System.out.println("No hay libros disponibles para este autor.");
                }
            }
        } else {
            System.out.println("Autor no encontrado.");
        }

        return authorDTOs;
    }

    public List<LibroDTO> convierteDatos(List<DatosLibro> libro){
        return libro.stream()
                .map(l -> new LibroDTO(l.getId(),l.getCodigoLibro(), l.getTitulo(), l.getMaterias(), l.getAutores(), l.getDescargas(),l.getLenguajes(),l.getFormatos()))
                .collect(Collectors.toList());
    }

    public List<AuthorDTO> convierteDatosAuthor(List<DatosAuthor> author){
        return author.stream()
                .map(a -> new AuthorDTO(a.getId(), a.getNombre(), a.getAnoNacimiento(), a.getAnoFallecimiento(),a.getLibros()))
                .collect(Collectors.toList());
    }

    

}

