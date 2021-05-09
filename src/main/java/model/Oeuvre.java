package model;

import java.util.ArrayList;
import java.util.List;

public class Oeuvre {

	private String titre;
	private List<Auteur> auteurs;
	private int nbPage;
	private String datePubli;
	private String role;
	private String contenu;

    public Oeuvre() {
        this.auteurs = new ArrayList<Auteur>();
    }

	public Oeuvre(String titre, List<Auteur> auteurs, int nbPage, String datePubli, String role, String contenu) {
		this.titre = titre;
		this.auteurs = auteurs;
		this.nbPage = nbPage;
		this.datePubli = datePubli;
		this.role = role;
		this.contenu = contenu;
	}


	@Override
	public String toString() {
		return "Oeuvre- titre: " + titre +
				       "\nauteurs: " + auteurs +
				       "\nnbPage: " + nbPage +
				       "\ndatePubli: " + datePubli + 
				       "\nrole=" + role + 
				       "\ncontenu=" + contenu
				       + "\n------------";
	}
	

	public String getTitre() {
		return titre;
	}

	public List<Auteur> getAuteurs() {
		return auteurs;
	}

	public int getNbPage() {
		return nbPage;
	}

	public String getDatePubli() {
		return datePubli;
	}

	public String getRole() {
		return role;
	}

	public String getContenu() {
		return contenu;
	}	

}
