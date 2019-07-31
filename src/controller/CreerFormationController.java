package controller;
import java.time.LocalDate;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Formateur;
import model.Formation;
import model.Utilisateur;
public class CreerFormationController {
	
	@FXML
    private TextField NomFormation;

    @FXML
    private TextField Description;

    @FXML
    private DatePicker datedebut;

    @FXML
    private DatePicker datefin;
    
    @FXML
    private Button valider;
 
    private Formateur form=null;

    public CreerFormationController(Utilisateur util) {
		this.form=(Formateur)util;
	}
    @FXML
    void ValidezClick(ActionEvent event) {
    	String nomFormation=new String(this.NomFormation.getText());
    	String description=new String(this.Description.getText());
    	LocalDate datedebut=this.datedebut.getValue();
    	LocalDate datefin =this.datefin.getValue();
    	Formation formation =new Formation(nomFormation,description,datedebut,datefin);
    	this.form.getFormations().add(formation);
    }
	
}
