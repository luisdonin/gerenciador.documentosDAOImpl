package br.edu.utfpr.td.tsi.gerenciador.documentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({ "file:./application.properties" })
@ImportResource({ "file:./applicationContext.xml" })
public class Main {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/gerenciador.documentos");
		SpringApplication.run(Main.class, args);
	}
}
