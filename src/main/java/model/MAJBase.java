package model;

import java.io.File;

import org.bson.Document;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

public class MAJBase {

	  
   //Permet de récupérer
   public static File[] recupFichiers() {
	  
	  File repertoire = new File("./import"); // Répertoire dans lequel sont stockés les fichiers
	  File[] files=repertoire.listFiles(); //Liste des fichiers
	    
	  return files;
   }
   
   //Permet d'insérer les données d'un fichier txt dans la base de données s'il n'existe pas déjà
   //Renvoie false si l'oeuvre existe déjà, true sinon
   public static boolean insererOeuvreEnBase(Oeuvre oeuvre) {
	   
	   //On teste si l'oeuvre existe en base
	   
	   
	   //Requête
	   /*
	   //Test insert d'un document dans la table commentaire
	   Document document = new Document();
       document.append("login", "oiseau54");
       document.append("datePublication", 2011-03-16);
       document.append("note", "9.4");
       document.append("texte", "blablablabla blabla bla");
       
       System.out.println(document.toString());

	   new MongoDBConnexion().getDatabase().getCollection("commentaire").insertOne(document);
	   System.out.println("Document inséré avec succès"); 
	   */
	   
	   //Si ça existe déjà
	   if (1 == 1) {
		   
	   }
	   return true;
   }
   
   public static boolean insererUtilisateurEnBase(Utilisateur utilisateur) {
	   
	   return false;
   }
   
   public static boolean insererFormationEnBase(Formation formation) {

	   return false;
   }
   
   public static boolean insererCommentaireEnBase(Commentaire com) {
	   
	   Document document = new Document();
	   //On créé le document à insérer
       document.append("login", com.getLogin() );
       document.append("datePublication", com.getDatePublication());
       document.append("note", com.getNote());
       document.append("texte", com.getTexte());
       
       //On teste s'il existe déjà dans la BDD
       //Requête BDD
       
       //S'il n'existe pas, on l'insère dans la collection commentaire
       new MongoDBConnexion().getDatabase().getCollection("commentaire").insertOne(document);
	  //Si oui on ne fait rien
	   return false;
   }
   
   
   //Trasnforme un fichier en une oeuvre en analysant son contenu
   public static void lireFichier(File fichier) {
	   
	   //si fichier non lisible
   }
   
   //Déplacer fichier
   

   //Vider base
   public static void viderBDD() {
	   
	   //On vide chaque collection
	   new MongoDBConnexion().getDatabase().getCollection("oeuvre").drop(); //Supprime la collection
	   new MongoDBConnexion().getDatabase().createCollection("oeuvre"); //La crée
	   
	   new MongoDBConnexion().getDatabase().getCollection("utilisateur").drop(); //Supprime la collection
	   new MongoDBConnexion().getDatabase().createCollection("utilisateur"); //La crée
	   
	   new MongoDBConnexion().getDatabase().getCollection("formation").drop(); //Supprime la collection
	   new MongoDBConnexion().getDatabase().createCollection("formation"); //La crée
	   
	   new MongoDBConnexion().getDatabase().getCollection("commentaire").drop(); //Supprime la collection
	   new MongoDBConnexion().getDatabase().createCollection("commentaire"); //La crée
	   

   }
   
   
}
