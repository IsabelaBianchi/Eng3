import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestaCadastraCliente extends Application {
private static Stage stage;
	
	public void start(Stage primaryStage) {
		
		stage = primaryStage;
				
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/cadastraCliente.fxml"));
			Scene scene = new Scene(root,328,310);
			
			primaryStage.setMaximized(false);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Cadastro de pessoa");
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
