package br.edu.utfpr.td.tsi.gerenciador.documentos.persistencia.mongoDB;

import static com.mongodb.client.model.Filters.eq;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.edu.utfpr.td.tsi.gerenciador.documentos.modelo.Autor;
import br.edu.utfpr.td.tsi.gerenciador.documentos.modelo.Documento;
import br.edu.utfpr.td.tsi.gerenciador.documentos.persistencia.DocumentoDAO;

@Component
public class MongoDBDocumentoDAO implements DocumentoDAO {

	@Value("${spring.data.mongodb.uri}")
	private String connectionString;

	@Value("${spring.data.mongodb.database}")
	private String databaseName;

	@Override
	public void persistir(Documento documento) {

		documento.setId(UUID.randomUUID().toString());

		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			MongoCollection<Document> collection = database.getCollection("documento");

			Document document = new Document();
			document.append("_id", documento.getId());
			document.append("nome", documento.getNome());
			document.append("descricao", documento.getDescricao());
			document.append("conteudo", documento.getConteudo());

			Document documentAutor = new Document();
			Autor autor = documento.getAutor();
			autor.setId(UUID.randomUUID().toString());
			documentAutor.append("_id", autor.getId());
			documentAutor.append("nome", autor.getNome());
			documentAutor.append("email", autor.getEmail());
			documentAutor.append("dataNascimento", autor.getDataNascimento());

			document.append("autor", documentAutor);

			collection.insertOne(document);
		}
	}

	@Override

	public List<Documento> listarTodos() {
		ArrayList<Documento> documentos = new ArrayList<>();

		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			MongoCollection<Document> collection = database.getCollection("documento");

			FindIterable<Document> documentosEncontrados = collection.find();
			for (Document document : documentosEncontrados) {
				String id = document.getString("_id");
				String nome = document.getString("nome");
				String descricao = document.getString("descricao");
				String conteudo = document.getString("conteudo");

				Documento documento = new Documento();
				documento.setId(id);
				documento.setNome(nome);
				documento.setDescricao(descricao);
				documento.setConteudo(conteudo);

				Document documentAutor = (Document) document.get("autor");
				if (documentAutor != null) {
					String idAutor = documentAutor.getString("_id");
					String nomeAutor = documentAutor.getString("nome");
					String emailAutor = documentAutor.getString("email");
					Date dataNascimentoAutor = documentAutor.getDate("dataNascimento");

					Autor autor = new Autor();
					autor.setId(idAutor);
					autor.setNome(nomeAutor);
					autor.setEmail(emailAutor);

					if (dataNascimentoAutor != null) {
						LocalDate localDate = LocalDate.ofInstant(dataNascimentoAutor.toInstant(), ZoneId.systemDefault());
						autor.setDataNascimento(localDate);
					}

					documento.setAutor(autor);
				}

				documentos.add(documento);
			}
		}
		return documentos;
	}

	@Override
	public void remover(String id) {

		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			MongoCollection<Document> collection = database.getCollection("documento");

			Bson query = eq("_id", id);
			collection.deleteOne(query);
		}

	}

}
