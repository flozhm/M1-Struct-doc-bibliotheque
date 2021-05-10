package model;

import java.util.List;
import java.util.stream.Collectors;
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

    /**
     * 
     */
    public List<Oeuvre> getOeuvres() {
	return null;
    }

    public List<Commentaire> getCommentaires(Oeuvre oeuvre) {
	return null;
    }

    public double getNote(Oeuvre oeuvre) {
	return getCommentaires(oeuvre).stream().map(commentaire -> commentaire.getNote())
		.collect(Collectors.summingDouble(Double::doubleValue)) / getCommentaires(oeuvre).stream().count();

    }

    public List<Formation> getFormations() {
	// TODO Auto-generated method stub
	return null;
    }

    public List<FormationUtilisateur> getFormationUtilisateurs() {
	// TODO Auto-generated method stub
	return null;
    }

    public List<Utilisateur> getUtilisateurs() {
	// TODO Auto-generated method stub
	return null;
    }

}
