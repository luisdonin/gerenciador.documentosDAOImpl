package br.edu.utfpr.td.tsi.gerenciador.documentos.persistencia.mongoDB;

import static com.mongodb.client.model.Filters.eq;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.edu.utfpr.td.tsi.gerenciador.documentos.persistencia.AutorDAO;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.edu.utfpr.td.tsi.gerenciador.documentos.modelo.Autor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MongoDBAutorDAO implements AutorDAO {

/*
 1 - 04/05/2025
	private String connectionString = "mongodb:// localhost:27017";
	private String databaseName = "gerenciador-documentos";
	mudança funcionando
*/
@Value("${spring.data.mongodb.uri}")
private String connectionString;

	@Value("${spring.data.mongodb.database}")
	private String databaseName;
	public void persistir(Autor autor) {

		autor.setId(UUID.randomUUID().toString());

		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			MongoCollection<Document> collection = database.getCollection("autor");

			Document documentAutor = new Document();
			autor.setId(UUID.randomUUID().toString());
			documentAutor.append("_id", autor.getId());
			documentAutor.append("nome", autor.getNome());
			documentAutor.append("email", autor.getEmail());
			documentAutor.append("dataNascimento", autor.getDataNascimento());

			collection.insertOne(documentAutor);
		}
	}

	@Override
	public List<Autor> listar() {
		ArrayList<Autor> autores = new ArrayList<>();

		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			MongoCollection<Document> collection = database.getCollection("autor");

			FindIterable<Document> documentosEncontrados = collection.find();
			for (Document document : documentosEncontrados) {
				if (document != null) {
					String idAutor = document.getString("_id");
					String nomeAutor = document.getString("nome");
					String emailAutor = document.getString("email");
					Date dataNascimentoAutor = document.getDate("dataNascimento");

					Autor autor = new Autor();
					autor.setId(idAutor);
					autor.setNome(nomeAutor);
					autor.setEmail(emailAutor);

					if (dataNascimentoAutor != null) {
						LocalDate localDate = LocalDate.ofInstant(dataNascimentoAutor.toInstant(), ZoneId.systemDefault());
						autor.setDataNascimento(localDate);
					}

					autores.add(autor);
				}
			}
		}
		return autores;
	}

	public void remover(String id) {

		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			MongoCollection<Document> collection = database.getCollection("autor");

			Bson query = eq("_id", id);
			collection.deleteOne(query);
		}

	}

}
