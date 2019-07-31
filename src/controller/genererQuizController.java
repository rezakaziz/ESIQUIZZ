package controller;


import java.io.BufferedOutputStream;
import java.io.File;

import java.io.FileOutputStream;

import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Formateur;
import model.Formation;
import model.Notion;
import model.Qcm;
import model.Qcu;
import model.Qo;
import model.Question;
import model.Quiz;

public class genererQuizController implements Initializable {

    @FXML
    private ListView<CheckBox> listCheckNotions;

    @FXML
    private ComboBox<String> listFormation;
    
    @FXML
    private TextField nomQuiz;

    @FXML
    private DatePicker dateDebutQuiz;

    @FXML
    private DatePicker dateFinQuiz;
    @FXML
    private Button validerNotionQuiz;
    
    @FXML
    private VBox affichQuiz;
    
    private Formateur form=null;//le formateur connecté
    ObservableList<String> data=FXCollections.observableArrayList();//pour le combobox
    ObservableList<CheckBox> datacheck=FXCollections.observableArrayList();//pour listview des checkbox
    
    
    
    /**
     * Valider les notions a générer
     * @param event
     */
    @FXML
    void validerNotionQuizClick(ActionEvent event) {
    		Quiz quiz=new Quiz();
    		quiz.setNom(nomQuiz.getText());
    		quiz.setDateDebut(dateDebutQuiz.getValue());
    		quiz.setDateFin(dateFinQuiz.getValue());
    		ArrayList<Notion> notionsQuiz=new ArrayList<Notion>();
    		//recuperer la formation
    		boolean arret=false;
    		int cpt=0;
    		ArrayList<Formation> formations= form.getFormations();
    		Iterator<Formation> it= formations.iterator();
    		Formation f=null;
    		while (it.hasNext() & arret==false) {
    			f=it.next();
    			if(f.getNom().equals(listFormation.getValue())) {
    				arret=true;
    				
    			}else cpt++;		
    		}
    		//creer arraylist des notion
    		String nomNotion=null;Notion n=null;boolean ar=false;
    		ObservableList<CheckBox> list=FXCollections.observableArrayList();
    		list=listCheckNotions.getItems();
    		for(CheckBox a: list) {
    			if (a.isSelected()) {
    				nomNotion=a.getText();
    				ArrayList<Notion> notions=f.getNotions();
    				ar=false;
    				Iterator<Notion> it2= notions.iterator();
    				while (it2.hasNext() & !ar) {
    					n=it2.next();
    					if(n.getDescriptionNotion().equals(nomNotion)) {
    						notionsQuiz.add(n);
    						ar=true;
    					}
    							
    				}
    				
    			}
    		}
    		
    		quiz=this.form.genererQuiz(f, notionsQuiz, quiz);
    		f.ajoutQuiz(quiz);
    		
    		f.affichequiz();
    		//faire l'affichage de quiz
    		int numQuestion=1;
    		int numReponse=1;
    		ArrayList<Notion> quizNot=quiz.getNotion();
    		for(Notion n1:quizNot) {
    			ArrayList<Question> ques=n1.getQuestions();
    			for (Question q:ques) {
    				//si q est Qcm
    				if (q instanceof Qcm ) {
    					Label enoncé=new Label(numQuestion+"- "+q.getEnonce());
    					numQuestion++;
       				 	affichQuiz.getChildren().add(enoncé);
       				 	Qcm q1=(Qcm)q;
       				 	for(String s1:q1.getReponsesFausses()) {
       				 		CheckBox rep=new CheckBox(numReponse+"- "+s1);
       				 		numReponse++;
       				 		affichQuiz.getChildren().add(rep);
       				 	}
       				 	for(String s2:q1.getReponsesJustes()) {
       				 		CheckBox rep=new CheckBox(numReponse+"- "+s2);
       				 		numReponse++;
       				 		affichQuiz.getChildren().add(rep);
       				 	}
       				 	numReponse=1;
    				}else if (q instanceof Qcu ){
    					Label enoncé=new Label(numQuestion+"- "+q.getEnonce());
    					numQuestion++;
       				 	affichQuiz.getChildren().add(enoncé);
       				 	Qcu q1=(Qcu)q;
       				 	for(String s1:q1.getReponsesFausses()) {
       				 		CheckBox rep=new CheckBox(numReponse+"- "+s1);
       				 		numReponse++;
       				 		affichQuiz.getChildren().add(rep);
       				 	}
       				 	
       				 		CheckBox rep=new CheckBox(numReponse+"- "+q1.getReponseJuste());
       				 		affichQuiz.getChildren().add(rep);
       				 	
    				}else if (q instanceof Qo ) {
    					Label enoncé=new Label(numQuestion+"- "+q.getEnonce());
    					numQuestion++;
       				 	affichQuiz.getChildren().add(enoncé);
       				 	Qo q1=(Qo)q;
       				 	TextArea rep=new TextArea();
       				 	affichQuiz.getChildren().add(rep);
    				}
    			}
    		}
    		//serialiser la formation et la mettre dans un fichier
    		try {
    			File f2=new File("Formteur.ser");
    			
    			
    			if(f2.exists()) {
    				ObjectOutputStream out=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f2)));
    				out.writeObject(form);
    				out.close();
    				
    			}else {
    				f2.createNewFile();
    				ObjectOutputStream out=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f2)));
    				out.writeObject(form);
    				out.close();
    			}
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    }
    
    
    /**
     * 
     * @param event
     */
    @FXML
    void selectListFormation(ActionEvent event) {
    	
    	int cpt=0;
    	boolean arret=false;
    	ArrayList<Formation> formations= form.getFormations();
		Iterator<Formation> it= formations.iterator();
		Formation f=null;
		while (it.hasNext() & arret==false) {
			f=it.next();
			if(f.getNom().equals(listFormation.getValue())) {
				arret=true;
				
			}else cpt++;		
		}
		ArrayList<Notion> notions=f.getNotions();
		Notion n=null;
		Iterator<Notion> it2= notions.iterator();
		while (it2.hasNext()) {
			n=it2.next();
			datacheck.add(new CheckBox(n.getDescriptionNotion()));		
		}
		listCheckNotions.setItems(datacheck);
		
		
    }

	public genererQuizController(Formateur form) {
		super();
		this.form = form;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ArrayList<Formation> formations= form.getFormations();
		Iterator<Formation> it= formations.iterator();
		
		while (it.hasNext()) {
			Formation f=it.next();
			data.add((f.getNom()));
						
		}
		listFormation.getItems().clear();
		listFormation.setItems(data);
		
		
	}

}
