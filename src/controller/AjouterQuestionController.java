package controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import model.Notion;
import model.Qcm;
import model.Qcu;
import model.Qo;


public class AjouterQuestionController implements Initializable {
	private ObservableList<String> list=FXCollections.observableArrayList(
			new String("QCM"),
			new String("QCU"),
			new String("QO")
			);
	

    @FXML
    private ComboBox<String> typeQuestion;
    
    @FXML
    private TextArea enonce;
    @FXML
    private TextArea propositions;

    @FXML
    private TextArea repsJustes;

    @FXML
    private Button validez;

    private Notion notion=null;
    @FXML
    void validerClick(ActionEvent event) {
    	//Question question=null;
    	
    	if(typeQuestion.getValue().equals("QCM")) {
    		Qcm question=new Qcm(this.enonce.getText());
    		//recuperer les reponses juste
    		
    		String repJuste =new String(this.repsJustes.getText());
    		ArrayList<String> reponsesJuste=new ArrayList<String>(Arrays.asList(repJuste.split("\n")));
    		((Qcm)question).setReponsesJustes(reponsesJuste);
    		//recuperer reponses fausses
    		String repFausses =new String(this.propositions.getText());
    		ArrayList<String> reponsesFausses=new ArrayList<String>(Arrays.asList(repFausses.split("\n")));
    		((Qcm)question).setReponsesFausses(reponsesFausses);
    		
    		this.notion.ajouterQuestion(question);
    		
    	}
    	else if(typeQuestion.getValue().equals("QCU")) {
    		Qcu question=new Qcu(this.enonce.getText());
//recuperer les reponses juste
    		
    		
    		String reponseJuste=this.repsJustes.getText();
    		((Qcu)question).setReponseJuste(reponseJuste);
    		//recuperer reponses fausses
    		String repFausses =new String(this.propositions.getText());
    		ArrayList<String> reponsesFausses=new ArrayList<String>(Arrays.asList(repFausses.split("\n")));
    		((Qcu)question).setReponsesFausses(reponsesFausses);
    		this.notion.ajouterQuestion(question);
    	}	
    	else if(typeQuestion.getValue().equals("QO")) {
    		Qo question=new Qo(this.enonce.getText());
    		String repJuste =new String(this.repsJustes.getText());
    		ArrayList<String> reponsesJuste=new ArrayList<String>(Arrays.asList(repJuste.split("\n")));
    		((Qo)question).setBonneReponsesProposé(reponsesJuste);
    		this.notion.ajouterQuestion(question);
    		
    	}
    	
    }
    public AjouterQuestionController(Notion notion) {
    	this.notion=notion;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		typeQuestion.setItems(list);
	}

}

