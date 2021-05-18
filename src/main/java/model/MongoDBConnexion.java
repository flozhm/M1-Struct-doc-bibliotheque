package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.Document;
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

    /**
     * Récupère la liste des oeuvres associées à un Rôle
     * 
     * @param role Rôle
     * @return liste d'oeuvres
     */
    public List<Oeuvre> getOeuvres(Role role) {
	// Initialisation de la liste
	List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();

	// Paramètrage du filtre
	BasicDBObject query = new BasicDBObject();
	query.put("role", roletoString(role));

	// Récupère les oeuvres de la base de données et les insère dans la liste
	database.getCollection("oeuvre").find(query).forEach(oeuvre -> {
	    List<Auteur> auteurs = new ArrayList<Auteur>();
	    oeuvre.getList("auteurs", Document.class)
		    .forEach(auteur -> auteurs.add(new Auteur(auteur.getString("nom"), auteur.getString("prenom"))));
	    oeuvres.add(new Oeuvre(oeuvre.getString("titre"), auteurs, oeuvre.getInteger("nbPage"),
		    stringToLocalDate(oeuvre.getString("datePublication")), stringToRole(oeuvre.getString("role")),
		    oeuvre.getString("theme"), oeuvre.getString("contenu")));
	});
	return oeuvres;
    }

    /**
     * Récupération des commentaires d"une oeuvre
     * 
     * @param oeuvre Oeuvre
     * @return liste de commentaires
     */
    public List<Commentaire> getCommentaires(Oeuvre oeuvre) {
	// Initialisation de la liste
	List<Commentaire> commentaires = new ArrayList<Commentaire>();

	// Paramètrage du filtre
	BasicDBObject query = new BasicDBObject();
	query.put("oeuvre", oeuvre.getTitre());

	// Récupère les commentaires de la base de données et les insère dans la liste
	database.getCollection("commentaire").find(query)
		.forEach(commentaire -> commentaires.add(new Commentaire(commentaire.getString("oeuvre"),
			commentaire.getString("login"), stringToLocalDate(commentaire.getString("datePublication")),
			commentaire.getDouble("note"), commentaire.getString("texte"))));
	return commentaires;
    }

    /**
     * Récupère un utilisateur via son login
     * 
     * @param login
     * @return
     */
    public Utilisateur getUtilisateur(String login) {
	// Paramètrage du filtre
	BasicDBObject query = new BasicDBObject();
	query.put("login", login);

	// Récupération de l'utilisateur en base de données
	Document utilisateur = database.getCollection("utilisateur").find(query).first();

	// Transformation des FormationUtilisateur en objet
	List<FormationUtilisateur> formationsUtilisateurs = new ArrayList<FormationUtilisateur>();
	utilisateur.getList("formations", Document.class)
		.forEach(formationUtilisateur -> formationsUtilisateurs.add(new FormationUtilisateur(
			formationUtilisateur.getString("nom"), formationUtilisateur.getInteger("anneeEntree"),
			formationUtilisateur.getInteger("anneeSortie"))));

	// Transformation de l'utilisateur en objet
	return new Utilisateur(utilisateur.getString("login"), utilisateur.getString("nom"),
		utilisateur.getString("prenom"), utilisateur.getString("universiteRattachement"),
		formationsUtilisateurs, stringToRole(utilisateur.getString("role")));
    }

    /**
     * Retourne la note moyenne d'une oeuvre
     * 
     * @param oeuvre Oeuvre
     * @return note moyenne
     */
    public Double getNote(Oeuvre oeuvre) {
	List<Commentaire> commentaires = getCommentaires(oeuvre);
	return commentaires.stream().map(commentaire -> commentaire.getNote())
		.collect(Collectors.summingDouble(Double::doubleValue)) / commentaires.stream().count();
    }

    /**
     * Retourne la date du dernier commentaire d'une oeuvre
     * 
     * @param oeuvre Oeuvre
     * @return date du dernier commentaire
     */
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

    /**
     * Convertit un String en LocalDate
     * 
     * @param date LocalDate
     * @return localdate
     */
    public static LocalDate stringToLocalDate(String date) {
	String[] dateTab = date.split("-");
	return LocalDate.of(Integer.parseInt(dateTab[0]), Integer.parseInt(dateTab[1]), Integer.parseInt(dateTab[2]));
    }

    /**
     * Convertit une LocalDate en String
     * 
     * @param date LocalDate
     * @return string
     */
    public static String localDatetoString(LocalDate date) {
	return date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
    }

    /**
     * Convertit un String en Rôle
     * 
     * @param role String
     * @return role
     */
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

    /**
     * Convertit un Rôle en String
     * 
     * @param role Role
     * @return string
     */
    public static String roletoString(Role role) {
	return role.name().toLowerCase();
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
