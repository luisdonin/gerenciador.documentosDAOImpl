package br.edu.utfpr.td.tsi.gerenciador.documentos.modelo;

import java.time.LocalDate;

public class Documento {

	private String id;
	private LocalDate dataCriacao;
	private String nome;
	private String descricao;
	private Autor autor;
	private String conteudo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public String toString() {
		return "Documento [id=" + id + ", dataCriacao=" + dataCriacao + ", nome=" + nome + ", descricao=" + descricao + ", autor=" + autor + ", conteudo=" + conteudo + "]";
	}

}
