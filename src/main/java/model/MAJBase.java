package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MAJBase {

	// Permet de récupérer
	public static File[] recupFichiers() {

		File repertoire = new File("./import"); // Répertoire dans lequel sont stockés les fichiers
		File[] files = repertoire.listFiles(); // Liste des fichiers

		return files;
	}

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

	//
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
					oeuvre.titre = line.split(": ")[1];
				} else if (line.split(": ")[0].equals("Auteurs")) {
					if (line.split(": ")[1].contains(",")) {
						String[] auteurs;
						auteurs = line.split(": ")[1].split(", ");
						for (int i = 0; i < auteurs.length; i++) {
							oeuvre.auteurs.add(auteurs[i]);
						}
					} else {
						oeuvre.auteurs.add(line.split(": ")[1]);
					}

				} else if (line.split(": ")[0].equals("Pages")) {
					oeuvre.nbPage = Integer.parseInt(line.split(": ")[1]);

				} else if (line.split(": ")[0].equals("Publication")) {
					oeuvre.datePubli = line.split(": ")[1];

				} else if (line.split(": ")[0].equals("Theme")) {
					oeuvre.theme = line.split(": ")[1];
				} else if (line.split(": ")[0].equals("Roles")) {
					oeuvre.role = line.split(": ")[1];
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
			oeuvre.toString();
			// System.out.println("Contenu du contenu: ");
			// System.out.println(sb.toString());
			oeuvre.contenu = sb.toString();
			System.out.println(oeuvre.toString());

		} catch (

		IOException e) {
			// A voir ce que l'on fait ici
		}
	}

	// Déplacer fichier

	// Vider base
	public static void viderBDD() {

		// Connexion à la BDD
		// On vide chaque table

	}

}
