package programa.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditaEncomendaApp extends Application {

	private static Stage stage;

	@Override
	public void start(final Stage primaryStage) {

		stage = primaryStage;

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/editaOs.fxml"));
			Scene scene = new Scene(root, 700, 545);

			primaryStage.setMaximized(false);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Editar Encomenda");
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
