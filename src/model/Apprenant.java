package model;
import java.io.Serializable;
import java.util.*;

public class Apprenant extends Utilisateur implements Serializable {
	private Date dateDeNaissance;
	private String adresse;
	private Groupe groupe;
	private ArrayList<Quiz> quizzAcomp;
	private ArrayList<Quiz> quizzNonAcompli;
	private ArrayList<Quiz> quizzExpiré;
	
	
	/**
	 * the differents methods 
	 */
	
	public void afficherQuiz(int numQuiz)
	{
		
	}
	public Apprenant(String identifiant, String mdp,String nom,String prenom,Date dateDeNaissance, String adresse, Groupe groupe) {
		super(identifiant,mdp,nom,prenom);
		this.dateDeNaissance = dateDeNaissance;
		this.adresse = adresse;
		this.groupe = groupe;
		quizzAcomp=new ArrayList<Quiz>();
		 quizzNonAcompli=new ArrayList<Quiz>();
		 quizzExpiré=new ArrayList<Quiz>();
	}
	public void ajouterQuizAcomp(Quiz quiz) {
		this.quizzAcomp.add(quiz);
	}
	public void ajouterQuizNonAcomp(Quiz quiz) {
		this.quizzNonAcompli.add(quiz);
	}
	public void ajouterQuizExpiré(Quiz quiz) {
		this.quizzExpiré.add(quiz);
	}
	public void sauvgarderRep()
	{

	}
	/**
	 * 
	 * @param numQuiz : le numero du quizz dans le tableau des quizzNonAcompli
	 * @return le quiz en question
	 */
	public Quiz choisirQuiz(int numQuiz)
	{
		//on peut afficher ses question/reponses ici ou bien dans le main si on veut "pour le scenario"
		return(null);
	}
	
	public void validerQuiz(Quiz quiz)
	{
		
	}
	public void consultationActiviteApprenant() {
		Quiz quiz;
		System.out.println("L'activité de l'apprenant "+super.getNom()+" est la suivante :");
		System.out.println("Les quizz accomplis:");
		Iterator<Quiz> it=this.quizzAcomp.iterator();
		while(it.hasNext()) {
			quiz=it.next();
			System.out.println("--->"+quiz.getNom() +" avec taux d'accomplissement de "+quiz.getTauxAccomplissement());
		}
		System.out.println("Les quizz non accomplis:");
		Iterator<Quiz> it1=this.quizzNonAcompli.iterator();
		while(it1.hasNext()) {
			quiz=it1.next();
			System.out.println("--->"+quiz.getNom() +" avec taux d'accomplissement de "+quiz.getTauxAccomplissement());
		}
		System.out.println("Les quizz Expiré:");
		Iterator<Quiz> it2=this.quizzExpiré.iterator();
		while(it2.hasNext()) {
			quiz=it2.next();
			System.out.println("--->"+quiz.getNom() +" avec taux d'accomplissement de "+quiz.getTauxAccomplissement());
		}
		
	}
}
