package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Oeuvre {

    private String	 titre;
    private List<Auteur> auteurs;
    private int		 nbPage;
    private LocalDate	 datePubli;
    private LocalDate	 dateDerCommentaire = null;	   // Pas dans la BDD (calculée)
    private String	 theme;
    private Role	 role;
    private String	 contenu;
    private String	 note		    = "Non notée"; // Pas dans la BDD (calculée)

    public Oeuvre() {
	this.auteurs = new ArrayList<Auteur>();
    }

    public Oeuvre(String titre, List<Auteur> auteurs, int nbPage, LocalDate datePubli, Role role, String theme,
	    String contenu) {
	this.titre = titre;
	this.auteurs = auteurs;
	this.nbPage = nbPage;
	this.datePubli = datePubli;
	this.role = role;
	this.theme = theme;
	this.contenu = contenu;
	calculerNote();
	calculerDateDerCommentaire();
    }

    public String getTitre() {
	return titre;
    }

    public void setTitre(String titre) {
	this.titre = titre;
    }

    public List<Auteur> getAuteurs() {
	return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
	this.auteurs = auteurs;
    }

    public int getNbPage() {
	return nbPage;
    }

    public void setNbPage(int nbPage) {
	this.nbPage = nbPage;
    }

    public LocalDate getDatePubli() {
	return datePubli;
    }

    public void setDatePubli(LocalDate datePubli) {
	this.datePubli = datePubli;
    }

    public LocalDate getDateDerCommentaire() {
	return dateDerCommentaire;
    }

    public String getTheme() {
	return theme;
    }

    public void setTheme(String theme) {
	this.theme = theme;
    }

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    public String getContenu() {
	return contenu;
    }

    public void setContenu(String contenu) {
	this.contenu = contenu;
    }

    public String getNote() {
	return note;
    }

    /**
     * Récupère la note moyenne de l"oeuvre
     */
    public void calculerNote() {
	MongoDBConnexion mdb = new MongoDBConnexion();
	Double newNote = mdb.getNote(this);
	if (Double.isNaN(newNote)) {
	    note = "Non notée";
	}
	else {
	    note = newNote.toString();
	}
    }

    /**
     * Récupère la date du dernier commentaire
     */
    public void calculerDateDerCommentaire() {
        MongoDBConnexion mdb = new MongoDBConnexion();
        LocalDate newDateDerCommentaire = mdb.getDateDerCommentaire(this);
        if (newDateDerCommentaire != null) {
            dateDerCommentaire = newDateDerCommentaire;
        }
    }

}
