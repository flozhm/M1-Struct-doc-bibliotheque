package model;

import java.time.LocalDate;

public class Commentaire {

	private String oeuvre;
	private String login;
	private LocalDate datePublication;
	private double note;
	private String texte;

	public Commentaire(String oeuvre, String login, LocalDate datePublication, double note, String texte) {
		this.oeuvre = oeuvre;
		this.login = login;
		this.datePublication = datePublication;
		this.note = note;
		this.texte = texte;
	}

	public String getOeuvre() {
		return oeuvre;
	}

	public String getLogin() {
		return login;
	}

	public LocalDate getDatePublication() {
		return datePublication;
	}

	public double getNote() {
		return note;
	}

	public String getTexte() {
		return texte;
	}

	@Override
	public String toString() {
		return "Commentaire [oeuvre=" + oeuvre + ", login=" + login + ", datePublication=" + datePublication + ", note="
				+ note + ", texte=" + texte + "]";
	}

}
