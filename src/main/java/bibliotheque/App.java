package bibliotheque;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MAJBase;

public class App extends Application {

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
		}
	}

	public static void main(String[] args) {

		MAJBase.viderBDD();// On vide la BDD

		File[] fichiers = MAJBase.recupFichiers(); // On récupère les fichiers

		for (int i = 0; i < fichiers.length; i++) {
			MAJBase.lireFichier(fichiers[i]);
		}

		launch(); // On lance l'application*/

	}

}