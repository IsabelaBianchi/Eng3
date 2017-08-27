package programa.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import programa.app.EditaClienteApp;
import programa.app.EditaFornecedorApp;
import programa.app.GerenciaClienteApp;
import programa.app.GerenciaFornecedorApp;
import programa.dao.ClienteDao;
import programa.dao.FornecedorDao;
import programa.entity.Cliente;
import programa.entity.Fornecedor;
import programa.valida.ValidaCampo;

public class EditaFornecedorController implements Initializable {
	
	
	Fornecedor fornecedorEdita;
	FornecedorDao fornecedorDao = new FornecedorDao();

	@FXML
	private TextField tfNome = new TextField();
	@FXML
	private TextField tfSobrenome = new TextField();
	@FXML
	private TextField tfEmail = new TextField();
	@FXML
	private TextField tfTelefone = new TextField();
	

	public void salvar(ActionEvent event) {

		if (ValidaCampo.isEmpty(tfNome.getText())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Nome do fornecedor nao pode ser em branco");
			a.showAndWait();
		} else {

			fornecedorEdita.setNomeFornecedor(tfNome.getText());
			
			fornecedorEdita.setFoneFornecedor(tfTelefone.getText());
		

			fornecedorDao.updateFornecedor(fornecedorEdita);

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Fornecedor " + tfNome.getText() + " alterado com sucesso");
			a.showAndWait();
			new GerenciaFornecedorApp().start(new Stage());
			EditaFornecedorApp.getStage().close();
		}

	}

	public void cancelar(ActionEvent event) {

		GerenciaFornecedorApp.getStage().show();
		EditaFornecedorApp.getStage().close();

	}

	public void initialize(URL location, ResourceBundle resources) {

		pegaCliente();

		tfNome.setText(fornecedorEdita.getNomeFornecedor());
		tfTelefone.setText(fornecedorEdita.getFoneFornecedor());
		

	}

	public void pegaCliente() {

		fornecedorEdita = fornecedorDao.getFornecedor(GerenciaFornecedorController.getCliente());

	}
}
