package model;

import java.util.ArrayList;
import java.util.List;

public class Formation {

    private String	 nom;
    private String	 niveau;
    private int		 nbPlace;
    private List<String> universites;

    public Formation(String nom, String niveau, int nbPlace, List<String> universites) {
	this.nom = nom;
	this.niveau = niveau;
	this.nbPlace = nbPlace;
	this.universites = universites;
    }

    public Formation(String nom) {
	this.nom = nom;
	this.universites = new ArrayList<>();
    }

    public String getNom() {
	return nom;
    }

    public String getNiveau() {
	return niveau;
    }

    public int getNbPlace() {
	return nbPlace;
    }

    public List<String> getUniversites() {
	return universites;
    }

}
