package model;

public class Commentaire {

	private String oeuvre;
	private String login;
	private String datePublication;
	private double note;
	private String texte;
	
	
	public Commentaire(String oeuvre, String login, String datePublication, double note, String texte) {
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
	public String getDatePublication() {
		return datePublication;
	}
	public double getNote() {
		return note;
	}
	public String getTexte() {
		return texte;
	}
	
	

}
