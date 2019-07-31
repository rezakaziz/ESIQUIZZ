package model;
import java.io.Serializable;

import java.time.LocalDate;
import java.util.*;
public class Quiz implements Serializable{

	private String Nom;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private ArrayList<Notion> notion;
	private float score;
	private float tauxAccomplissement;
	
	
	public Quiz(String nom, LocalDate dateDebut, LocalDate dateFin) {
		super();
		Nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	public float getTauxAccomplissement() {
		return tauxAccomplissement;
	}
	public void setTauxAccomplissement(float tauxAccomplissement) {
		this.tauxAccomplissement = tauxAccomplissement;
	}
	/**
	 * the constructors and methods 
	 */
	
	public Quiz()
	{
		this.notion=new ArrayList<Notion>();
	}
	public Quiz(String nom, LocalDate dateDebut, LocalDate dateFin, float score) {
		
		Nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.score = score;
		this.notion=new ArrayList<Notion>();
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public LocalDate getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(LocalDate localDate) {
		this.dateDebut = localDate;
	}
	public LocalDate getDateFin() {
		return dateFin;
	}
	public void setDateFin(LocalDate localDate) {
		this.dateFin = localDate;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public void ajoutNotions(Notion notion, int nbQuestions ) {
		//return this.notion.add(notion);
		int j,i=0;
		Notion no1;
		no1=new Notion(notion.getDescriptionNotion());
		while(i<nbQuestions) {
			j=(int) (Math.random() * ( notion.getQuestions().size() ));
			if(no1.getQuestions().contains(notion.getQuestion(j))) {
				continue;
			}
			else {
				no1.ajouterQuestion(notion.getQuestion(j));
				i++;
			}
		}
		this.notion.add(no1);
	}
	public boolean supprimerNotion(Notion notion) {
		return this.notion.remove(notion);
	}
	
	public void afficherQuiz() {
		Iterator<Notion> it= notion.iterator();
		System.out.println("***************************");
		while (it.hasNext()) {
			Notion not=it.next();
			not.afficheQuestions();
			
		}
	}
	public void repondre(Question ques,ArrayList<String> rep) {
		Iterator<Notion> it= notion.iterator();
		boolean stop=false;
		Notion no1 = null;
		while(it.hasNext() && !stop) {
			no1=it.next();
			if (no1.getQuestions().contains(ques)) {
				stop=true;
			}
		}
		int i=0;
		Iterator<Question> it1= no1.getQuestions().iterator();
		while(it1.hasNext()) {

			Question quu=it1.next();
			if (quu==ques) {
				for(String s : rep) {
					if (ques instanceof Qcm ) ((Qcm)ques).repondre(s);
					if (ques instanceof Qcu ) ((Qcu)ques).repondre(s);
					if (ques instanceof Qo) ((Qo)ques).repondre(s);
				}
			}
			i++;
		}
		//calculer le taux d accomplissement
		this.tauxAccomplissement=tauxAccomplissement();
	}
	
	public ArrayList<Notion> getNotion() {
		return notion;
	}
	public void setNotion(ArrayList<Notion> notion) {
		this.notion = notion;
	}
	private int tauxAccomplissement() {
		Iterator<Notion> it= notion.iterator(); 
		Notion no1;
		Question ques;
		int nbQues=0,nbRep=0;
		while (it.hasNext()) {
			no1=it.next();
			Iterator<Question> it1=no1.getQuestions().iterator();
			while (it1.hasNext()) {
				ques=it1.next();
				nbQues++;
				if (ques instanceof Qcm ) {
					if((((Qcm)ques).getReponses().size()) > 0)nbRep++;
				}
				if (ques instanceof Qcu ) {
					if(((Qcu)ques).getReponse()!=null)nbRep++;
				}
				if (ques instanceof Qo) {
					if(((Qo)ques).getReponse()!=null)nbRep++;
				}
			}
		}
		return (nbRep*100)/nbQues;
	}
	
	public float evaluationQuiz() {
		Iterator<Notion> it= notion.iterator(); 
		Notion no1;
		Question ques;
		int nbQues=0;
		float cumul=0;
		while (it.hasNext()) {
			no1=it.next();
			Iterator<Question> it1=no1.getQuestions().iterator();
			while (it1.hasNext()) {
				ques=it1.next();
				nbQues++;
				if (ques instanceof Qcm ) {
					cumul+=((Qcm)ques).evaluer();
					
				}
				if (ques instanceof Qcu ) {
					cumul+=((Qcu)ques).evaluer();
				}
				if (ques instanceof Qo) {
					cumul+=((Qo)ques).evaluer();
				}
			}
		}
		return cumul/nbQues;
	}
	
	
	public String etatQuiz() {
		
	  
	     
		if(this.dateFin.compareTo(LocalDate.now())==-1) {
			return "Expiré";
		}
		if(this.tauxAccomplissement<100) {
			return "Non Accomplis";
		}
		if (this.tauxAccomplissement==100) {
			return "Accomplis";
		}
		return null;
	}
}
