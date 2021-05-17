package bibliotheque;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MAJBase;

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
		} catch (IOException e) {
			e.printStackTrace();
			LOG.severe("Erreur");
		}
	}

	public static void main(String[] args) {

		MAJBase.viderBDD();// On vide la BDD

		// Insertion utilisateur test
		/*
		 * ArrayList<FormationUtilisateur> formation1 = new
		 * ArrayList<FormationUtilisateur>(); formation1.add(new
		 * FormationUtilisateur("LicenceMIAGE", 2016, 2019)); formation1.add(new
		 * FormationUtilisateur("MasterMIAGE", 2019, 2021)); Utilisateur user = new
		 * Utilisateur("Mcdonnell", "Charly", "IDMC", formation1, Role.Etudiant);
		 * MAJBase.insererUtilisateurEnBase(user);
		 * 
		 * Utilisateur user2 = new Utilisateur("Mcdonnell", "Charlie", "IDMC",
		 * formation1, Role.Etudiant); MAJBase.insererUtilisateurEnBase(user2);
		 * 
		 * formation1.add(new FormationUtilisateur("Mzzeezez", 2019, 2021)); Utilisateur
		 * user3 = new Utilisateur("Mcdonnell", "Charlie", "IDMC2", formation1,
		 * Role.Etudiant); MAJBase.insererUtilisateurEnBase(user3);
		 */

		/*
		 * BasicDBObject query = new BasicDBObject(); MongoDBConnexion mdb = new
		 * MongoDBConnexion(); query.put("login", "pierrat"); Document utilisateur =
		 * mdb.getDatabase().getCollection("utilisateur").find(query).first();
		 * System.out.println(utilisateur.getList("formations", Document.class));
		 */

		// Insertion oeuvre test
		/*
		 * ArrayList<Auteur> auteurs = new ArrayList<Auteur>(); Oeuvre oeuvre = new
		 * Oeuvre("Oeuvre1", auteurs, 50, LocalDate.of(2011, 3, 16), Role.Etudiant,
		 * "Poisson", "blablabla"); MAJBase.insererOeuvreEnBase(oeuvre);
		 * 
		 * Oeuvre oeuvre2 = new Oeuvre("Oeuvre2", auteurs, 50, LocalDate.of(2011, 3,
		 * 16), Role.Etudiant, "Poisson", "blablabla");
		 * MAJBase.insererOeuvreEnBase(oeuvre2);
		 */

		// Insertion commentaire test
		/*
		 * Commentaire com = new Commentaire("Oeuvre1", "zahm", LocalDate.of(2011, 3,
		 * 16), 9.4, "blabla"); // YYYY-MM-DD MAJBase.insererCommentaireEnBase(com);
		 */

		// Insertion formation test
		/*
		 * ArrayList<String> universites = new ArrayList<String>();
		 * universites.add("IDMC"); universites.add("DME"); Formation formation = new
		 * Formation("MasterMIAGE", "B+5", 50, universites);
		 * MAJBase.insererFormationEnBase(formation);
		 */

		File[] fichiers = null;
		fichiers = MAJBase.recupFichiers(); // On récupère les fichiers

		for (int i = 0; i < fichiers.length; i++) {
			MAJBase.lireFichier(fichiers[i]);
			System.out.println(fichiers[i].getName());
		}

		launch(); // On lance l'application*/

	}

}