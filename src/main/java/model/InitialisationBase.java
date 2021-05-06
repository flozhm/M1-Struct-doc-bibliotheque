package model;

import java.io.File;

public class InitialisationBase {

	  
   //Permet de récupérer
   public static File[] recupFichiers() {
	  
	  File repertoire = new File("./import"); // Répertoire dans lequel sont stockés les fichiers
	  File[] files=repertoire.listFiles(); //Liste des fichiers
	    
	  return files;
   }
   
   //Permet d'insérer les données d'un fichier txt dans la base de données
   //Renvoie false si l'oeuvre existe déjà, true sinon
   public static boolean importerFichierEnBase(File fichier) {
	   
	   //Tester s'il existe en base
	   try {
		   //Requête
	   } catch (Exception e) {

	   }
	   
	   //Si ça existe déjà
	   if (1 == 1) {
		   
	   }
	   return true;
   }
   
   
   
   //Vider base
   public static void viderBDD() {
	   
	   //Connexion à la BDD
	   //On vide chaque table
	   	   
   }
   
   
}
