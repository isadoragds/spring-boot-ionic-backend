package com.nelioalves;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.domain.Categoria;
import com.nelioalves.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}


	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Casa");
		Categoria cat2 = new Categoria(null, "Sala");

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}
}