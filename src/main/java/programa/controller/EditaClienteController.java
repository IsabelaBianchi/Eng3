package programa.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programa.app.EditaClienteApp;
import programa.app.GerenciaClienteApp;
import programa.dao.ClienteDao;
import programa.entity.Cliente;
import programa.valida.ValidaCampo;

public class EditaClienteController implements Initializable {

	Cliente clienteEdita;
	ClienteDao clienteDao = new ClienteDao();

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

	public void salvar(ActionEvent event) {

		if (ValidaCampo.isEmpty(tfNome.getText())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Nome do cliente nao pode ser em branco");
			a.showAndWait();
		} else {

			clienteEdita.setNomeCliente(tfNome.getText());
			clienteEdita.setSobrenome(tfSobrenome.getText());
			clienteEdita.setEmailCliente(tfEmail.getText());
			clienteEdita.setTelefone(tfTelefone.getText());
			clienteEdita.setCelular(tfCelular.getText());
			clienteEdita.setEndereco(tfEndereco.getText());
			clienteEdita.setCidade(tfCidade.getText());

			clienteDao.updateCliente(clienteEdita);

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Cliente " + tfNome.getText() + " alterado com sucesso");
			a.showAndWait();
			new GerenciaClienteApp().start(new Stage());
			EditaClienteApp.getStage().close();
		}

	}

	public void cancelar(ActionEvent event) {

		GerenciaClienteApp.getStage().show();
		EditaClienteApp.getStage().close();

	}

	public void initialize(URL location, ResourceBundle resources) {

		pegaCliente();

		tfNome.setText(clienteEdita.getNomeCliente());
		tfSobrenome.setText(clienteEdita.getSobrenome());
		tfEmail.setText(clienteEdita.getEmailCliente());
		tfTelefone.setText(clienteEdita.getTelefone());
		tfCelular.setText(clienteEdita.getCelular());
		tfEndereco.setText(clienteEdita.getEndereco());
		tfCidade.setText(clienteEdita.getCidade());

	}

	public void pegaCliente() {

		clienteEdita = clienteDao.getCliente(GerenciaClienteController.getCliente());

	}

}
