package programa.controller;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import programa.app.CadastraFornecedorApp;
import programa.app.CadastraFuncionarioApp;
import programa.app.PrincipalApp;
import programa.dao.FornecedorDao;
import programa.dao.FuncionarioDao;
import programa.entity.Fornecedor;
import programa.entity.Funcionario;
import programa.valida.ValidaCampo;
public class CadastrarFuncionarioController implements Initializable {

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

			Funcionario g = new Funcionario();
			FuncionarioDao cDao = new FuncionarioDao();

			g.setNome(tfNome.getText());
			g.setTelefone(tfTelefone.getText());
			g.setCpf(tfCpf.getText());
			g.setCargo(tfCargo.getText());
			g.setEndereco(tfEndereco.getText());
			
			cDao.addFuncionario(g);

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Funcionario " + tfNome.getText() + " cadastrado com sucesso");
			a.showAndWait();
			//new PrincipalApp().start(new Stage());
			CadastraFuncionarioApp.getStage().close();
		}

	}

	public void cancelar(ActionEvent event) {
	
		new PrincipalApp().start(new Stage());
		CadastraFuncionarioApp.getStage().close();
	
	}

	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
