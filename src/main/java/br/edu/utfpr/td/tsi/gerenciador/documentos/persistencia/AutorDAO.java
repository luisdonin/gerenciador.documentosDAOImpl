package br.edu.utfpr.td.tsi.gerenciador.documentos.persistencia;

import br.edu.utfpr.td.tsi.gerenciador.documentos.modelo.Autor;

import java.util.List;

public interface AutorDAO {
    public void persistir(Autor autor);
    public void remover(String id);
    public List<Autor> listar();

}
