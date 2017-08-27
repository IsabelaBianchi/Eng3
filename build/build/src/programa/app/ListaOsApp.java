package programa.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListaOsApp extends Application {

	private static Stage stage;

	@Override
	public void start(final Stage primaryStage) {

		stage = primaryStage;

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/listaOs.fxml"));
			Scene scene = new Scene(root, 800, 550);

			primaryStage.setMaximized(false);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Encomendas");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) {

		launch(args);
	}

	public static Stage getStage() {
		return stage;

	}

}
