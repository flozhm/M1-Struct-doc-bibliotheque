package model;

public class Commentaire {

	private String login;
	private String datePublication;
	private double note;
	private String texte;
	
	
	public Commentaire(String login, String datePublication, double note, String texte) {
		this.login = login;
		this.datePublication = datePublication;
		this.note = note;
		this.texte = texte;
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
