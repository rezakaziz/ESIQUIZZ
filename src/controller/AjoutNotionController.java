package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Formateur;
import model.Formation;
import model.Notion;


public class AjoutNotionController implements Initializable {

    @FXML
    private ComboBox<String> listFormation;
    @FXML
    private TextArea descrNotion;

    @FXML
    private Button ajouterQuestion;
    
    @FXML
    private Button validerNotion;

    @FXML
    private AnchorPane fenInterne;
    
    ObservableList<String> data=FXCollections.observableArrayList();
   	
    private Notion not=new Notion(null);
    private Formateur form=null;

	public AjoutNotionController(Formateur form) {
		super();
		this.form = form;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ArrayList<Formation> formations=form.getFormations();
		Iterator<Formation> it= formations.iterator();
		
		while (it.hasNext()) {
			Formation f=it.next();
			data.add(f.getNom());
			
			
		}
		listFormation.getItems().clear();
		listFormation.setItems(data);
		
	}
	
	 @FXML
	    void validerNotionClick(ActionEvent event) {
		 	not.setDescriptionNotion(this.descrNotion.getText());
		 	ArrayList<Formation> formations=form.getFormations();
		 	//rechercher dans la liste des formation
		 	Iterator<Formation> it= formations.iterator();
			boolean arret=false;
			int cpt=0;
			while (it.hasNext() && !arret) {
				Formation f=it.next();
				if(f.getNom().contentEquals(listFormation.getValue())) {
					arret=true;
				}
				else cpt++;
			}
			
			
			formations.get(cpt).ajoutNotions(not);
			form.setFormations(formations);
			
	    }


    @FXML
    void ajouterQuestionClick(ActionEvent event) {
    	try {
			
    		Stage secondStage=new Stage();
			final java.net.URL fxmlURL=getClass().getResource("../view/AjouterQuestion.fxml");
			final FXMLLoader fxmlLoader=new FXMLLoader(fxmlURL);
			
			AjouterQuestionController form=new AjouterQuestionController(this.not);
			
			fxmlLoader.setController(form);
			
			final Node node=fxmlLoader.load();
			
			StackPane root = new StackPane(node);
			Scene scene = new Scene(root,1024,756);
			secondStage.setTitle("Ajouter Question");
			
			secondStage.setScene(scene);
			secondStage.show();
    		System.out.println("ajouter question");
    		}
    		catch(IOException e) {
    			e.printStackTrace();
    		}
    }

}
