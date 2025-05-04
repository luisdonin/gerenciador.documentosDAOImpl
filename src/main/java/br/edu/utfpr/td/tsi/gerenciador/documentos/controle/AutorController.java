package br.edu.utfpr.td.tsi.gerenciador.documentos.controle;

import br.edu.utfpr.td.tsi.gerenciador.documentos.modelo.Autor;
import br.edu.utfpr.td.tsi.gerenciador.documentos.persistencia.AutorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AutorController {

    @Autowired
    private AutorDAO autorDAO;

    @GetMapping(value = "/cadastrarAutor")
    public String exibirPagCadastrarAutor() {return "cadastrarAutor";}

    /*
    * 04/05/2025
    * Implementação do botão post para cadastrar o autor
    * está cadastrando mas todos os campos sao null
    * problema resolvido, quando salvando os dados no formulário, era como se eu estivesse importando de outra coleção
    * */

    @PostMapping(value = "/cadastrarAutor")
    public String exibirPagCadastrarAutor(Autor autor) {
        System.out.println("Autor: " + autor);
        autorDAO.persistir(autor);
        return "index";
    }



}
