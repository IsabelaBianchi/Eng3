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
import programa.app.EditaFornecedorApp;
import programa.app.GerenciaClienteApp;
import programa.app.GerenciaFornecedorApp;
import programa.app.PrincipalApp;
import programa.dao.ClienteDao;
import programa.dao.FornecedorDao;
import programa.entity.Cliente;
import programa.entity.Fornecedor;
import programa.tabela.ClienteTabela;
import programa.tabela.FornecedorTabela;

public class GerenciaFornecedorController implements Initializable {

	ObservableList<FornecedorTabela> obervablelistPessoaTabela = FXCollections.observableArrayList();
	FornecedorDao clienteDao = new FornecedorDao();
	private static FornecedorTabela clienteSelecionadoTabela;

	List<Fornecedor> listCliente;

	@FXML
	private TableView<FornecedorTabela> tvCliente;
	@FXML
	private TableColumn<FornecedorTabela, String> tcNome;
	@FXML
	private TableColumn<FornecedorTabela, String> tcTelefone;
	
	@FXML
	private Button btnEditar = new Button();
	@FXML
	private Button btnExcluir = new Button();
	@FXML
	private TextField tfBuscaCliente = new TextField();
	@FXML
	private Button btnBuscarCliente = new Button();

	public void editar(ActionEvent event) {

		new EditaFornecedorApp().start(new Stage());
		GerenciaFornecedorApp.getStage().close();

	}

	public void excluir(ActionEvent event) {

		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setContentText("Voce deseja realmente excluir este fornecedor?");
		a.setHeaderText("Atenção");
		a.setTitle("Confirmaçao pendente");

		Optional<ButtonType> result = a.showAndWait();
		if (result.get() == ButtonType.OK) {
			clienteDao.removeFornecedor(clienteSelecionadoTabela.getIdFornecedor());
			Alert a1 = new Alert(AlertType.INFORMATION);
			a1.setContentText("Fornecedor excluido com sucesso");
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
		listCliente = clienteDao.listFornecedor();

		if (!obervablelistPessoaTabela.isEmpty()) {
			obervablelistPessoaTabela.clear();
		}

		for (Fornecedor fornecedor : listCliente) {

			FornecedorTabela fornecedorTabela = new FornecedorTabela(fornecedor.getIdFornecedor(), fornecedor.getNomeFornecedor(),
					fornecedor.getFoneFornecedor());

			obervablelistPessoaTabela.add(fornecedorTabela);
			
		}
		

		tcNome.setCellValueFactory(new PropertyValueFactory<FornecedorTabela, String>("NomeFornecedor"));
		
		tcTelefone.setCellValueFactory(new PropertyValueFactory<FornecedorTabela, String>("Telefone"));
		

		tvCliente.setItems(obervablelistPessoaTabela);
	}

	public void buscarCliente(){

		listCliente = clienteDao.buscarFornecedor(tfBuscaCliente.getText());

		if (!obervablelistPessoaTabela.isEmpty()) {
			obervablelistPessoaTabela.clear();
		}

		for (Fornecedor fornecedor : listCliente) {


			FornecedorTabela fornecedorTabela = new FornecedorTabela(fornecedor.getIdFornecedor(), fornecedor.getNomeFornecedor(),
					fornecedor.getFoneFornecedor());

			obervablelistPessoaTabela.add(fornecedorTabela);

		}

tcNome.setCellValueFactory(new PropertyValueFactory<FornecedorTabela, String>("NomeFornecedor"));
		
		tcTelefone.setCellValueFactory(new PropertyValueFactory<FornecedorTabela, String>("Telefone"));
		

		tvCliente.setItems(obervablelistPessoaTabela);
		
		
	}
	public void menu() {

	}

	public void clicar(MouseEvent event) {

		try {

			clienteSelecionadoTabela = tvCliente.getSelectionModel().getSelectedItem();
			System.out.println(clienteSelecionadoTabela.getIdFornecedor());
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
		return clienteSelecionadoTabela.getIdFornecedor();
	}

}
