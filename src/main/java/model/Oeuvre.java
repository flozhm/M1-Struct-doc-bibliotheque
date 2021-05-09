package model;

import java.util.ArrayList;
import java.util.List;

public class Oeuvre {
	public String titre;
	public List<String> auteurs;
	public int nbPage;
	public String datePubli;
	public String theme;
	public String role;
	public String contenu;

	public Oeuvre() {
		this.auteurs = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Oeuvre\n- titre: " + titre + "\nauteurs: " + auteurs + "\nnbPage: " + nbPage + "\ndatePubli: "
				+ datePubli + "\nTheme: " + theme + "\nrole= " + role + "\ncontenu=" + contenu + "\n------------";
	}

}
