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

    /**
     * Récupère la liste des fichiers du dosssier "import"
     * 
     * @return tableau de fichiers
     */
    public static File[] recupFichiers() {
	File repertoire = new File("./import");
	return repertoire.listFiles();
    }

    /**
     * Insert un utilisateur en base de données
     * 
     * @param utilisateur Utilisateur
     */
    public static void insererUtilisateur(Utilisateur utilisateur) {
	// Récupération de la base utilisateur
	MongoCollection<Document> collection = mdb.getCollection("utilisateur");

	// On teste s'il existe déjà dans la BDD (via nom+prenom)
	Document query = new Document("nom", utilisateur.getNom()).append("prenom", utilisateur.getPrenom());
	long count = collection.countDocuments(query);

	// On l'ajoute s'il n'existe pas
	// On le met à jour sinon
	if (count == 0) {
	    ajoutUtilisateur(utilisateur);
	}
	else {
	    majUtilisateur(utilisateur);
	}
    }

    /**
     * Ajoute un utilisateur
     * 
     * @param user
     */
    private static void ajoutUtilisateur(Utilisateur user) {
	// Récupération de la base utilisateur
	MongoCollection<Document> collection = mdb.getCollection("utilisateur");

	// S'il n'existe pas dans la BDD, on itère sur les login
	Document query = new Document("login",
		user.getNom().toLowerCase() + user.getPrenom().toLowerCase().substring(0, 1));
	long count = collection.countDocuments(query);
	int cpt = 0;
	boolean numero = false;
	while (count == 1) {
	    cpt++;
	    query = new Document("login",
		    user.getNom().toLowerCase() + user.getPrenom().toLowerCase().substring(0, 1) + cpt);
	    count = collection.countDocuments(query);
	    numero = true;
	}
	Document document = new Document();
	if (numero == true) {
	    document.append("login",
		    user.getNom().toLowerCase() + user.getPrenom().toLowerCase().substring(0, 1) + cpt);
	}
	else {
	    document.append("login", user.getNom().toLowerCase() + user.getPrenom().toLowerCase().substring(0, 1));
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
	document.append("role", MongoDBConnexion.roletoString(user.getRole()));

	// On ajoute le nouvel utilisateur dans la collection utilisateur
	collection.insertOne(document);

    }

    /**
     * Met à jour un utilisateur
     * 
     * @param user
     */
    private static void majUtilisateur(Utilisateur user) {
	// Récupération de la base utilisateur
	MongoCollection<Document> collection = mdb.getCollection("utilisateur");

	// On met à jour l'université de rattachement
	collection.updateOne(new Document("nom", user.getNom()).append("prenom", user.getPrenom()),
		new Document("$set", new Document("universiteRattachement", user.getUniversiteRattachement())));

	// Mise à jour du role de l'utilisateur en lien avec sa dernière oeuvre publiée
	collection.updateOne(new Document("nom", user.getNom()).append("prenom", user.getPrenom()),
		new Document("$set", new Document("role", MongoDBConnexion.roletoString(user.getRole()))));

	// On récupère les formations associées à notre utilisateur en base
	List<FormationUtilisateur> formationsUtilisateurs = new ArrayList<FormationUtilisateur>();
	Document query = new Document("nom", user.getNom()).append("prenom", user.getPrenom());
	Document utilisateur = collection.find(query).first();
	utilisateur.getList("formations", Document.class)
		.forEach(formationUtilisateur -> formationsUtilisateurs.add(new FormationUtilisateur(
			formationUtilisateur.getString("nom"), formationUtilisateur.getInteger("anneeEntree"),
			formationUtilisateur.getInteger("anneeSortie"))));

	// On combine la liste des anciennes et nouvelles formations
	boolean existe;
	for (int i = 0; i < user.getFormation().size(); i++) {
	    existe = false;
	    for (int j = 0; j < formationsUtilisateurs.size() && !existe; j++) {
		if (user.getFormation().get(i).equals(formationsUtilisateurs.get(j))) {
		    existe = true;
		}
	    }
	    if (!existe) {
		formationsUtilisateurs.add(user.getFormation().get(i));
	    }
	}

	// On transforme la liste de formations utilisateur en liste de documents
	List<Document> majFormation = new ArrayList<Document>();
	for (int i = 0; i < formationsUtilisateurs.size(); i++) {
	    majFormation.add(new Document("nom", formationsUtilisateurs.get(i).getNom())
		    .append("anneeEntree", formationsUtilisateurs.get(i).getAnneeEntree())
		    .append("anneeSortie", formationsUtilisateurs.get(i).getAnneeSortie()));
	}

	// On met à jour les formations de l'utilisateur
	collection.updateOne(new Document("nom", user.getNom()).append("prenom", user.getPrenom()),
		new Document("$set", new Document("formations", majFormation)));

    }

    /**
     * Insere une oeuvre
     * 
     * @param oeuvre Oeuvre
     */
    public static void insererOeuvre(Oeuvre oeuvre) {
	// Récupération de la base utilisateur
	MongoCollection<Document> collection = mdb.getCollection("oeuvre");

	// On teste s'il existe déjà dans la BDD (via le titre de l'oeuvre)
	// Requête BDD qui liste tous les titres égaux à ceux de l'oeuvre en param
	Document query = new Document("titre", oeuvre.getTitre());
	long count = collection.countDocuments(query);

	// Si l'oeuvre n'existe pas :
	if (count == 0) {
	    // On créé une liste de documents pour le ou les auteurs(s)
	    List<Document> auteurs = new ArrayList<Document>();
	    for (int i = 0; i < oeuvre.getAuteurs().size(); i++) {
		auteurs.add(new Document("nom", oeuvre.getAuteurs().get(i).getNom()).append("prenom",
			oeuvre.getAuteurs().get(i).getPrenom()));
	    }

	    // Creation du document à inserer
	    Document document = new Document();
	    document.append("titre", oeuvre.getTitre());
	    document.append("auteurs", auteurs);
	    document.append("nbPage", oeuvre.getNbPage());
	    document.append("datePublication", MongoDBConnexion.localDatetoString(oeuvre.getDatePubli()));
	    document.append("theme", oeuvre.getTheme());
	    document.append("role", MongoDBConnexion.roletoString(oeuvre.getRole()));
	    document.append("contenu", oeuvre.getContenu());

	    // On ajoute la nouvelle oeuvre dans la collection oeuvre
	    collection.insertOne(document);
	}
    }

    // Insère une formation
    public static void insererFormation(Formation formation) {
	// Récupération de la base utilisateur
	MongoCollection<Document> collection = mdb.getCollection("formation");

	// On teste si elle existe déjà dans la BDD (via le nom)
	// Requête BDD qui liste tous les noms égaux à ceux de la formation en param
	Document query = new Document("nom", formation.getNom());
	long count = collection.countDocuments(query);

	// Si la formation n'existe pas :
	if (count == 0) {
	    // On créé une liste de documents pour le ou les universite(s)
	    List<Document> universites = new ArrayList<Document>();
	    for (int i = 0; i < formation.getUniversites().size(); i++) {
		universites.add(new Document("nom", formation.getUniversites().get(i)));
	    }

	    // Creation du document à inserer
	    Document document = new Document();
	    document.append("nom", formation.getNom());
	    document.append("niveau", formation.getNiveau());
	    document.append("nbPlace", formation.getNbPlace());
	    document.append("universites", universites);

	    // On ajoute la nouvelle formation dans la collection formation
	    collection.insertOne(document);
	}
    }

    // Insère un commentaire
    public static void insererCommentaire(Commentaire commentaire) {
	// Récupération de la base utilisateur
	MongoCollection<Document> collection = mdb.getCollection("commentaire");

	// On teste s'il existe déjà dans la BDD (via titreOeuvre + login +
	// datePublication)
	Document query = new Document("oeuvre", commentaire.getOeuvre())
		.append("datePublication", MongoDBConnexion.localDatetoString(commentaire.getDatePublication()))
		.append("login", commentaire.getLogin());
	long count = collection.countDocuments(query);

	// S'il n'existe pas :
	if (count == 0) {
	    // On créé le document à insérer
	    Document document = new Document();
	    document.append("oeuvre", commentaire.getOeuvre());
	    document.append("login", commentaire.getLogin());
	    document.append("datePublication", MongoDBConnexion.localDatetoString(commentaire.getDatePublication()));
	    document.append("note", commentaire.getNote());
	    document.append("texte", commentaire.getTexte());

	    // On l'insère dans la collection commentaire
	    collection.insertOne(document);
	}
    }

    /**
     * Parse un fichier et créer l'oeuvre associée
     * 
     * @param file fichier
     */
    public static void lireFichier(File file) {
	Oeuvre oeuvre = new Oeuvre();
	String universiteRattachement = "";
	List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	List<FormationUtilisateur> formationsUser = new ArrayList<FormationUtilisateur>();
	List<Formation> formations = new ArrayList<Formation>();

	try {
	    // Créer l'objet File Reader
	    FileReader fr = new FileReader(file);
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
			    oeuvre.getAuteurs().add(new Auteur(auteurs[i].split(" ")[1], auteurs[i].split(" ")[0]));
			    utilisateurs.add(new Utilisateur(auteurs[i].split(" ")[1], auteurs[i].split(" ")[0]));
			}
		    }
		    else {
			oeuvre.getAuteurs()
				.add(new Auteur(line.split(": ")[1].split(" ")[1], line.split(": ")[1].split(" ")[0]));
			utilisateurs.add(
				new Utilisateur(line.split(": ")[1].split(" ")[1], line.split(": ")[1].split(" ")[0]));
		    }

		}
		else if (line.split(": ")[0].equals("Pages")) {
		    oeuvre.setNbPage(Integer.parseInt(line.split(": ")[1]));

		}
		else if (line.split(": ")[0].equals("Publication")) {
		    oeuvre.setDatePubli(MongoDBConnexion.stringToLocalDate(line.split(": ")[1]));

		}
		else if (line.split(": ")[0].equals("Theme")) {
		    oeuvre.setTheme(line.split(": ")[1]);
		}
		else if (line.split(": ")[0].equals("Roles")) {
		    oeuvre.setRole(MongoDBConnexion.stringToRole(line.split(": ")[1].toLowerCase()));
		}
		else if (line.split(": ")[0].equals("Formations")) {
		    if (line.split(": ")[1].contains(",")) {
			String[] formation;
			formation = line.split(": ")[1].split(", ");
			for (int i = 0; i < formation.length; i++) {
			    formationsUser.add(new FormationUtilisateur(formation[i], 0, 0));
			    formations.add(new Formation(formation[i]));
			}
		    }
		    else {
			formationsUser.add(new FormationUtilisateur(line.split(": ")[1], 0, 0));
			formations.add(new Formation(line.split(": ")[1]));
		    }

		}
		else if (line.split(": ")[0].equals("Universites")) {
		    universiteRattachement = line.split(": ")[1];

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
	    oeuvre.setContenu(sb.toString());
	    for (int i = 0; i < utilisateurs.size(); i++) {
		utilisateurs.get(i).setUniversiteRattachement(universiteRattachement);
		utilisateurs.get(i).setFormation(formationsUser);
		utilisateurs.get(i).setRole(oeuvre.getRole());
		insererUtilisateur(utilisateurs.get(i));
	    }
	    for (int i = 0; i < formations.size(); i++) {
		formations.get(i).getUniversites().add(universiteRattachement);
		insererFormation(formations.get(i));
	    }
	    insererOeuvre(oeuvre);

	}
	catch (IOException e) {
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