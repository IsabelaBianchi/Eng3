package programa.app;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	private static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		
		stage = primaryStage;
				
		try {
			//Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
			
			
			Scene scene = new Scene(root,350,350);
			
			primaryStage.setMaximized(false);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Login");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	public static Stage getStage(){
		
		return stage;
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
