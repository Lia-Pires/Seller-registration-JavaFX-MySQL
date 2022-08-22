package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Instanciando um objeto do tipo FXMLLoader + passar o caminh oda View
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			
			//chama o loader.load
			ScrollPane scrollPane = loader.load();
			
			// Cria um objeto do tipo Scene e instancia com o argumento parent (objeto principalo da view)
			Scene mainScene = new Scene(scrollPane);
			
			//Ajustar ScrollPane à altura/largura
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			//palco da cena: seta a cena com a cena principal
			primaryStage.setScene(mainScene);
			
			//título do palco
			primaryStage.setTitle("Sample JavaFX application");
			
			//mostrar o palco
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
