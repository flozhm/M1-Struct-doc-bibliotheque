package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
	   
	   //On teste s'il existe déjà dans la BDD (via nom de famille)
       //Requête BDD
	   
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
   
   //(Lors de l'insertion d'une oeuvre)
   //Insère un utilisateur en base
   public static boolean insererUtilisateurEnBase(Utilisateur user) {
	   
	   Document document = new Document();
	   Document query;
	   long count; //Compteur
	   MongoCollection<Document> collection = new MongoDBConnexion().getDatabase().getCollection("utilisateur");
	
       
	   //On teste s'il existe déjà dans la BDD (via nom)
       //Requête BDD qui liste tous les utilisateurs via leur nom
	   
	   //db.utilisateur.find({"nom": com.getNom()})
       query = new Document("nom",user.getNom());
       count = collection.countDocuments(query);
       System.out.println("Requête : " + count);
       collection.find(query).limit(5).forEach(element -> System.out.println(element));
       
       
       //S'il n'existe pas :
       if (count == 0) {
		   //On créé le document à insérer
    	   document.append("login", user.getNom().toLowerCase() );
	   } else {
		   //S'il existe déjà on ajoute un numéro correspondant au n-ème nom
		   //On créé le document à insérer
    	   document.append("login", user.getNom().toLowerCase() + count);
	   }
       
       document.append("nom", user.getNom() );
       document.append("prenom", user.getPrenom());
       document.append("universiteRattachement", user.getUniversiteRattachement());
       document.append("formation", user.getFormation());
       document.append("role", user.getRole());
       
       //On ajoute le nouvel utilisateur dans la collection utilisateur
       collection.insertOne(document);
 	   return true;
   }
   
   //(Lors de l'insertion d'une nouvelle oeuvre)
   //Insère une formation en base
   public static boolean insererFormationEnBase(Formation formation) {

	   //On teste s'il existe déjà dans la BDD (via nom)
       //Requête BDD
	   
	   return false;
   }
   
   //(Via l'application lorsqu'on est connecté)
   //Insère un commentaire en base
   public static boolean insererCommentaireEnBase(Commentaire com) {
	   
	   Document document = new Document();
	   Document query;
	   long count; //Compteur
	   MongoCollection<Document> collection = new MongoDBConnexion().getDatabase().getCollection("commentaire");
	
       
       //On teste s'il existe déjà dans la BDD (via titreOeuvre + login + datePublication)
       //Requête BDD qui liste tous les titres, logins et dates de publication égales à ceux du commentaire en param
	   
	   //db.commentaire.find({"oeuvre": com.getOeuvre(), "datePublication": com.getDatePublication(), "login": com.getLogin()})
       query = new Document("oeuvre",com.getOeuvre())
    		   	.append("datePublication", com.getDatePublication())
    		   	.append("login", com.getLogin());
       count = collection.countDocuments(query);
       System.out.println("Requête : " + count);
       collection.find(query).limit(5).forEach(element -> System.out.println(element));
       
       
       //S'il n'existe pas :
       if (count == 0) {

		   //On créé le document à insérer
    	   document.append("oeuvre", com.getOeuvre() );
	       document.append("login", com.getLogin() );
	       document.append("datePublication", com.getDatePublication());
	       document.append("note", com.getNote());
	       document.append("texte", com.getTexte());
	       
	       //On l'insère dans la collection commentaire
	       collection.insertOne(document);
	       return true;
	   	} else {
	  	  //S'il existe on ne fait rien
	 	   return false;
	   	}

   }
   
  
   
   //Déplacer fichier
 

	// Permet d'insérer les données d'un fichier txt dans la base de données s'il
	// n'existe pas déjà
	// Renvoie false si l'oeuvre existe déjà, true sinon
	public static boolean importerOeuvreEnBase(File file) {

		// Tester s'il existe en base

		// Requête

		// Si ça existe déjà
		if (1 == 1) {

		}
		return true;
	}

	//Transforme un fichier en une oeuvre en analysant son contenu
	public static void lireFichier(File f) {
		Oeuvre oeuvre = new Oeuvre();

		try {
			// Créer l'objet File Reader
			FileReader fr = new FileReader(f);
			// Créer l'objet BufferedReader
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			String line;

			boolean isContenu = false;
			while (((line = br.readLine()) != null)) {

				if (line.split(": ")[0].equals("Titre")) {
					oeuvre.setTitre(line.split(": ")[1]);
				} else if (line.split(": ")[0].equals("Auteurs")) {
					if (line.split(": ")[1].contains(",")) {
						String[] auteurs;
						auteurs = line.split(": ")[1].split(", ");
						for (int i = 0; i < auteurs.length; i++) {
							oeuvre.getAuteurs().add(auteurs[i]);
						}
					} else {
						oeuvre.getAuteurs().add(line.split(": ")[1]);
					}

				} else if (line.split(": ")[0].equals("Pages")) {
					oeuvre.setNbPage(Integer.parseInt(line.split(": ")[1]));

				} else if (line.split(": ")[0].equals("Publication")) {
					oeuvre.setDatePubli(line.split(": ")[1]);

				} else if (line.split(": ")[0].equals("Theme")) {
					oeuvre.setTheme(line.split(": ")[1]);
				} else if (line.split(": ")[0].equals("Roles")) {
					oeuvre.setRole(line.split(": ")[1]);
				} else if (line.split(": ")[0].equals("Formations")) {
					continue;

				} else if (line.split(": ")[0].equals("Universites")) {
					continue;

				} else if (isContenu && !line.split(": ")[0].equals("Contenu:")) {
					sb.append(line);
					sb.append("\n");

				} else {

					isContenu = true;

				}

			}

			fr.close();
			// System.out.println("Contenu du contenu: ");
			// System.out.println(sb.toString());
			oeuvre.setContenu() = sb.toString();
			System.out.println(oeuvre.toString());

		} catch (

		IOException e) {
			// A voir ce que l'on fait ici
		}
	}

	// Déplacer fichier

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
