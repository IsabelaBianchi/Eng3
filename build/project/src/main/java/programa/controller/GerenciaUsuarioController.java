package programa.controller;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import programa.app.GerenciaUsuarioApp;
import programa.app.PrincipalApp;
import programa.dao.UsuarioDao;
import programa.entity.Usuario;
import programa.tabela.UsuarioTabela;
import programa.util.Md5Converte;
import programa.valida.ValidaCampo;

public class GerenciaUsuarioController implements Initializable {

	public void initialize(URL location, ResourceBundle resources) {
		esconder();
		listaUsuarios();
		rbAdmin.setToggleGroup(grupo);
		rbUsuario.setToggleGroup(grupo);

	}

	ObservableList<UsuarioTabela> observableListUsuarioTabela = FXCollections.observableArrayList();

	UsuarioDao usuarioDao = new UsuarioDao();
	Usuario usuario = new Usuario();
	UsuarioTabela usuarioTabelaSelecionado;

	List<Usuario> listUsuario;

	@FXML
	TableView<UsuarioTabela> tvUsuario = new TableView<UsuarioTabela>();

	@FXML
	private Button btnEditar = new Button();
	@FXML
	private Button btnExcluir = new Button();
	@FXML
	private Button btnSalvar = new Button();
	@FXML
	private Button btnCancelar = new Button();
	@FXML
	private Button btnSair = new Button();
	@FXML
	private Label lblNome = new Label();
	@FXML
	private Label lblEmail = new Label();
	@FXML
	private Label lblUsuario = new Label();
	@FXML
	private Label lblSenha = new Label();
	@FXML
	private TextField tfNome = new TextField();
	@FXML
	private TextField tfEmail = new TextField();
	@FXML
	private TextField tfUsuario = new TextField();
	@FXML
	private TextField tfSenha = new TextField();
	@FXML
	private TableColumn<UsuarioTabela, String> tcNome;
	@FXML
	private TableColumn<UsuarioTabela, String> tcEmail;
	@FXML
	private TableColumn<UsuarioTabela, String> tcUsuario;
	@FXML
	private TableColumn<UsuarioTabela, String> tcSenha;
	@FXML
	private TableColumn<UsuarioTabela, String> tcPermissao;
	@FXML
	private RadioButton rbAdmin = new RadioButton();
	@FXML
	private RadioButton rbUsuario = new RadioButton();

	ToggleGroup grupo = new ToggleGroup();

