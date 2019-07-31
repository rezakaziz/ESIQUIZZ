package controller;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import model.Formateur;
import model.Formation;

import model.Utilisateur;

public class FormateurController {

    @FXML
    private Label Identifiant;
    
    @FXML
    private Label name;

    @FXML
    private Label surname;
    
    @FXML
    private Button CreerFormation;

    @FXML
    private Button AjouterNotion;

    @FXML
    private Button GenererQuiz;
    
    @FXML
    private Button afficherform;
    
    @FXML
    private Button afficherQuiz;
    @FXML
    private AnchorPane fenInterne;
    
    private Formateur form=null;
    private ObservableList<Formation> formationData = FXCollections.observableArrayList();
    
    public ObservableList<Formation> getFormationData() {
		return formationData;
	}

	public void setFormationData(ObservableList<Formation> formationData) {
		this.formationData = formationData;
	}

	public FormateurController(Utilisateur util) {
		this.form=(Formateur)util;
		try {
			File f=new File("Formteur.ser");
			
			if(f.exists()) {
				ObjectInputStream in=new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
				Formateur format=(Formateur)in.readObject();
				in.close();
				if (format.getIdentifiant().equals(util.getIdentifiant())) {
					this.form=format;
				}
				//si non on doit faire une recherche sur l'utilisateur ici j'ai fait que le cas ou on a un seul utilisateur
			}else {
				this.form=(Formateur)util;
			}
		}catch (Exception e) { 
			e.printStackTrace();
			} 
		
    	
    }

	public Label getIdentifiant() {
		return Identifiant;
	}

	public void setIdentifiant(String identifiant) {
		
		this.Identifiant.setText(identifiant);
	}
	
	public Label getName() {
		return name;
	}
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public Label getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname.setText(surname);
	}
    
	@FXML
    void ajouterNotionClick(ActionEvent event)  {
		try {
			
		final java.net.URL fxmlURL=getClass().getResource("../view/ajoutNotionController.fxml");
		final FXMLLoader fxmlLoader=new FXMLLoader(fxmlURL);
		AjoutNotionController formation=new AjoutNotionController(this.form);
		fxmlLoader.setController(formation);
		final Node node=fxmlLoader.load();
		fenInterne.getChildren().clear();
		fenInterne.getChildren().add(node);
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
    }

	 @FXML
	    void afficherQuizClick(ActionEvent event) {
		 try {
			 final java.net.URL fxmlURL=getClass().getResource("../view/ListeQuizz.fxml");
				final FXMLLoader fxmlLoader=new FXMLLoader(fxmlURL);
				ListeQuizzController formation=new ListeQuizzController(this.form);
				fxmlLoader.setController(formation);
				final Node node=fxmlLoader.load();
				fenInterne.getChildren().clear();
	 		fenInterne.getChildren().add(node);
		 }catch(IOException e) {
				e.printStackTrace();
			}
		 }
	 
	 
	 
    @FXML
    void creerFormationClick(ActionEvent event) {
    	try {
    		
    		final java.net.URL fxmlURL=getClass().getResource("../view/CreerForm.fxml");
			final FXMLLoader fxmlLoader=new FXMLLoader(fxmlURL);
			CreerFormationController formation=new CreerFormationController(this.form);
			fxmlLoader.setController(formation);
			final Node node=fxmlLoader.load();
			fenInterne.getChildren().clear();
    		fenInterne.getChildren().add(node);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void afficherFormClick(ActionEvent event) {
try {
    		
    		final java.net.URL fxmlURL=getClass().getResource("../view/TableFormation.fxml");
			final FXMLLoader fxmlLoader=new FXMLLoader(fxmlURL);
			
			
			
			TableFormation formation=new TableFormation(form);
			
			fxmlLoader.setController(formation);
			final Node node=fxmlLoader.load();
			fenInterne.getChildren().clear();
    		fenInterne.getChildren().add(node);
    		
    	
		}
		catch(IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void genererQuizClick(ActionEvent event) {
try {
    		
    		final java.net.URL fxmlURL=getClass().getResource("../view/listCheckNotions.fxml");
			final FXMLLoader fxmlLoader=new FXMLLoader(fxmlURL);
			
			
			
			genererQuizController formation=new genererQuizController(form);
			
			fxmlLoader.setController(formation);
			final Node node=fxmlLoader.load();
			fenInterne.getChildren().clear();
    		fenInterne.getChildren().add(node);
    		
    	
		}
		catch(IOException e) {
			e.printStackTrace();
		}
    }

}