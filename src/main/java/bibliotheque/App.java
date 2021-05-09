package bibliotheque;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bson.Document;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Commentaire;
import model.MAJBase;
import model.MongoDBConnexion;
import model.Oeuvre;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final Logger LOG = Logger.getLogger(App.class.getName());

    @Override
    public void start(Stage primaryStage) {
	primaryStage.setTitle("Bibliotheque");
	try {
	    Parent root = FXMLLoader.load(getClass().getResource("bibliotheque.fxml"));
	    Scene scene = new Scene(root);
	    primaryStage.setScene(scene);
	    primaryStage.setMaximized(true);
	    primaryStage.show();
	}
	catch (IOException e) {
	    e.printStackTrace();
	    LOG.severe("Erreur");
	}
    }

    public static void main(String[] args) {
    
    	Oeuvre[] fichiers = null;
    	
    	MAJBase.viderBDD();//On vide la BDD 
    	//fichiers = MAJBase.recupFichiers(); //On récupère les fichiers
    	Document document = new Document();
    	Commentaire com = new Commentaire("loginFZ", "2011-03-16", 9.4, "blabla");
        document.append("login", com.getLogin() );
        document.append("datePublication", com.getDatePublication());
        document.append("note", com.getNote());
        document.append("texte", com.getTexte());
        
        new MongoDBConnexion().getDatabase().getCollection("commentaire").insertOne(document);
        
    	//MAJBase.insererCommentaireEnBase(com);
    
  	  /*for(int i = 0; i < fichiers.length ; i++){ //On boucle sur les fichiers du répertoire

		  //String fileName = fichiers[i].getName(); // On récupère le nom du fichier
		  //MAJBase.importerOeuvreEnBase(fichiers[i]); //On insère les données des fichiers un par un dans la BDD
	  }

    	launch(); // On lance l'application*/
    }

}