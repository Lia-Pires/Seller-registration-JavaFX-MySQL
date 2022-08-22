package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {
	//menu Item + nome
	@FXML 
	private MenuItem menuItemSeller;
	@FXML 
	private MenuItem menuItemDepartment;
	@FXML 
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		System.out.println("onMenuItemDepartmentAction");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {	 		
	}
	
	//absolute name porque vai passar o caminho completo
	private void loadView(String absoluteName) {
		try {
			//instanciar o objeto do tipo Loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load(); 
			
			Scene mainScene = Main.getMainScene(); 
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			// referência do mainMenu, o 0 quer dizer que é o primeiro filho do VBox, ou seja o mainMenu
			Node mainMenu = mainVBox.getChildren().get(0);
			// limpa todos os filhos do VBox
			mainVBox.getChildren().clear();
			//Adicionar mainMenu e os filhos do VBox
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());			
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	

}
