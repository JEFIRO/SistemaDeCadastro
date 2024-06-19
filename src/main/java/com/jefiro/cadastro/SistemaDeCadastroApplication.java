package com.jefiro.cadastro;

import com.jefiro.email.Email;
import com.jefiro.modelos.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SistemaDeCadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeCadastroApplication.class, args);
		Scanner sc = new Scanner(System.in);
		Usuario usuario = new Usuario();
		System.out.println("Bem vindo");




	}
}
