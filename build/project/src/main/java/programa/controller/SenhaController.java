package programa.controller;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import programa.app.SenhaApp;
import programa.dao.UsuarioDao;
import programa.entity.Usuario;
import programa.util.Md5Converte;
import programa.valida.ValidaCampo;

public class SenhaController implements Initializable {

	public void initialize(URL location, ResourceBundle resources) {

		logado = LoginController.getUsuarioLogado();
		lblUsuario.setText(logado.getUsuario());
		lblNome.setText(logado.getNome());

	}

	Usuario logado;

	@FXML
	private Button btnSalvar = new Button();
	@FXML
	private Button btnCancelar = new Button();
	@FXML
	private TextField tfSenha = new TextField();
	@FXML
	private TextField tfConfirma = new TextField();
	@FXML
	private Label lblUsuario = new Label();
	@FXML
	private Label lblNome = new Label();

	public void salvar() throws NoSuchAlgorithmException {

		if (ValidaCampo.isEmpty(tfSenha.getText()) || ValidaCampo.isEmpty(tfConfirma.getText())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Erro");
			a.setContentText("Nao pode ser em branco");
			a.setHeaderText(null);
			a.showAndWait();
		} else {
			if (tfSenha.getText().equals(tfConfirma.getText())) {
				System.out.println("senha igual");
				UsuarioDao dao = new UsuarioDao();
				logado.setSenha(Md5Converte.converte(tfSenha.getText()));
				dao.updateUsuario(logado);
				Alert a = new Alert(AlertType.CONFIRMATION);
				a.setTitle("Sucesso");
				a.setContentText("Senha alterado com sucesso");
				a.setHeaderText(null);
				a.showAndWait();
				SenhaApp.getStage().close();
			} else {
				System.out.println("Senhas diferentes");
			}

		}

	}

	public void cancelar() {

		SenhaApp.getStage().close();

	}
}
