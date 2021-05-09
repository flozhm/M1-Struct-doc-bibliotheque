package model;

import java.util.ArrayList;
import java.util.List;

public class Oeuvre {

	public String titre;
    public List<String> auteurs;
    public int nbPage;
    public String datePubli;
    public String role;
    public String contenu;

    public Oeuvre() {
        this.auteurs = new ArrayList<>();
    }
    

	public Oeuvre(String titre, List<String> auteurs, int nbPage, String datePubli, String role, String contenu) {
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
    
    
	
}
