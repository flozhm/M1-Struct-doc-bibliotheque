package model;

import java.util.List;

public class Utilisateur {

	private String login;
	private String nom;
	private String prenom;
	private List<String> universiteRattachement;
	private List<FormationUtilisateur> formation;
	private Role role;


	public Utilisateur(String nom, String prenom, List<String> universiteRattachement,
			           List<FormationUtilisateur> formation, Role role) {
		this.login = null;
		this.nom = nom;
		this.prenom = prenom;
		this.universiteRattachement = universiteRattachement;
		this.formation = formation;
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

	public List<String> getUniversiteRattachement() {
		return universiteRattachement;
	}
	public List<FormationUtilisateur> getFormation() {
		return formation;
	}


	public Role getRole() {
		return role;
	}
	
	

}
