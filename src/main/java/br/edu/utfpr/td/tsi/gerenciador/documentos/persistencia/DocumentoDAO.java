package br.edu.utfpr.td.tsi.gerenciador.documentos.persistencia;

import java.util.List;

import br.edu.utfpr.td.tsi.gerenciador.documentos.modelo.Documento;

public interface DocumentoDAO {

	 void persistir(Documento documento);

	 List<Documento> listarTodos();

	 void remover(String id);

}
