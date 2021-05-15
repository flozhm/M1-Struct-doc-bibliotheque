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
	database.getCollection("oeuvre").find().forEach(
		oeuvre -> oeuvres.add(new Oeuvre(oeuvre.getString("titre"), oeuvre.getList("auteurs", Auteur.class),
			oeuvre.getInteger("nbPage"), stringToLocalDate(oeuvre.getString("datePublication")),
			stringToRole(oeuvre.getString("role")), oeuvre.getString("contenu"))));
	return oeuvres;
    }

    public List<Commentaire> getCommentaires(Oeuvre oeuvre) {
	List<Commentaire> commentaires = new ArrayList<Commentaire>();
	BasicDBObject query = new BasicDBObject();
	query.put("oeuvre", oeuvre.getTitre());
	database.getCollection("commentaire").find(query)
		.forEach(commentaire -> commentaires.add(new Commentaire(commentaire.getString("oeuvre"),
			commentaire.getString("login"), stringToLocalDate(commentaire.getString("datePublication")),
			commentaire.getDouble("note"), commentaire.getString("texte"))));
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
	if (getCommentaires(oeuvre).stream().count() > 0) {
	    return getCommentaires(oeuvre).stream()
		    .sorted(Comparator.comparing(Commentaire::getDatePublication).reversed())
		    .collect(Collectors.toList()).get(0).getDatePublication();
	}
	else {
	    return null;
	}
    }

    public static LocalDate stringToLocalDate(String date) {
	String[] dateTab = date.split("-");
	return LocalDate.of(Integer.parseInt(dateTab[0]), Integer.parseInt(dateTab[1]), Integer.parseInt(dateTab[2]));
    }

    public static String localDatetoString(LocalDate date) {
	return date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
    }

    public static Role stringToRole(String role) {
	switch (role) {
	    case "etudiant":
		return Role.Etudiant;

	    case "professeur":
		return Role.Professeur;

	    case "chercheur":
		return Role.Chercheur;

	    case "administratif":
		return Role.Administratif;

	    default:
		return null;
	}
    }
    

    

    public static String roletoString(Role role) {
	return role.name().toLowerCase();
    }
}
