package br.edu.utfpr.td.tsi.gerenciador.documentos.controle;

import java.util.List;

import br.edu.utfpr.td.tsi.gerenciador.documentos.modelo.Autor;
import br.edu.utfpr.td.tsi.gerenciador.documentos.persistencia.AutorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.utfpr.td.tsi.gerenciador.documentos.modelo.Documento;
import br.edu.utfpr.td.tsi.gerenciador.documentos.persistencia.DocumentoDAO;

@Controller
public class DocumentoController {

    @Qualifier("mongoDBDocumentoDAO")
    @Autowired
	private DocumentoDAO documentoDAO;
	@Autowired
	private AutorDAO autorDAO;

	@GetMapping(value = "/cadastrarDocumento")
	public String exibirPaginaCadastrarDocumento(Model model) {
		List<Autor> autores = autorDAO.listar();
		model.addAttribute("autores", autores);
		return "cadastrarDocumento";
	}

	@PostMapping(value = "/cadastrarDocumento")
	public String cadastrarDocumento(Documento documento) {

		documentoDAO.persistir(documento);
		return "index";
	}

	@GetMapping(value = "/listarDocumentos")
	public String exibirPaginaListarDocumentos(Model model) {
		List<Documento> documentos = documentoDAO.listarTodos();
		model.addAttribute("documentos", documentos);
		return "listarDocumentos";
	}

	@GetMapping(value = "/removerDocumento")
	public String removerDocumentos(@RequestParam String idDocumento) {
		System.out.println("removendo documento de id " + idDocumento);
		documentoDAO.remover(idDocumento);
		return "index";
	}
}
