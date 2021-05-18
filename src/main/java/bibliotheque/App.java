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
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	// Récupération et insertion des fichiers du dossier "import" en base de données
	File[] fichiers = MAJBase.recupFichiers();
	for (int i = 0; i < fichiers.length; i++) {
	    MAJBase.lireFichier(fichiers[i]);
	}

	// Lancement de l'application
	launch();
    }

}