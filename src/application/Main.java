package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//Instanciando um objeto do tipo FXMLLoader + passar o caminho da View
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			
			//Chama o loader.load
			ScrollPane scrollPane = loader.load();
			
			//Cria um objeto do tipo Scene e instancia com o argumento parent (objeto principalo da view)
			mainScene = new Scene(scrollPane);
			
			//Ajustar ScrollPane à altura/largura
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			//Palco da cena: seta a cena com a cena principal
			primaryStage.setScene(mainScene);
			
			//Título do palco
			primaryStage.setTitle("Sample JavaFX application");
			
			//Mostrar o palco
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Para referenciar o mainScene pois é privado
	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
