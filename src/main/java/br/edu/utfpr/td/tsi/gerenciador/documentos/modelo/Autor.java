package br.edu.utfpr.td.tsi.gerenciador.documentos.modelo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Autor {

	private String id;
	private String nome;
	private String email;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + ", email=" + email + ", dataNascimento=" + dataNascimento + "]";
	}

}
