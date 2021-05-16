package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MAJBase {

    private static MongoDatabase mdb = new MongoDBConnexion().getDatabase();

    // Permet de récupérer les fichiers à traiter
    public static File[] recupFichiers() {
	return null;
    }

    // Insère un utilisateur en base
    public static void insererUtilisateurEnBase(Utilisateur user) {
	Document document = new Document();
	Document query;
	long count; // Compteur
	MongoCollection<Document> collection = mdb.getCollection("utilisateur");

	// On teste s'il existe déjà dans la BDD (via nom)
	// Requête BDD qui liste tous les utilisateurs via leur nom

	// db.utilisateur.find({"nom": com.getNom()})
	query = new Document("nom", user.getNom());
	count = collection.countDocuments(query);
	System.out.println("Requête : " + count);
	collection.find(query).limit(5).forEach(element -> System.out.println(element));

	// Si le login n'existe pas :
	if (count == 0) {
	    // On l'ajoute
	    document.append("login", user.getNom().toLowerCase());
	}
	else {
	    // S'il existe déjà on ajoute un numéro correspondant au n-ème login
	    document.append("login", user.getNom().toLowerCase() + count);
	}

	// On créé une liste de documents pour la ou les formation(s)
	List<Document> formations = new ArrayList<Document>();
	for (int i = 0; i < user.getFormation().size(); i++) {
	    formations.add(new Document("nom", user.getFormation().get(i).getNom())
		    .append("anneeEntree", user.getFormation().get(i).getAnneeEntree())
		    .append("anneeSortie", user.getFormation().get(i).getAnneeSortie()));
	}
	document.append("nom", user.getNom());
	document.append("prenom", user.getPrenom());
	document.append("universiteRattachement", user.getUniversiteRattachement());
	document.append("formations", formations);
	document.append("role", user.getRole().name());

	// On ajoute le nouvel utilisateur dans la collection utilisateur
	collection.insertOne(document);
    }

    // Insère une oeuvre en base
    public static void insererOeuvreEnBase(Oeuvre oeuvre) {
	Document document = new Document();
	Document query;
	long count;
	MongoCollection<Document> collection = mdb.getCollection("oeuvre");

	// On teste s'il existe déjà dans la BDD (via le titre de l'oeuvre)
	// Requête BDD qui liste tous les titres égaux à ceux de l'oeuvre en param
	query = new Document("titre", oeuvre.getTitre());
	count = collection.countDocuments(query);

	System.out.println("Requête : " + count);
	collection.find(query).limit(5).forEach(element -> System.out.println(element));

	// Si l'oeuvre n'existe pas :
	if (count == 0) {
	    // On créé une liste de documents pour le ou les auteurs(s)
	    List<Document> auteurs = new ArrayList<Document>();
	    for (int i = 0; i < oeuvre.getAuteurs().size(); i++) {
		auteurs.add(new Document("nom", oeuvre.getAuteurs().get(i).getNom()).append("prenom",
			oeuvre.getAuteurs().get(i).getPrenom()));
	    }

	    // Creation du document à inserer
	    document.append("titre", oeuvre.getTitre());
	    document.append("auteurs", auteurs);
	    document.append("nbPage", oeuvre.getNbPage());
	    document.append("datePublication", MongoDBConnexion.localDatetoString(oeuvre.getDatePubli()));
	    document.append("theme", oeuvre.getTheme());
	    document.append("role", oeuvre.getRole().name());
	    document.append("contenu", oeuvre.getContenu());

	    // On ajoute la nouvelle oeuvre dans la collection oeuvre
	    collection.insertOne(document);
	}
    }

    // Insère une formation en base
    public static void insererFormationEnBase(Formation formation) {
	Document document = new Document();
	Document query;
	long count;
	MongoCollection<Document> collection = mdb.getCollection("formation");

	// On teste si elle existe déjà dans la BDD (via le nom)
	// Requête BDD qui liste tous les noms égaux à ceux de la formation en param
	query = new Document("nom", formation.getNom());
	count = collection.countDocuments(query);

	System.out.println("Requête : " + count);
	collection.find(query).limit(5).forEach(element -> System.out.println(element));

	// Si la formation n'existe pas :
	if (count == 0) {
	    // On créé une liste de documents pour le ou les universite(s)
	    List<Document> universites = new ArrayList<Document>();
	    for (int i = 0; i < formation.getUniversites().size(); i++) {
		universites.add(new Document("nom", formation.getUniversites().get(i)));
	    }

	    // Creation du document à inserer
	    document.append("nom", formation.getNom());
	    document.append("niveau", formation.getNiveau());
	    document.append("nbPlace", formation.getNbPlace());
	    document.append("universites", universites);

	    // On ajoute la nouvelle formation dans la collection formation
	    collection.insertOne(document);
	}
    }

    // Insère un commentaire en base
    public static void insererCommentaireEnBase(Commentaire commentaire) {
	Document document = new Document();
	Document query;
	long count;
	MongoCollection<Document> collection = mdb.getCollection("commentaire");

	// On teste s'il existe déjà dans la BDD (via titreOeuvre + login +
	// datePublication)
	// Requête BDD qui liste tous les titres, logins et dates de publication égales
	// à ceux du commentaire en param

	// db.commentaire.find({"oeuvre": com.getOeuvre(), "datePublication":
	// com.getDatePublication(), "login": com.getLogin()})
	query = new Document("oeuvre", commentaire.getOeuvre())
		.append("datePublication", MongoDBConnexion.localDatetoString(commentaire.getDatePublication()))
		.append("login", commentaire.getLogin());
	count = collection.countDocuments(query);

	System.out.println("Requête : " + count);
	collection.find(query).limit(5).forEach(element -> System.out.println(element));

	// S'il n'existe pas :
	if (count == 0) {
	    // On créé le document à insérer
	    document.append("oeuvre", commentaire.getOeuvre());
	    document.append("login", commentaire.getLogin());
	    document.append("datePublication", MongoDBConnexion.localDatetoString(commentaire.getDatePublication()));
	    document.append("note", commentaire.getNote());
	    document.append("texte", commentaire.getTexte());

	    // On l'insère dans la collection commentaire
	    collection.insertOne(document);
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
		String[] lineTab = line.split(": ");
		if (lineTab[0].equals("Titre")) {
		    oeuvre.setTitre(lineTab[1]);
		}

		else if (lineTab[0].equals("Auteurs")) {
		    if (lineTab[1].contains(",")) {
			String[] auteurs;
			auteurs = lineTab[1].split(", ");
			for (int i = 0; i < auteurs.length; i++) {
			    // TODO Transformer string en auteur
			    oeuvre.getAuteurs().add(auteurs[i]);
			}
		    }
		    else {
			// TODO Transformer string en auteur
			oeuvre.getAuteurs().add(lineTab[1]);
		    }
		}

		else if (lineTab[0].equals("Pages")) {
		    oeuvre.setNbPage(Integer.parseInt(lineTab[1]));

		}

		else if (lineTab[0].equals("Publication")) {
		    oeuvre.setDatePubli(MongoDBConnexion.stringToLocalDate(lineTab[1]));

		}

		else if (lineTab[0].equals("Theme")) {
		    oeuvre.setTheme(lineTab[1]);
		}

		else if (lineTab[0].equals("Roles")) {
		    oeuvre.setRole(MongoDBConnexion.stringToRole(lineTab[1]));
		}

		else if (lineTab[0].equals("Formations")) {
		    // TODO
		    continue;
		}

		else if (lineTab[0].equals("Universites")) {
		    // TODO
		    continue;
		}

		else if (isContenu && !lineTab[0].equals("Contenu:")) {
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
	    // TODO
	}
    }

    public static void viderBDD() {
	// On vide chaque collection
	mdb.getCollection("oeuvre").drop();
	mdb.createCollection("oeuvre");

	mdb.getCollection("utilisateur").drop();
	mdb.createCollection("utilisateur");

	mdb.getCollection("formation").drop();
	mdb.createCollection("formation");

	mdb.getCollection("commentaire").drop();
	mdb.createCollection("commentaire");
    }
}