package model;

import java.io.Serializable;

public class Utilisateur implements Comparable<Utilisateur>,Serializable {
	
	private String Identifiant;
	private String MotDePasse;
	private String Nom;
	private String Prenom;
	
	public Utilisateur()
	{
		
	}
	public Utilisateur(String identifiant, String motDePasse, String nom, String prenom) {
		
		Identifiant = identifiant;
		MotDePasse = motDePasse;
		Nom = nom;
		Prenom = prenom;
	}
	public String getIdentifiant() {
		return Identifiant;
	}
	public void setIdentifiant(String identifiant) {
		Identifiant = identifiant;
	}
	public String getMotDePasse() {
		return MotDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		MotDePasse = motDePasse;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	
	public int compareTo(Utilisateur util) {
		String identifiant=util.getIdentifiant();
		return this.Identifiant.compareTo(identifiant);
	}
	public int hashCode() {
		return this.Identifiant.hashCode();
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Utilisateur)) return false;
		return Identifiant.equals(((Utilisateur)o).getIdentifiant());
	}
	
}
