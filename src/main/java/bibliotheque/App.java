package bibliotheque;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InitialisationBase;

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
	    primaryStage.show();
	}
	catch (IOException e) {
	    e.printStackTrace();
	    LOG.severe("Erreur");
	}
    }

    public static void main(String[] args) {
    
    	File[] fichiers = null;
    	
    	InitialisationBase.viderBDD();//On vide la BDD 
    	fichiers = InitialisationBase.recupFichiers(); //On récupère les fichiers
    	
    	
  	  for(int i = 0; i < fichiers.length ; i++){ //On boucle sur les fichiers du répertoire

		  //String fileName = fichiers[i].getName(); // On récupère le nom du fichier
		  InitialisationBase.importerFichierEnBase(fichiers[i]); //On insère les données des fichiers un par un dans la BDD
	  }

    	launch(); // On lance l'application
    }

}