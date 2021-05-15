package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnexion {

    private MongoClient	  mongoClient;
    private MongoDatabase database;

    public MongoDBConnexion() {
	mongoClient = MongoClients.create();
	database = mongoClient.getDatabase("bibliotheque");
    }

    public MongoDatabase getDatabase() {
	return database;
    }

    public List<Oeuvre> getOeuvres() {
	List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
	database.getCollection("oeuvre").find().forEach(oeuvre -> {
	    List<Auteur> auteurs = new ArrayList<Auteur>();
	    // TODO Récupérer la liste des auteurs
	    oeuvres.add(new Oeuvre((String) oeuvre.get("titre"), auteurs, (int) oeuvre.get("nbPage"),
		    stringToLocalDate((String) oeuvre.get("datePublication")), (String) oeuvre.get("role"),
		    (String) oeuvre.get("contenu")));
	});
	return oeuvres;
    }

    public List<Commentaire> getCommentaires(Oeuvre oeuvre) {
	List<Commentaire> commentaires = new ArrayList<Commentaire>();
	BasicDBObject query = new BasicDBObject();
	query.put("oeuvre", oeuvre.getTitre());
	database.getCollection("commentaire").find(query)
		.forEach(commentaire -> commentaires.add(new Commentaire((String) commentaire.get("oeuvre"),
			(String) commentaire.get("login"),
			stringToLocalDate((String) commentaire.get("datePublication")),
			Double.parseDouble((String) commentaire.get("note")), (String) commentaire.get("texte"))));
	return commentaires;
    }

    public Double getNote(Oeuvre oeuvre) {
	return getCommentaires(oeuvre).stream().map(commentaire -> commentaire.getNote())
		.collect(Collectors.summingDouble(Double::doubleValue)) / getCommentaires(oeuvre).stream().count();
    }

    public List<Formation> getFormations() {
	List<Formation> formations = new ArrayList<Formation>();
	// TODO
	return formations;
    }

    public List<FormationUtilisateur> getFormationUtilisateurs() {
	List<FormationUtilisateur> formationsUtilisateurs = new ArrayList<FormationUtilisateur>();
	// TODO
	return formationsUtilisateurs;
    }

    public List<Utilisateur> getUtilisateurs() {
	List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	// TODO
	return utilisateurs;
    }

    public LocalDate getDateDerCommentaire(Oeuvre oeuvre) {
	return getCommentaires(oeuvre).stream().sorted(Comparator.comparing(Commentaire::getDatePublication).reversed())
		.collect(Collectors.toList()).get(0).getDatePublication();
    }

    public static LocalDate stringToLocalDate(String date) {
	// TODO
	return LocalDate.of(0, 0, 0);
    }
}
