package model;

import java.io.File;

public class MAJBase {

	  
   //Permet de récupérer
   public static File[] recupFichiers() {
	  
	  File repertoire = new File("./import"); // Répertoire dans lequel sont stockés les fichiers
	  File[] files=repertoire.listFiles(); //Liste des fichiers
	    
	  return files;
   }
   
   //Permet d'insérer les données d'un fichier txt dans la base de données s'il n'existe pas déjà
   //Renvoie false si l'oeuvre existe déjà, true sinon
   public static boolean importerOeuvreEnBase(Oeuvre oeuvre) {
	   
	   //Tester s'il existe en base

	   //Requête

	   
	   //Si ça existe déjà
	   if (1 == 1) {
		   
	   }
	   return true;
   }
   
   //
   public static Oeuvre lireFichier(File fichier) {
	   
	   return null; //si fichier non lisible
   }
   
   //Déplacer fichier
   

   //Vider base
   public static void viderBDD() {
	   
	   //Connexion à la BDD
	   //On vide chaque table
	   	   
   }
   
   
}
