package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

public class MAJBase {

    // Permet de récupérer
    public static File[] recupFichiers() {
	   return null;
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
       
	   //On créé le document à insérer
       //Si le login n'existe pas :
       if (count == 0) {
    	   //On l'ajoute
    	   document.append("login", user.getNom().toLowerCase() );
	   } else {
		   //S'il existe déjà on ajoute un numéro correspondant au n-ème login
    	   document.append("login", user.getNom().toLowerCase() + count);
	   }
       
       
     //On créé une liste de documents pour la ou les formation(s)
     List<Document> formations = new ArrayList<Document>();
     for(int i = 0; i < user.getFormation().size(); i++){
	     formations.add(
               new Document("nom", user.getFormation().get(i).getNom())
                       .append("anneeEntree", user.getFormation().get(i).getAnneeEntree())
                       .append("anneeSortie", user.getFormation().get(i).getAnneeSortie())
         );
     }
       document.append("nom", user.getNom() );
       document.append("prenom", user.getPrenom() );
       document.append("universiteRattachement", user.getUniversiteRattachement() );
       document.append("formations", formations);
       document.append("role", user.getRole().name() );
       
       //On ajoute le nouvel utilisateur dans la collection utilisateur
       collection.insertOne(document);
 	   return true;
   }
   
   //(Lors de l'insertion d'une oeuvre)
   //Insère une oeuvre en base
   public static boolean insererOeuvreEnBase(Oeuvre oeuvre) {
	   // On teste si elle existe déjà dans la BDD (via titre)

	   Document document = new Document();
	   Document query;
	   long count; // Compteur
	   MongoCollection<Document> collection = new MongoDBConnexion().getDatabase().getCollection("oeuvre");
	   
	   // On teste s'il existe déjà dans la BDD (via le titre de l'oeuvre)
	   // Requête BDD qui liste tous les titres égaux à ceux de l'oeuvre en param
	   query = new Document("oeuvre", oeuvre.getTitre());
	   count = collection.countDocuments(query);
	   System.out.println("Requête : " + count);
	   collection.find(query).limit(5).forEach(element -> System.out.println(element));
	   
	   // Si l'oeuvre n'existe pas : 
	   if (count == 0) {
		   //On créé le document
		   		   
		     //On créé une liste de documents pour la ou les formation(s)
		     List<Document> auteurs = new ArrayList<Document>();
		     for(int i = 0; i < oeuvre.getAuteurs().size(); i++){
		    	 auteurs.add(
		               new Document("nom", oeuvre.getAuteurs().get(i).getNom())
		                       .append("prenom", oeuvre.getAuteurs().get(i).getPrenom())
		         );
		     }
		   		   
		   document.append("titre", oeuvre.getTitre());
		   document.append("auteurs", auteurs);
		   document.append("nbPage", oeuvre.getNbPage());
		   document.append("datePublication", oeuvre.getDatePubli());
		   document.append("thematique", oeuvre.getTheme());
		   document.append("contenu", oeuvre.getContenu());
		   
		   collection.insertOne(document);
		   return true;
	   }
	   else {
		   //On ne fait rien
	   }
	   return false;
    }
   
    // (Lors de l'insertion d'une nouvelle oeuvre)
    // Insère une formation en base
    public static boolean insererFormationEnBase(Formation formation) {
	// On teste s'il existe déjà dans la BDD (via nom)
	// Requête BDD
	return false;
    }

    // (Via l'application lorsqu'on est connecté)
    // Insère un commentaire en base
    public static boolean insererCommentaireEnBase(Commentaire com) {
	Document document = new Document();
	Document query;
	long count; // Compteur
	MongoCollection<Document> collection = new MongoDBConnexion().getDatabase().getCollection("commentaire");

	// On teste s'il existe déjà dans la BDD (via titreOeuvre + login +
	// datePublication)
	// Requête BDD qui liste tous les titres, logins et dates de publication égales
	// à ceux du commentaire en param

	// db.commentaire.find({"oeuvre": com.getOeuvre(), "datePublication":
	// com.getDatePublication(), "login": com.getLogin()})
	query = new Document("oeuvre", com.getOeuvre()).append("datePublication", com.getDatePublication())
		.append("login", com.getLogin());
	count = collection.countDocuments(query);
	System.out.println("Requête : " + count);
	collection.find(query).limit(5).forEach(element -> System.out.println(element));

	// S'il n'existe pas :
	if (count == 0) {
	    // On créé le document à insérer
	    document.append("oeuvre", com.getOeuvre());
	    document.append("login", com.getLogin());
	    document.append("datePublication", com.getDatePublication());
	    document.append("note", com.getNote());
	    document.append("texte", com.getTexte());

	    // On l'insère dans la collection commentaire
	    collection.insertOne(document);
	    return true;
	}
	else {
	    // S'il existe on ne fait rien
	    return false;
	}

    }

    // Transforme un fichier en une oeuvre en analysant son contenu
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
		}
		else if (line.split(": ")[0].equals("Auteurs")) {
		    if (line.split(": ")[1].contains(",")) {
			String[] auteurs;
			auteurs = line.split(": ")[1].split(", ");
			for (int i = 0; i < auteurs.length; i++) {
			    oeuvre.getAuteurs().add(auteurs[i]);
			}
		    }
		    else {
			oeuvre.getAuteurs().add(line.split(": ")[1]);
		    }
		}
		else if (line.split(": ")[0].equals("Pages")) {
		    oeuvre.setNbPage(Integer.parseInt(line.split(": ")[1]));

		}
		else if (line.split(": ")[0].equals("Publication")) {
		    oeuvre.setDatePubli(line.split(": ")[1]);

		}
		else if (line.split(": ")[0].equals("Theme")) {
		    oeuvre.setTheme(line.split(": ")[1]);
		}
		else if (line.split(": ")[0].equals("Roles")) {
		    oeuvre.setRole(line.split(": ")[1]);
		}
		else if (line.split(": ")[0].equals("Formations")) {
		    continue;

		}
		else if (line.split(": ")[0].equals("Universites")) {
		    continue;

		}
		else if (isContenu && !line.split(": ")[0].equals("Contenu:")) {
		    sb.append(line);
		    sb.append("\n");
		}
		else {
		    isContenu = true;
		}
	    }
	    fr.close();
	    // System.out.println("Contenu du contenu: ");
	    // System.out.println(sb.toString());
	    oeuvre.setContenu(sb.toString());
	    System.out.println(oeuvre.toString());
	}
	catch (IOException e) {
	    // A voir ce que l'on fait ici
	}
    }

    // Déplacer fichier
    
    // Vider base
    public static void viderBDD() {
	// On vide chaque collection
	new MongoDBConnexion().getDatabase().getCollection("oeuvre").drop(); // Supprime la collection
	new MongoDBConnexion().getDatabase().createCollection("oeuvre"); // La crée

	new MongoDBConnexion().getDatabase().getCollection("utilisateur").drop(); // Supprime la collection
	new MongoDBConnexion().getDatabase().createCollection("utilisateur"); // La crée

	new MongoDBConnexion().getDatabase().getCollection("formation").drop(); // Supprime la collection
	new MongoDBConnexion().getDatabase().createCollection("formation"); // La crée

	new MongoDBConnexion().getDatabase().getCollection("commentaire").drop(); // Supprime la collection
	new MongoDBConnexion().getDatabase().createCollection("commentaire"); // La crée
    }
}