package bibliotheque;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Auteur;
import model.Commentaire;
import model.FormationUtilisateur;
import model.MAJBase;
import model.Oeuvre;
import model.Role;
import model.Utilisateur;

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

    //MAJBase.viderBDD();//On vide la BDD 
    	
	//Insertion commentaire test 
	//Commentaire com = new Commentaire("Oeuvre", "loginFZ58", "2011-03-16", 9.4, "blabla");
    //MAJBase.insererCommentaireEnBase(com);
    //new LocalDate(0, 0, 0);
	
	//Insertion utilisateur test
	/*ArrayList<FormationUtilisateur> formation1 = new ArrayList<FormationUtilisateur>();
	formation1.add(new FormationUtilisateur("LicenceMIAGE",2016,2019));
	formation1.add(new FormationUtilisateur("MasterMIAGE",2019,2021));
	Utilisateur user = new Utilisateur("Pierrat", "Charly", "IDMC", formation1, Role.Etudiant);
    MAJBase.insererUtilisateurEnBase(user);*/
	
	//Insertion oeuvre test
	/*ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
	Oeuvre oeuvre = new Oeuvre("Oeuvre1", auteurs, int nbPage, LocalDate datePubli, String role, String contenu);
	
	insererOeuvreEnBase(oeuvre);*/
	
	//Insertion formation test
   
    
        
	//File[] fichiers = null;
	//fichiers = MAJBase.recupFichiers(); // On récupère les fichiers
	/*
	 * for (int i = 0; i < fichiers.length; i++) { // On boucle sur les fichiers du
	 * répertoire MAJBase.lireFichier(fichiers[i]); // String fileName =
	 * fichiers[i].getName(); // On récupère le nom du fichier //
	 * MAJBase.importerOeuvreEnBase(fichiers[i]); //On insère les données des //
	 * fichiers un par un dans la BDD }
	 */

	launch(); // On lance l'application*/

    }
}