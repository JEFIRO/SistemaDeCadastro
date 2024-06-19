package com.jefiro.cadastro;

import com.jefiro.conect.SQL;
import com.jefiro.email.Email;
import com.jefiro.modelos.Usuario;
import com.jefiro.validacao.ValidaTempo;
import com.jefiro.validacao.ValidarCpf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SistemaDeCadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeCadastroApplication.class, args);
		Scanner sc = new Scanner(System.in);
		Usuario usuario = new Usuario();
		ValidarCpf validarCpf = new ValidarCpf();
		ValidaTempo tempo = new ValidaTempo();
		Email emailMain = new Email();
		SQL sql = new SQL("cadadstroUsuario", "jefiro", "admin");
		System.out.println("Bem vindo");
		while (true){
			System.out.println("Adicione seu nome");
			var nome = sc.nextLine();
			System.out.println("Adicione sua senha");
			var senha = sc.nextLine();
			System.out.println("Adicione seu email");
			var email = sc.nextLine();
			System.out.println("Adicione seu telefone");
			var telefone = sc.nextLine();
			System.out.println("Adicione seu cpf");
			var cpf = sc.nextLine();
			System.out.println("Adicione sua data de nascimento");
			var data = sc.nextLine();
			System.out.println("==============================");

			usuario.setNome(nome);
			usuario.setSenha(senha);

			usuario.setTelefone(telefone);
			usuario.setAniversario(data);

			if (validarCpf.ValidarCpf(cpf)){
				usuario.setCpf(cpf);
			}else {
				System.out.println("CPF invalido, tente novamente");
			}

			System.out.println("verifique seu email, você ira receber um codigo de 6 digitos.Validos por 15 minutos");
			emailMain.enviarCodigo(email);
			System.out.println("insira o codigo enviado para o seu email: ");
			var codigo = sc.nextLine();
			var verificar = emailMain.verificarCodigo(codigo);
			if (verificar){
				System.out.println("codigo verificado com sucesso");
				System.out.println("Sua conta já esta pronta para uso");
				usuario.setGmail(email);

				var query = "INSERT INTO usuarios (idUsuario, nome, senha, email, telefone, cpf, aniversario)";
				var values = String.format("VALUES (%s, '%s', '%s', '%s', '%s','%s', '%s')"
						,usuario.getIdUsuario(),nome,senha,email,telefone,cpf,data);
				sql.conectar();
				sql.insert(query+values);
				sql.close();
				System.out.println("ok");
			}else {
				System.out.println("tennte novamente");
			}
		}
	}
}
