package br.edu.utfpr.td.tsi.gerenciador.documentos.persistencia.mysql;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.gerenciador.documentos.modelo.Documento;
import br.edu.utfpr.td.tsi.gerenciador.documentos.persistencia.DocumentoDAO;

@Component
public class MySqlDocumentoDAO implements DocumentoDAO {

	@Override
	public void persistir(Documento documento) {
		System.out.println("persistindo com MySql");

	}

	@Override
	public List<Documento> listarTodos() {
		System.out.println("listando com MySql");
		return null;
	}

	@Override
	public void remover(String id) {
		System.out.println("removendo com MySql");
	}

}
