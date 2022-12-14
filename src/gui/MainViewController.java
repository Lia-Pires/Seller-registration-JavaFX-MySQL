package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.DepartmentService;
import model.services.SellerService;

public class MainViewController implements Initializable {
	
	//Menu Item + nome
	@FXML 
	private MenuItem menuItemSeller;
	@FXML 
	private MenuItem menuItemDepartment;
	@FXML 
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		loadView("/gui/SellerList.fxml", (SellerListController controller) -> {
			controller.setSellerService(new SellerService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {	 		
	}
	
	//Absolute name porque vai passar o caminho completo
	//synchronized garante que o que tem no try vai ser executado sem ser interrompido
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			//Instanciar o objeto do tipo Loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load(); 
			
			Scene mainScene = Main.getMainScene(); 
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			//Referência do mainMenu, o 0 quer dizer que é o primeiro filho do VBox, ou seja o mainMenu
			Node mainMenu = mainVBox.getChildren().get(0);
			//Limpa todos os filhos do VBox
			mainVBox.getChildren().clear();
			//Adicionar mainMenu e os filhos do VBox
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			
			}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR); 
		}
	}		
		

}
