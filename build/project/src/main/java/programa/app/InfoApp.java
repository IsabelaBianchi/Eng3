package programa.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InfoApp extends Application {

	private static Stage stage;

	@Override
	public void start(final Stage primaryStage) {

		stage = primaryStage;

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/info.fxml"));
			Scene scene = new Scene(root, 297, 302);

			primaryStage.setMaximized(false);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Sobre");
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
