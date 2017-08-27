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
import programa.app.EditaFuncionarioApp;
import programa.app.EditaMateriaPrimaApp;
import programa.app.GerenciaClienteApp;
import programa.app.GerenciaFuncionarioApp;
import programa.app.GerenciaMateriaPrimaApp;
import programa.app.PrincipalApp;
import programa.dao.ClienteDao;
import programa.dao.FuncionarioDao;
import programa.dao.MateriaPrimaDao;
import programa.entity.Cliente;
import programa.entity.Funcionario;
import programa.entity.MateriaPrima;
import programa.tabela.ClienteTabela;
import programa.tabela.FuncionarioTabela;
import programa.tabela.MateriaPrimaTabela;

public class GerenciaFuncionarioController implements Initializable {

	ObservableList<FuncionarioTabela> obervablelistPessoaTabela = FXCollections.observableArrayList();
	FuncionarioDao clienteDao = new FuncionarioDao();
	private static FuncionarioTabela funcionarioSelecionadoTabela;

	List<Funcionario> listCliente;

	@FXML
	private TableView<FuncionarioTabela> tvCliente;
	@FXML
	private TableColumn<FuncionarioTabela, String> tcNome;
	@FXML
	private TableColumn<FuncionarioTabela, String> tcCpf;
	@FXML
	private TableColumn<FuncionarioTabela, Integer> tcId;
	@FXML
	private TableColumn<FuncionarioTabela, String> tcTelefone;
	@FXML
	private TableColumn<FuncionarioTabela, String> tcCargo;
	@FXML
	private TableColumn<FuncionarioTabela, String> tcEndereco;

	@FXML
	private Button btnEditar = new Button();
	@FXML
	private Button btnExcluir = new Button();
	@FXML
	private TextField tfBuscaCliente = new TextField();
	@FXML
	private Button btnBuscarCliente = new Button();

	public void editar(ActionEvent event) {

		new EditaFuncionarioApp().start(new Stage());
		GerenciaFuncionarioApp.getStage().close();

	}

	public void excluir(ActionEvent event) {

		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setContentText("Voce deseja realmente excluir este Funcionário?");
		a.setHeaderText("Atenção");
		a.setTitle("Confirmaçao pendente");

		Optional<ButtonType> result = a.showAndWait();
		if (result.get() == ButtonType.OK) {
			clienteDao.removeFuncionario(funcionarioSelecionadoTabela.getId());
			Alert a1 = new Alert(AlertType.INFORMATION);
			a1.setContentText("Funcionário excluido com sucesso");
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

		new PrincipalApp().start(new Stage());
		GerenciaFuncionarioApp.getStage().close();

	}

	public void listaCliente() {
		listCliente = clienteDao.listFuncionario();

		if (!obervablelistPessoaTabela.isEmpty()) {
			obervablelistPessoaTabela.clear();
		}

		for (Funcionario funcionario : listCliente) {

			
			FuncionarioTabela funcionarioTabela  = new FuncionarioTabela(funcionario.getId(),funcionario.getNome(),
					funcionario.getTelefone() , funcionario.getCpf() , funcionario.getEndereco() , funcionario.getCargo());

			obervablelistPessoaTabela.add(funcionarioTabela);
			
		}
		

		tcId.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, Integer>("id"));
		tcNome.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("nome"));
		tcCargo.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("cargo"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("cpf"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("telefone"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("endereco"));
		tvCliente.setItems(obervablelistPessoaTabela);
	}

	public void buscarCliente(){

		listCliente = clienteDao.buscarFuncionario(tfBuscaCliente.getText());

		if (!obervablelistPessoaTabela.isEmpty()) {
			obervablelistPessoaTabela.clear();
		}


	for (Funcionario funcionario : listCliente) {

			
			FuncionarioTabela funcionarioTabela  = new FuncionarioTabela(funcionario.getId(),funcionario.getNome(),
					funcionario.getTelefone() , funcionario.getCpf() , funcionario.getEndereco() , funcionario.getCargo());

			obervablelistPessoaTabela.add(funcionarioTabela);
			
		}
		

		tcId.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, Integer>("id"));
		tcNome.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("nome"));
		tcCargo.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("cargo"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("cpf"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("telefone"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("endereco"));
		tvCliente.setItems(obervablelistPessoaTabela);
		
	}
	public void menu() {

	}

	public void clicar(MouseEvent event) {

		try {

			funcionarioSelecionadoTabela = tvCliente.getSelectionModel().getSelectedItem();
			System.out.println(funcionarioSelecionadoTabela.getId());
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
		return funcionarioSelecionadoTabela.getId();
	}

}
