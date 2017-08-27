package programa.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SenhaApp extends Application {

	private static Stage stage;

	@Override
	public void start(final Stage primaryStage) {

		stage = primaryStage;

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/senha.fxml"));
			Scene scene = new Scene(root, 330, 217);

			primaryStage.setMaximized(false);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Alterar senha");
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
