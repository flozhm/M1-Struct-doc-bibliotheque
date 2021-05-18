package model;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {

    private String		       login;
    private String		       nom;
    private String		       prenom;
    private String		       universiteRattachement;
    private List<FormationUtilisateur> formation;
    private Role		       role;

    public Utilisateur(String nom, String prenom, String universiteRattachement, List<FormationUtilisateur> formation,
	    Role role) {
	this.login = null;
	this.nom = nom;
	this.prenom = prenom;
	this.universiteRattachement = universiteRattachement;
	this.formation = formation;
	this.role = role;
    }

    public Utilisateur(String nom, String prenom) {
	this.nom = nom;
	this.prenom = prenom;
	this.formation = new ArrayList<FormationUtilisateur>();
    }

    public Utilisateur(String login, String nom, String prenom, String universiteRattachement,
	    List<FormationUtilisateur> formation, Role role) {
	this.login = login;
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

    public String getUniversiteRattachement() {
	return universiteRattachement;
    }

    public List<FormationUtilisateur> getFormation() {
	return formation;
    }

    public Role getRole() {
	return role;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    public void setPrenom(String prenom) {
	this.prenom = prenom;
    }

    public void setUniversiteRattachement(String universiteRattachement) {
	this.universiteRattachement = universiteRattachement;
    }

    public void setFormation(List<FormationUtilisateur> formation) {
	this.formation = formation;
    }

    public void setRole(Role role) {
	this.role = role;
    }

}
