package com.alura.literatura;

import com.alura.literatura.principal.Principal;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LiteraturaApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =
				SpringApplication.run(LiteraturaApplication.class, args);

		LibroRepository libroRepository = context.getBean(LibroRepository.class);
		AutorRepository autorRepository = context.getBean(AutorRepository.class);

		Principal principal = new Principal(libroRepository, autorRepository);
		principal.mostrarMenu();
	}
}