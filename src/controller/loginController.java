package controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import model.Compte;
import model.CompteException;
import model.Comptes;
import model.Formateur;
import model.Groupe;
import model.Utilisateur;

public class loginController implements Initializable {


	@FXML
    private TextField userName;

    @FXML
    private PasswordField password;

	  @FXML
	    private Button seconnecter;

	    @FXML
	    private Button fermer;

	    @FXML
	    void fermerClick(ActionEvent event) {
	    	Stage stage=(Stage)fermer.getScene().getWindow();
	    	stage.close();
	    }

	    @FXML
	    void seConnecter(ActionEvent event) {
	    	//appel a la methode authentification 
	    	String userName=new String(this.userName.getText());
	    	String password=new String(this.password.getText());
	    	
	    	Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			try {
				date = simpleDateFormat.parse("10/07/2019");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Groupe gr =new Groupe();
	    	Formateur appr= new Formateur("hr_aziz","kazer1999","AZIZ","Rezak");
	    	Comptes comptes=new Comptes();
	    	Compte compte=new Compte(appr);
	    	
	    	comptes.AjouterComptes(compte);
	    	
	    	try {
	    		if(comptes.Authentifiation(userName, password)) {
	    			Utilisateur util=comptes.recupererUtilisateur(userName, password);
	    			Stage secondStage=new Stage();
	    			final java.net.URL fxmlURL=getClass().getResource("../view/Formateur.fxml");
	    			final FXMLLoader fxmlLoader=new FXMLLoader(fxmlURL);
	    			FormateurController form=new FormateurController(util);
	    			
	    			fxmlLoader.setController(form);
	    			
	    			final Node node=fxmlLoader.load();
	    			
	    			StackPane root = new StackPane(node);
	    			Scene scene = new Scene(root,1024,756);
	    			secondStage.setTitle("Formateur");
	    			
	    			secondStage.setScene(scene);
	    			secondStage.show();
	    			form.setIdentifiant(userName);
	    			/*Recuperation du nom d'utilisateur */
	    			
	    			if (util != null) {
	    				form.setSurname(util.getPrenom());
	    				form.setName(util.getNom());
	    			}
	    			
	    		}
	    	}catch(CompteException e) {
	    		//System.out.println(userName);
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    }

