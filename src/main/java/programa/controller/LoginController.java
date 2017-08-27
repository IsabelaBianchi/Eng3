package programa.controller;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import programa.app.Main;
import programa.app.PrincipalApp;
import programa.dao.UsuarioDao;
import programa.entity.Usuario;
import programa.util.Md5Converte;
import programa.valida.UsuarioValidaDao;

public class LoginController implements Initializable {

	private final Image image = new Image("/imagens/Access.png");
	private static Usuario usuarioLogado;

	boolean autentica = false;

	@FXML
	Button btnEntrar = new Button();
	@FXML
	Label lblTitulo = new Label();
	@FXML
	Label lblNome = new Label();
	@FXML
	Label lblSenha = new Label();
	@FXML
	TextField tfUsuario = new TextField();
	@FXML
	PasswordField pfSenha = new PasswordField();
	@FXML
	ImageView imgView = new ImageView(image);

	@FXML
	public void entrar(ActionEvent event) throws NoSuchAlgorithmException, IOException {

		String usuario = tfUsuario.getText();
		String senha = Md5Converte.converte(pfSenha.getText());

		autentica = UsuarioValidaDao.verificaUsuario(usuario, senha);

		System.out.println(autentica);
		if (autentica == false) {

			Alert info = new Alert(AlertType.INFORMATION);
			info.setContentText("Usuario ou senha incorreto");
			info.setHeaderText("Erro de login");
			info.setTitle("Ocorreu um erro");
			info.showAndWait();

			tfUsuario.clear();
			pfSenha.clear();
		} else {

			UsuarioDao usuarioDao = new UsuarioDao();
			usuarioLogado = usuarioDao.getUsuario(usuario, senha);

			new PrincipalApp().start(new Stage());
			Main.getStage().close();
		}

	}

	public void initialize(URL location, ResourceBundle resources) {
		imgView.setVisible(true);

	}

	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

}
