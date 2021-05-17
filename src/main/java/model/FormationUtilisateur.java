package model;

public class FormationUtilisateur {

	private String nom;
	private int anneeEntree;
	private int anneeSortie;

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

	@Override
	public String toString() {
		return "FormationUtilisateur [nom=" + nom + ", anneeEntree=" + anneeEntree + ", anneeSortie=" + anneeSortie
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormationUtilisateur other = (FormationUtilisateur) obj;
		if (anneeEntree != other.anneeEntree)
			return false;
		if (anneeSortie != other.anneeSortie)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}
