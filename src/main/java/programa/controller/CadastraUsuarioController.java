package programa.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import programa.app.CadastraUsuarioApp;
import programa.app.Main;
import programa.app.PrincipalApp;
import programa.dao.FuncionarioDao;
import programa.dao.UsuarioDao;
import programa.entity.Funcionario;
import programa.entity.MateriaPrima;
import programa.entity.Usuario;
import programa.util.Md5Converte;
import programa.valida.ValidaCampo;

public class CadastraUsuarioController implements Initializable {

	@FXML
	private Label lblTitulo = new Label();
	@FXML
	private Label lblNome = new Label();
	@FXML
	private Label lblSenha = new Label();
	@FXML
	private Label lblEmail = new Label();
	@FXML
	private Label lblUsuario = new Label();
	@FXML
	private TextField tfNome = new TextField();
	@FXML
	private TextField tfEmail = new TextField();
	@FXML
	private TextField tfUsuario = new TextField();
	@FXML
	private TextField tfSenha = new TextField();
	@FXML
	private Label lblPermissao = new Label();
	@FXML
	private Button btnCancelar = new Button();
	@FXML
	private Button btnSalvar = new Button();

	@SuppressWarnings("rawtypes")
	@FXML
	ChoiceBox cbPermissao = new ChoiceBox();
	
	@SuppressWarnings("rawtypes")
	@FXML
	ChoiceBox cbFunc = new ChoiceBox();

	ObservableList<String> permissaoTipos = FXCollections.observableArrayList("1 - Administrador", "2 - Usuario");
	private ObservableList<String> obscervableFunc = FXCollections.observableArrayList();
	private ObservableList<Funcionario> obscervableFunc2 = FXCollections.observableArrayList();
	private List<Funcionario> listFunc;

	Funcionario funcionario = new Funcionario();
	FuncionarioDao funcionarioDao = new FuncionarioDao();

	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {

		Main.getStage().close();
		cbPermissao.setItems(permissaoTipos);
		cbPermissao.setValue(permissaoTipos);
		listarFunc();

	}
	@SuppressWarnings("unchecked")
	public void listarFunc(){
		
		
			listFunc = funcionarioDao.listFuncionario();

			for (Funcionario funcionario : listFunc) {

				obscervableFunc.add(funcionario.getNome());

			}
			
			cbFunc.setItems(obscervableFunc);
			//cbMateria.setValue(observableMateria);
		
	}

	public void salvar(ActionEvent event) throws Throwable {

		if (ValidaCampo.isEmpty(tfNome.getText())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Preencher campo nome, nao pode ser em branco");
			a.setHeaderText("Campo nome em branco");
			a.showAndWait();
		} else

		if (ValidaCampo.isEmpty(tfUsuario.getText()) || ValidaCampo.contemEspaco(tfUsuario.getText())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Campo usuário não pode ser em branco ou conter espaços");
			a.setHeaderText("Campo de usuario em branco");
			a.showAndWait();

		} else if (cbPermissao.getValue().toString().equals("[1 - Administrador, 2 - Usuario]")) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Selecionar o tipo de permissao do usuario");
			a.setHeaderText("Campo permissao nao selecionado");
			a.showAndWait();
		} else {

			Usuario u = new Usuario();
			u.setNome(tfNome.getText());
			u.setEmail(tfEmail.getText());
			u.setUsuario(tfUsuario.getText());
			u.setSenha(Md5Converte.converte(tfSenha.getText()));
			Funcionario c = new Funcionario();
			c = funcionarioDao.getFuncionario(cbFunc.getValue().toString());
		//	u.setIdFunc(c.getId());
			if (cbPermissao.getValue().equals("1 - Administrador")) {
				u.setPermissao(1);
			} else {
				u.setPermissao(2);
			}

			UsuarioDao usuarioDao = new UsuarioDao();
			usuarioDao.addUsuario(u);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Usuario cadastrado com sucesso");
			alert.setHeaderText("Sucesso");
			alert.showAndWait();

			new PrincipalApp().start(new Stage());
			CadastraUsuarioApp.getStage().close();

		}

	}// Fim salvar

	public void cancelar(ActionEvent event) {

		new PrincipalApp().start(new Stage());
		CadastraUsuarioApp.getStage().close();
	}

}
