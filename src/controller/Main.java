package controller;
	


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			final java.net.URL fxmlURL=getClass().getResource("../view/TPGraphique.fxml");
			final FXMLLoader fxmlLoader=new FXMLLoader(fxmlURL);
			loginController log=new loginController();
			fxmlLoader.setController(log);
			final Node node=fxmlLoader.load();
			
			StackPane root = new StackPane(node);
			Scene scene = new Scene(root,600,400);
			primaryStage.setTitle("Connection");
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
