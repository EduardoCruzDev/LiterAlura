package com.eduardocruzdev.literalura.principal;


import com.eduardocruzdev.literalura.dto.AuthorDTO;
import com.eduardocruzdev.literalura.dto.LibroDTO;
import com.eduardocruzdev.literalura.model.DatosLibro;
import com.eduardocruzdev.literalura.model.LibroRespuesta;
import com.eduardocruzdev.literalura.repository.LibroRepository;
import com.eduardocruzdev.literalura.service.ConsumoAPI;
import com.eduardocruzdev.literalura.service.ConvierteDatos;


import com.eduardocruzdev.literalura.service.LibroService;

import org.springframework.stereotype.Component;



import java.util.List;

import java.util.Optional;
import java.util.Scanner;


@Component
public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();

    private List<DatosLibro> libroBuscado;


    private LibroRepository repositorio;
    private final LibroService servicio;

    // Constructor que acepta el servicio
    public Principal(LibroRepository repository, LibroService service) {
        this.repositorio = repository;
        this.servicio = service;
    }



    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar Libro Por Titulo
                    2 - Listar Libros Registrados
                    3 - Listar Autores por Nombre
                    4 - Listar Autores registrados
                    5 - Lista Autores vivos en un determinado año
                    6 - Lista Libros por idioma
     
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    buscarLibroRegistrado();
                    break;
                case 3:
                    buscarAutorRegistradoPorNombre();
                    break;
                case 4:
                    buscarAutorRegistrado();
                    break;
                case 5:
                    buscarAutorVivo();
                    break;
                case 6:
                    buscarLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }
    }



    private void getDatosLibro() {
        System.out.println("Escribe el nombre del Libro que deseas buscar:");
        var nombreLibro = sc.nextLine();

        // Obtener los datos de la API
        var json = consumoAPI.obtenerDatos(URL_BASE + "/?search=" + nombreLibro.replace(" ", "%20"));
        System.out.println("Respuesta JSON: " + json);

        // Convertir el JSON a un objeto LibroRespuesta
        LibroRespuesta datosRespuesta = conversor.obtenerDatos(json, LibroRespuesta.class);
        System.out.println("Datos de respuesta: " + datosRespuesta);

        // Guardar cada libro en el repositorio
        // Verifica que no hayan libros repetidos en la base de datos
        if (datosRespuesta.libros() != null && !datosRespuesta.libros().isEmpty()) {
            for (DatosLibro libro : datosRespuesta.libros()) {
                // Verificar si el libro ya existe
                Optional<DatosLibro> existente = repositorio.findByTitulo(libro.getTitulo());

                if (existente.isPresent()) {
                    System.out.println("El libro '" + libro.getTitulo() + "' ya existe en la base de datos.");
                } else {
                    repositorio.save(libro);
                    System.out.println("Libro guardado: " + libro.getTitulo());
                }
            }
        } else {
            System.out.println("No se encontraron libros con el nombre especificado.");
        }
    }





            private List<LibroDTO> buscarLibroPorIdioma () {
                return servicio.listarPorLenguaje();
            }

            private List<AuthorDTO> buscarAutorVivo () {
                    return servicio.buscarTodosAuthoresVivos();
            }

            private List<AuthorDTO> buscarAutorRegistrado () {

            return servicio.buscarTodosAuthores();

            }
            private void buscarLibro () {

                getDatosLibro();

            }

            private List<LibroDTO> buscarLibroRegistrado () {

                return servicio.listar();

            }

            private List<AuthorDTO> buscarAutorRegistradoPorNombre() {
                return servicio.listarAutor();
            }
    }






