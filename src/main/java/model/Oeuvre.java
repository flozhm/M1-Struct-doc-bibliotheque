package model;

import java.util.ArrayList;
import java.util.List;


public class Oeuvre {
	
	private String titre;
	private List<Auteur> auteurs;
	private int nbPage;
	private String datePubli;
	private String theme;
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
		return "Oeuvre\n- titre: " + titre + "\nauteurs: " + auteurs + "\nnbPage: " + nbPage + "\ndatePubli: "
				+ datePubli + "\nTheme: " + theme + "\nrole= " + role + "\ncontenu=" + contenu + "\n------------";
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<Auteur> getAuteurs() {
		return auteurs;
	}

	public void setAuteurs(List<Auteur> auteurs) {
		this.auteurs = auteurs;
	}

	public int getNbPage() {
		return nbPage;
	}

	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}

	public String getDatePubli() {
		return datePubli;
	}

	public void setDatePubli(String datePubli) {
		this.datePubli = datePubli;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	


}
