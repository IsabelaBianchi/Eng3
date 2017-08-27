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
import programa.app.EditaMateriaPrimaApp;
import programa.app.EditaProdutoApp;
import programa.app.GerenciaClienteApp;
import programa.app.GerenciaMateriaPrimaApp;
import programa.app.GerenciaProdutoApp;
import programa.app.PrincipalApp;
import programa.dao.ClienteDao;
import programa.dao.MateriaPrimaDao;
import programa.dao.ProdutoDao;
import programa.entity.Cliente;
import programa.entity.MateriaPrima;
import programa.entity.Produto;
import programa.tabela.ClienteTabela;
import programa.tabela.CriarProdutoTabela;
import programa.tabela.MateriaPrimaTabela;

public class GerenciaProdutoController implements Initializable {

	ObservableList<CriarProdutoTabela> obervablelistProdutoTabela = FXCollections.observableArrayList();
	ProdutoDao produtoDao = new ProdutoDao();
	private static CriarProdutoTabela produtoSelecionadoTabela;

	List<Produto> listCliente;

	@FXML
	private TableView<CriarProdutoTabela> tvCliente;
	@FXML
	private TableColumn<CriarProdutoTabela, String> tcProduto;
	@FXML
	private TableColumn<CriarProdutoTabela, String> tcPreco;

	@FXML
	private Button btnEditar = new Button();
	@FXML
	private Button btnExcluir = new Button();
	@FXML
	private TextField tfBuscaCliente = new TextField();
	@FXML
	private Button btnBuscarCliente = new Button();

	public void editar(ActionEvent event) {

		new EditaProdutoApp().start(new Stage());
		GerenciaProdutoApp.getStage().close();

	}

	public void excluir(ActionEvent event) {

		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setContentText("Voce deseja realmente excluir este Produto?");
		a.setHeaderText("Atenção");
		a.setTitle("Confirmaçao pendente");

		Optional<ButtonType> result = a.showAndWait();
		if (result.get() == ButtonType.OK) {
			produtoDao.removeProduto(produtoSelecionadoTabela.getIdProduto());
			Alert a1 = new Alert(AlertType.INFORMATION);
			a1.setContentText("Produto excluido com sucesso");
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
		GerenciaProdutoApp.getStage().close();

	}

	public void listaCliente() {
		listCliente = produtoDao.listProduto();

		if (!obervablelistProdutoTabela.isEmpty()) {
			obervablelistProdutoTabela.clear();
		}

		for (Produto produto : listCliente) {

			
			CriarProdutoTabela criarProdutoTabela  = new CriarProdutoTabela(
					produto.getNomeProduto(), produto.getPrecoProduto(), produto.getIdProduto());

			obervablelistProdutoTabela.add(criarProdutoTabela);
			
		}
		

		tcProduto.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, String>("NomeProduto"));
		tcPreco.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela , String>("valorProdutoString"));

		tvCliente.setItems(obervablelistProdutoTabela);
		
	}

	public void buscarCliente(){

		listCliente = produtoDao.buscarProduto(tfBuscaCliente.getText());

		if (!obervablelistProdutoTabela.isEmpty()) {
			obervablelistProdutoTabela.clear();
		}


for (Produto produto : listCliente) {

			
			CriarProdutoTabela criarProdutoTabela  = new CriarProdutoTabela(
					produto.getNomeProduto(), produto.getPrecoProduto(), produto.getIdProduto());

			obervablelistProdutoTabela.add(criarProdutoTabela);
			
		}
		

		tcProduto.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, String>("NomeProduto"));
		tcPreco.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela , String>("valorProdutoString"));
		

		tvCliente.setItems(obervablelistProdutoTabela);
		
		
	}
	public void menu() {

	}

	public void clicar(MouseEvent event) {

		try {

			produtoSelecionadoTabela = tvCliente.getSelectionModel().getSelectedItem();
			System.out.println(produtoSelecionadoTabela.getIdProduto());
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
		return produtoSelecionadoTabela.getIdProduto();
	}

}
