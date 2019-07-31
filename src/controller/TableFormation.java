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


public class TableFormation implements Initializable{

    @FXML
    private TableView<Formation> TableFormation;
    
    private Formateur form; 
   
    ObservableList<Formation> data=FXCollections.observableArrayList();
	public TableFormation(Formateur form) {
		super();
		this.form = form;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		TableColumn<Formation,String> nom=new TableColumn<Formation,String>("Nom");
		TableColumn<Formation,String> description=new TableColumn<Formation,String>("Description");
		TableColumn<Formation,LocalDate> dateDebut=new TableColumn<Formation,LocalDate>("Date de Debut");
		TableColumn<Formation,LocalDate> dateFin=new TableColumn<Formation,LocalDate>("Date de fin");
		
		TableFormation.getColumns().addAll(nom,description,dateDebut,dateFin);
		nom.setCellValueFactory(new PropertyValueFactory<Formation,String>("nom"));
		description.setCellValueFactory(new PropertyValueFactory<Formation,String>("description"));
		dateDebut.setCellValueFactory(new PropertyValueFactory<Formation,LocalDate>("dateDebut"));
		dateFin.setCellValueFactory(new PropertyValueFactory<Formation,LocalDate>("dateFin"));
		
		ArrayList<Formation> formations=form.getFormations();
		Iterator<Formation> it= formations.iterator();
		
		while (it.hasNext()) {
			Formation f=it.next();
			data.add(f);
			
			
		}
		TableFormation.getItems().clear();
		TableFormation.setItems(data);
		
	}
    
}
