package programa.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrincipalApp extends Application {

	private static Stage stage;

	@Override
	public void start(final Stage primaryStage) {

		stage = primaryStage;

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/principal.fxml"));
			Scene scene = new Scene(root, 800, 600);

			primaryStage.setMaximized(false);
			primaryStage.setResizable(false);
			primaryStage.setTitle("BakeryBoss 1.0");
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
