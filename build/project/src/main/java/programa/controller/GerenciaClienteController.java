package programa.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import programa.app.EditaClienteApp;
import programa.app.GerenciaClienteApp;
import programa.app.PrincipalApp;
import programa.dao.ClienteDao;
import programa.entity.Cliente;
import programa.tabela.ClienteTabela;

public class GerenciaClienteController implements Initializable {

	ObservableList<ClienteTabela> obervablelistPessoaTabela = FXCollections.observableArrayList();
	ClienteDao clienteDao = new ClienteDao();
	private static ClienteTabela clienteSelecionadoTabela;

	List<Cliente> listCliente;

	@FXML
	private TableView<ClienteTabela> tvCliente;
	@FXML
	private TableColumn<ClienteTabela, String> tcNome;
	@FXML
	private TableColumn<ClienteTabela, String> tcSobrenome;
	@FXML
	private TableColumn<ClienteTabela, String> tcTelefone;
	@FXML
	private TableColumn<ClienteTabela, String> tcCelular;
	@FXML
	private TableColumn<ClienteTabela, String> tcEmail;
	@FXML
	private TableColumn<ClienteTabela, String> tcEndereco;
	@FXML
	private TableColumn<ClienteTabela, String> tcCidade;
	@FXML
	private TableColumn<ClienteTabela, String> tcCpf;
	@FXML
	private Button btnEditar = new Button();
	@FXML
	private Button btnExcluir = new Button();
	@FXML
	private TextField tfBuscaCliente = new TextField();
	@FXML
	private Button btnBuscarCliente = new Button();

	public void editar(ActionEvent event) {

		new EditaClienteApp().start(new Stage());
		GerenciaClienteApp.getStage().close();

	}

	public void excluir(ActionEvent event) {

		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setContentText("Voce deseja realmente excluir este cliente?");
		a.setHeaderText("Atenção");
		a.setTitle("Confirmaçao pendente");

		Optional<ButtonType> result = a.showAndWait();
		if (result.get() == ButtonType.OK) {
			clienteDao.removeCliente(clienteSelecionadoTabela.getIdCliente());
			Alert a1 = new Alert(AlertType.INFORMATION);
			a1.setContentText("Cliente excluida com sucesso");
			a1.setHeaderText(null);
			a1.setTitle("Sucesso");
			a1.showAndWait();
			listaCliente();
			btnEditar.setVisible(false);
			btnExcluir.setVisible(false);

		} else {

			btnEditar.setVisible(false);
			btnExcluir.setVisible(false);
		}

	}

	public void sair(ActionEvent event) {

		//new PrincipalApp().start(new Stage());
		GerenciaClienteApp.getStage().close();

	}

	public void listaCliente() {
		listCliente = clienteDao.listCliente();

		if (!obervablelistPessoaTabela.isEmpty()) {
			obervablelistPessoaTabela.clear();
		}

		for (Cliente cliente : listCliente) {

			ClienteTabela clienteTabela = new ClienteTabela(cliente.getId(), cliente.getNomeCliente(),
					cliente.getSobrenome(), cliente.getTelefone(), cliente.getCelular(), cliente.getEmailCliente(),
					cliente.getEndereco(), cliente.getCidade() , cliente.getCpf());

			obervablelistPessoaTabela.add(clienteTabela);
			
		}
		

		tcNome.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("NomeCliente"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Cpf"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Telefone"));
		tcCelular.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Celular"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("EmailCliente"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Endereco"));
		tcCidade.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Cidade"));

		tvCliente.setItems(obervablelistPessoaTabela);
	}

	public void buscarCliente(){

		listCliente = clienteDao.buscarCliente(tfBuscaCliente.getText());

		if (!obervablelistPessoaTabela.isEmpty()) {
			obervablelistPessoaTabela.clear();
		}

		for (Cliente cliente : listCliente) {

			ClienteTabela clienteTabela = new ClienteTabela(cliente.getId(), cliente.getNomeCliente(),
					cliente.getSobrenome(), cliente.getTelefone(), cliente.getCelular(), cliente.getEmailCliente(),
					cliente.getEndereco(), cliente.getCidade(),cliente.getCpf());

			obervablelistPessoaTabela.add(clienteTabela);

		}

		tcNome.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("NomeCliente"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Cpf"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Telefone"));
		tcCelular.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Celular"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("EmailCliente"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Endereco"));
		tcCidade.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Cidade"));

		tvCliente.setItems(obervablelistPessoaTabela);
		
		
	}
	public void menu() {

	}

	public void clicar(MouseEvent event) {

		try {

			clienteSelecionadoTabela = tvCliente.getSelectionModel().getSelectedItem();
			System.out.println(clienteSelecionadoTabela.getIdCliente());
			btnEditar.setVisible(true);
			btnExcluir.setVisible(true);

		} catch (Exception e) {
			System.out.println("Erro");
		}

	}

	public void initialize(URL location, ResourceBundle resources) {

		btnEditar.setVisible(false);
		btnExcluir.setVisible(false);

		listaCliente();

	}

	public static int getCliente() {
		return clienteSelecionadoTabela.getIdCliente();
	}

}
