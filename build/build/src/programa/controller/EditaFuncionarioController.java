package programa.controller;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import programa.app.CadastraFuncionarioApp;
import programa.app.EditaClienteApp;
import programa.app.EditaFornecedorApp;
import programa.app.EditaFuncionarioApp;
import programa.app.GerenciaClienteApp;
import programa.app.GerenciaFornecedorApp;
import programa.app.GerenciaFuncionarioApp;
import programa.app.PrincipalApp;
import programa.dao.ClienteDao;
import programa.dao.FornecedorDao;
import programa.dao.FuncionarioDao;
import programa.entity.Cliente;
import programa.entity.Fornecedor;
import programa.entity.Funcionario;
import programa.valida.ValidaCampo;

public class EditaFuncionarioController implements Initializable {
	
	
	Funcionario funcionario = new Funcionario();
	FuncionarioDao funcionarioDao = new FuncionarioDao();

	@FXML
	private TextField tfNome = new TextField();
	@FXML
	private TextField tfTelefone = new TextField();
	@FXML
	private TextField tfCpf = new TextField();
	@FXML
	private TextField tfEndereco = new TextField();
	@FXML
	private TextField tfCargo = new TextField();
	@FXML
	private Button btnSalvar = new Button();
	@FXML
	private Button btnCancelar = new Button();
	

public void salvar(ActionEvent event) throws ParseException {
		
		if (ValidaCampo.isEmpty(tfNome.getText()) || (ValidaCampo.isEmpty(tfCpf.getText()))) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Nome ou CPF do funcionário não podem ser em branco");
			a.showAndWait();
		}else {
			funcionario.setNome(tfNome.getText());
			funcionario.setTelefone(tfTelefone.getText());
			funcionario.setCpf(tfCpf.getText());
			funcionario.setCargo(tfCargo.getText());
			funcionario.setEndereco(tfEndereco.getText());
			
			funcionarioDao.updateFuncionario(funcionario);

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Funcionario " + tfNome.getText() + " alterado com sucesso");
			a.showAndWait();
			new PrincipalApp().start(new Stage());
			EditaFuncionarioApp.getStage().close();
		}

	}

	public void cancelar(ActionEvent event) {

		GerenciaFuncionarioApp.getStage().show();
		EditaFuncionarioApp.getStage().close();

	}

	public void initialize(URL location, ResourceBundle resources) {

		pegaCliente();

		tfNome.setText(funcionario.getNome());
		tfTelefone.setText(funcionario.getTelefone());
		tfCargo.setText(funcionario.getCargo());
		tfCpf.setText(funcionario.getCpf());
		tfEndereco.setText(funcionario.getEndereco());
	
		
		

	}

	public void pegaCliente() {

		funcionario = funcionarioDao.getFuncionario(GerenciaFuncionarioController.getCliente());

	}
}
