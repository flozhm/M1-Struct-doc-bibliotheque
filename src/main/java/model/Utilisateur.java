package model;

import java.util.List;

public class Utilisateur {

	private String login;
	private String nom;
	private String prenom;
	private List<FormationUtilisateur> universiteRattachement;
	private Role role;
	
	
	public Utilisateur(String login, String nom, String prenom, List<FormationUtilisateur> universiteRattachement, Role role) {
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.universiteRattachement = universiteRattachement;
		this.role = role;
	}
	
	
	public String getLogin() {
		return login;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public List<FormationUtilisateur> getUniversiteRattachement() {
		return universiteRattachement;
	}
	public Role getRole() {
		return role;
	}
	
	

}
