package com.alura.literatura.principal;

import com.alura.literatura.client.GutendexClient;
import dto.DatosAutor;
import dto.DatosLibro;
import dto.DatosRespuesta;
import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);

    private GutendexClient client = new GutendexClient();
    private ObjectMapper mapper = new ObjectMapper();

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void mostrarMenu() {

        int opcion = -1;

        while (opcion != 6) {

            System.out.println("""
                    
                    ---- MENU ----
                    
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un año
                    5 - Listar libros por idioma
                    6 - Salir
                    
                    """);

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:
                    buscarLibro();
                    break;

                case 2:
                    listarLibros();
                    break;

                case 3:
                    listarAutores();
                    break;

                case 4:
                    listarAutoresVivos();
                    break;

                case 5:
                    listarLibrosPorIdioma();
                    break;

                case 6:
                    System.out.println("Cerrando aplicación...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibro() {

        try {

            System.out.println("Escribe el nombre del libro:");
            String titulo = teclado.nextLine();

            String json = client.buscarLibro(titulo);

            DatosRespuesta respuesta =
                    mapper.readValue(json, DatosRespuesta.class);

            DatosLibro datosLibro = respuesta.resultados().get(0);
            DatosAutor datosAutor = datosLibro.autores().get(0);

            Autor autor = new Autor(
                    datosAutor.nombre(),
                    datosAutor.anioNacimiento(),
                    datosAutor.anioFallecimiento()
            );

            autorRepository.save(autor);

            Libro libro = new Libro(
                    datosLibro.titulo(),
                    datosLibro.idiomas().get(0),
                    datosLibro.numeroDescargas(),
                    autor
            );

            libroRepository.save(libro);

            System.out.println("Libro guardado correctamente");

        } catch (Exception e) {
            System.out.println("No se encontró el libro");
        }
    }

    private void listarLibros() {

        libroRepository.findAll()
                .forEach(libro ->
                        System.out.println(libro.getTitulo()));
    }

    private void listarAutores() {

        autorRepository.findAll()
                .forEach(autor ->
                        System.out.println(autor.getNombre()));
    }

    private void listarAutoresVivos() {

        System.out.println("Ingrese el año:");
        int anio = teclado.nextInt();

        autorRepository.findAll()
                .stream()
                .filter(a -> a.getNacimiento() <= anio)
                .filter(a -> a.getFallecimiento() == null || a.getFallecimiento() >= anio)
                .forEach(a -> System.out.println(a.getNombre()));
    }

    private void listarLibrosPorIdioma() {

        System.out.println("Ingrese el idioma (ej: en, es, fr):");
        String idioma = teclado.nextLine();

        libroRepository.findByIdioma(idioma)
                .forEach(l -> System.out.println(l.getTitulo()));
    }
}