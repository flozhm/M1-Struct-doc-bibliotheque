package model;

public class FormationUtilisateur {

    private String nom;
    private int	   anneeEntree;
    private int	   anneeSortie;

    public FormationUtilisateur(String nom, int anneeEntree, int anneeSortie) {
	this.nom = nom;
	this.anneeEntree = anneeEntree;
	this.anneeSortie = anneeSortie;
    }

    public String getNom() {
	return nom;
    }

    public int getAnneeEntree() {
	return anneeEntree;
    }

    public int getAnneeSortie() {
	return anneeSortie;
    }

}
