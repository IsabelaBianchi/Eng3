
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TestaPrincipal extends Application {
	@Override
	public void start(Stage primaryStage) {
				
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/principal.fxml"));
			Scene scene = new Scene(root,720,600);
			
			primaryStage.setMaximized(false);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Cadastro de pessoa");
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


