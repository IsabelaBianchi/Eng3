package programa.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programa.app.CadastraClienteApp;
import programa.app.PrincipalApp;
import programa.dao.ClienteDao;
import programa.entity.Cliente;
import programa.entity.Usuario;
import programa.valida.ValidaCampo;
import programa.util.*;

public class CadastrarClienteController implements Initializable {

	@FXML
	private TextField tfNome = new TextField();
	@FXML
	private TextField tfSobrenome = new TextField();
	@FXML
	private TextField tfEmail = new TextField();
	@FXML
	private TextField tfTelefone = new TextField();
	@FXML
	private TextField tfCelular = new TextField();
	@FXML
	private TextField tfEndereco = new TextField();
	@FXML
	private TextField tfCidade = new TextField();
	@FXML
	private TextField tfCpf = new TextField();
	@FXML
	private Label lblUser = new Label();
	@FXML 
	private TextField tfNasc = new TextField();
	
	
	
	
	public void salvar(ActionEvent event) throws ParseException {
		
		if (ValidaCampo.isEmpty(tfNome.getText())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Nome do cliente nao pode ser em branco");
			a.showAndWait();
		} else {

			Cliente c = new Cliente();
			ClienteDao cDao = new ClienteDao();

			c.setNomeCliente(tfNome.getText());
			c.setSobrenome(tfSobrenome.getText());
			c.setEmailCliente(tfEmail.getText());
			c.setTelefone(tfTelefone.getText());
			c.setCelular(tfCelular.getText());
			c.setEndereco(tfEndereco.getText());
			c.setCidade(tfCidade.getText());
			c.setCpf(tfCpf.getText());
			c.setUsuario(lblUser.getText());
			c.setNasc(tfNasc.getText());
			cDao.addUsuario(c);

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Cliente " + tfNome.getText() + " cadastrado com sucesso");
			a.showAndWait();
			//new PrincipalApp().start(new Stage());
			CadastraClienteApp.getStage().close();
		}

	}

	public void cancelar(ActionEvent event) {

		//new PrincipalApp().start(new Stage());
		CadastraClienteApp.getStage().close();

	}

	public void initialize(URL location, ResourceBundle resources) {
		Usuario logado = LoginController.getUsuarioLogado();

		lblUser.setText(logado.getUsuario());
		
	}
	
}
