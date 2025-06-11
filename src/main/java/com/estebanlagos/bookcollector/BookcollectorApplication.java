package com.estebanlagos.bookcollector;

import main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.BookApiClient;

@SpringBootApplication
public class BookcollectorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookcollectorApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showMenu();
	}
}
