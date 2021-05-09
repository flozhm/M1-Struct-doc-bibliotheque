package model;

public class Commentaire {

	private String login;
	private String datePublication;
	private int note;
	private String texte;
	
	
	public Commentaire(String login, String datePublication, int note, String texte) {
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
	public int getNote() {
		return note;
	}
	public String getTexte() {
		return texte;
	}
	
	

}
