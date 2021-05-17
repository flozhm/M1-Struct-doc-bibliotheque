package bibliotheque;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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

	MAJBase.viderBDD();// On vide la BDD

	// Insertion utilisateur test
	ArrayList<FormationUtilisateur> formation1 = new ArrayList<FormationUtilisateur>();
	formation1.add(new FormationUtilisateur("LicenceMIAGE", 2016, 2019));
	formation1.add(new FormationUtilisateur("MasterMIAGE", 2019, 2021));
	Utilisateur user = new Utilisateur("pierrat", "Charly", "IDMC", formation1, Role.Etudiant);
	MAJBase.insererUtilisateurEnBase(user);

	/*
	 * BasicDBObject query = new BasicDBObject(); MongoDBConnexion mdb = new
	 * MongoDBConnexion(); query.put("login", "pierrat"); Document utilisateur =
	 * mdb.getDatabase().getCollection("utilisateur").find(query).first();
	 * System.out.println(utilisateur.getList("formations", Document.class));
	 */

	// Insertion oeuvre test
	ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
	auteurs.add(new Auteur("zahhhhhhhm", "Florian"));
	auteurs.add(new Auteur("ait hhhhhsaine", "Myriam"));
	Oeuvre oeuvre = new Oeuvre("Oeuvre1", auteurs, 50, LocalDate.of(2011, 3, 16), Role.Etudiant, "Poisson",
		"blablabla");
	MAJBase.insererOeuvreEnBase(oeuvre);

	Oeuvre oeuvre2 = new Oeuvre("Oeuvre2", auteurs, 50, LocalDate.of(2011, 3, 16), Role.Etudiant, "Poisson",
		"blablabla");
	MAJBase.insererOeuvreEnBase(oeuvre2);

	// Insertion commentaire test
	Commentaire com = new Commentaire("Oeuvre1", "zahm", LocalDate.of(2011, 3, 16), 9.4, "blabla"); // YYYY-MM-DD
	MAJBase.insererCommentaireEnBase(com);

	Commentaire com2 = new Commentaire("Oeuvre1", "zahm", LocalDate.of(2011, 4, 16), 9.4, "blabla"); // YYYY-MM-DD
	MAJBase.insererCommentaireEnBase(com2);

	Commentaire com3 = new Commentaire("Oeuvre1", "zahm", LocalDate.of(2011, 5, 16), 9.4, "blabla"); // YYYY-MM-DD
	MAJBase.insererCommentaireEnBase(com3);

	Commentaire com4 = new Commentaire("Oeuvre1", "zahm", LocalDate.of(2011, 6, 16), 9.4, "blabla"); // YYYY-MM-DD
	MAJBase.insererCommentaireEnBase(com4);

	Commentaire com5 = new Commentaire("Oeuvre1", "zahm", LocalDate.of(2011, 7, 16), 9.4, "blabla"); // YYYY-MM-DD
	MAJBase.insererCommentaireEnBase(com5);

	Commentaire com6 = new Commentaire("Oeuvre1", "zahm", LocalDate.of(2011, 8, 16), 9.4, "blabla"); // YYYY-MM-DD
	MAJBase.insererCommentaireEnBase(com6);

	Commentaire com7 = new Commentaire("Oeuvre1", "zahm", LocalDate.of(2011, 9, 16), 9.4, "blabla"); // YYYY-MM-DD
	MAJBase.insererCommentaireEnBase(com7);

	Commentaire com8 = new Commentaire("Oeuvre1", "zahm", LocalDate.of(2011, 10, 16), 9.4, "blabla"); // YYYY-MM-DD
	MAJBase.insererCommentaireEnBase(com8);

	Commentaire com9 = new Commentaire("Oeuvre1", "zahm", LocalDate.of(2011, 11, 16), 9.4, "blabla"); // YYYY-MM-DD
	MAJBase.insererCommentaireEnBase(com9);

	Commentaire com10 = new Commentaire("Oeuvre1", "zahm", LocalDate.of(2011, 12, 16), 9.4, "blabla"); // YYYY-MM-DD
	MAJBase.insererCommentaireEnBase(com10);

	// Insertion formation test
	/*
	 * ArrayList<String> universites = new ArrayList<String>();
	 * universites.add("IDMC"); universites.add("DME"); Formation formation = new
	 * Formation("MasterMIAGE", "B+5", 50, universites);
	 * MAJBase.insererFormationEnBase(formation);
	 */

	// File[] fichiers = null;
	// fichiers = MAJBase.recupFichiers(); // On récupère les fichiers
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