	public void clicou() {
		try {
			usuarioTabelaSelecionado = tvUsuario.getSelectionModel().getSelectedItem();
			System.out.println(usuarioTabelaSelecionado.getNome());
			btnEditar.setVisible(true);
			btnExcluir.setVisible(true);

		} catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Erro...");
			a.setHeaderText("Nenhum usuário na tabela selecionado");
			a.setContentText("Selecione algum usuário na tabela.");
			a.showAndWait();
		}
	}

	public void editar() {
		tvUsuario.setDisable(true);
		System.out.println(usuarioTabelaSelecionado.getNome().toString());
		tfNome.setText(usuarioTabelaSelecionado.getNome().toString());
		tfEmail.setText(usuarioTabelaSelecionado.getEmail().toString());
		tfUsuario.setText(usuarioTabelaSelecionado.getUsuario().toString());
		tfSenha.setText(usuarioTabelaSelecionado.getSenha().toString());

		if (usuarioTabelaSelecionado.getPermissao().equals(1)) {
			rbAdmin.setSelected(true);
		} else if (usuarioTabelaSelecionado.getPermissao().equals(2)) {
			rbUsuario.setSelected(true);
		}

		mostra();

	}

	public void excluir() {

		int idLogado = LoginController.getUsuarioLogado().getId();
		if (usuarioTabelaSelecionado.getId().equals(idLogado)) {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Voce não pode excluir o usuário que esta logado");
			a.setHeaderText("Atenção");
			a.setTitle("Confirmaçao pendente");
			a.showAndWait();

		} else {

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Voce deseja realmente excluir este usuário?");
			a.setHeaderText("Atenção");
			a.setTitle("Confirmaçao pendente");

			Optional<ButtonType> result = a.showAndWait();
			if (result.get() == ButtonType.OK) {
				usuarioDao.removeUsuario(usuarioTabelaSelecionado.getId());
				Alert a1 = new Alert(AlertType.INFORMATION);
				a1.setContentText("Usuário excluido com sucesso");
				a1.setHeaderText(null);
				a1.setTitle("Sucesso");
				a1.showAndWait();
				listaUsuarios();

			} else {
				System.out.println("Cancelado");
			}

		}

	}

	public void salvar() throws NoSuchAlgorithmException {

		if (ValidaCampo.isEmpty(tfNome.getText())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Preencher campo nome, nao pode ser em branco");
			a.setHeaderText("Campo de nome em branco");
			a.showAndWait();
		} else

		if (ValidaCampo.isEmpty(tfUsuario.getText()) || ValidaCampo.contemEspaco(tfUsuario.getText())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Preencher campo usuario, nao pode ser em branco ou conter espaços");
			a.setHeaderText("Campo de usuario em branco");
			a.showAndWait();
		} else {

			Usuario u = new Usuario();

			u.setId(usuarioTabelaSelecionado.getId());
			u.setNome(tfNome.getText());
			u.setUsuario(tfUsuario.getText());
			u.setSenha(Md5Converte.converte(tfSenha.getText()));
			u.setEmail(tfEmail.getText());
			if (grupo.getSelectedToggle().toString()
					.equals("RadioButton[id=rbUsuario, styleClass=radio-button]'Usuario'")) {
				u.setPermissao(2);
			} else if (grupo.getSelectedToggle().toString()
					.equals("RadioButton[id=rbAdmin, styleClass=radio-button]'Admin'")) {
				u.setPermissao(1);

			}

			System.out.println(u.getId());
			System.out.println(u.getNome());
			System.out.println(u.getUsuario());
			System.out.println(u.getSenha());
			System.out.println(u.getEmail());
			System.out.println(u.getPermissao());

			usuarioDao.updateUsuario(u);
			listaUsuarios();

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Sucesso");
			a.setHeaderText(null);
			a.setContentText("Usuario editado com sucesso");
			a.showAndWait();
			esconder();

		}

	}

	public void cancelar() {

		esconder();
		tvUsuario.setDisable(false);
		usuarioTabelaSelecionado = null;

	}

	public void sair() {

		new PrincipalApp().start(new Stage());
		GerenciaUsuarioApp.getStage().close();

	}

	public void listaUsuarios() {

		listUsuario = usuarioDao.listUsuario();

		if (!observableListUsuarioTabela.isEmpty()) {
			observableListUsuarioTabela.clear();
		}

		for (Usuario usuario : listUsuario) {

			UsuarioTabela usuarioTabela = new UsuarioTabela(usuario.getId(), usuario.getNome(), usuario.getSenha(),
					usuario.getEmail(), usuario.getUsuario(), usuario.getPermissao());

			observableListUsuarioTabela.add(usuarioTabela);
		}

		tcNome.setCellValueFactory(new PropertyValueFactory<UsuarioTabela, String>("nome"));
		tcUsuario.setCellValueFactory(new PropertyValueFactory<UsuarioTabela, String>("usuario"));
		tcSenha.setCellValueFactory(new PropertyValueFactory<UsuarioTabela, String>("senha"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<UsuarioTabela, String>("email"));
		tcPermissao.setCellValueFactory(new PropertyValueFactory<UsuarioTabela, String>("permissao"));

		tvUsuario.setItems(observableListUsuarioTabela);

	}

	public void esconder() {
		lblNome.setVisible(false);
		lblEmail.setVisible(false);
		lblSenha.setVisible(false);
		lblUsuario.setVisible(false);

		btnEditar.setVisible(false);
		btnExcluir.setVisible(false);
		btnSalvar.setVisible(false);
		btnCancelar.setVisible(false);

		tfNome.setVisible(false);
		tfEmail.setVisible(false);
		tfSenha.setVisible(false);
		tfUsuario.setVisible(false);

		rbAdmin.setVisible(false);
		rbUsuario.setVisible(false);

		tvUsuario.setDisable(false);

	}

	public void mostra() {

		lblNome.setVisible(true);
		lblEmail.setVisible(true);
		lblSenha.setVisible(true);
		lblUsuario.setVisible(true);

		btnEditar.setVisible(false);
		btnExcluir.setVisible(false);
		btnSalvar.setVisible(true);
		btnCancelar.setVisible(true);

		tfNome.setVisible(true);
		tfEmail.setVisible(true);
		tfSenha.setVisible(true);
		tfUsuario.setVisible(true);

		rbAdmin.setVisible(true);
		rbUsuario.setVisible(true);

	}

}
