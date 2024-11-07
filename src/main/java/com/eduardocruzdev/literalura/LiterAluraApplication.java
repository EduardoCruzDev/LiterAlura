package com.eduardocruzdev.literalura;

import com.eduardocruzdev.literalura.principal.Principal;
import com.eduardocruzdev.literalura.repository.LibroRepository;
import com.eduardocruzdev.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
    @Autowired
    private LibroRepository repository;
    @Autowired
    private LibroService servicio;

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repository,servicio);
        principal.mostrarMenu();

    }
}
