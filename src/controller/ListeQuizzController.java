package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Formateur;
import model.Formation;
import model.Quiz;

public class ListeQuizzController implements Initializable{

    @FXML
    private TableView<Quiz> tableQuiz;
    private Formateur form; 
    
    ObservableList<Quiz> data=FXCollections.observableArrayList();
	public ListeQuizzController(Formateur form) {
		super();
		this.form = form;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		TableColumn<Quiz,String> nom=new TableColumn<Quiz,String>("Nom");
		
		TableColumn<Quiz,LocalDate> dateDebut=new TableColumn<Quiz,LocalDate>("Date de Debut");
		TableColumn<Quiz,LocalDate> dateFin=new TableColumn<Quiz,LocalDate>("Date de fin");
		
		tableQuiz.getColumns().addAll(nom,dateDebut,dateFin);
		nom.setCellValueFactory(new PropertyValueFactory<Quiz,String>("nom"));
	
		dateDebut.setCellValueFactory(new PropertyValueFactory<Quiz,LocalDate>("dateDebut"));
		dateFin.setCellValueFactory(new PropertyValueFactory<Quiz,LocalDate>("dateFin"));
		
		ArrayList<Formation> formations=form.getFormations();
		Iterator<Formation> it= formations.iterator();
		
		while (it.hasNext() ) {
			Formation f=it.next();
			Iterator<Quiz> it2= f.getQuizz().iterator();
			
			while(it2.hasNext()) {
				Quiz quiz1=it2.next();
				System.out.println("Formation");
				data.add(quiz1);
			}
		}
		tableQuiz.getItems().clear();
		tableQuiz.setItems(data);
		
	}

}
