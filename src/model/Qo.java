package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Qo extends Question implements Serializable{
	
	public Qo(String enonce) {
		super(enonce);
		// TODO Auto-generated constructor stub
	}
	private ArrayList<String> bonneReponsesProposé;
	private String reponse;
	
	
	public float evaluer()
	{
		/**
		 * la redifinition que on doit devlopper selon 
		 * la notation d'une Question ouverte donnée 
		 * dans le livrable
		 */
		if (bonneReponsesProposé.contains(reponse))return 1;
		return 0;
	}
	public ArrayList<String> getBonneReponsesProposé() {
		return bonneReponsesProposé;
	}
	public void setBonneReponsesProposé(ArrayList<String> bonneReponsesProposé) {
		this.bonneReponsesProposé = bonneReponsesProposé;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public void ajoutProposition(String propo) {
		/**
		 * ajouter une propostion au tableau de propos 
		 */
		this.bonneReponsesProposé.add(propo);
	}
	public void repondre(String rep) {
		// TODO Auto-generated method stub
		this.reponse=rep;
	}

	
}
