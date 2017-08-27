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
import programa.app.GerenciaClienteApp;
import programa.app.GerenciaMateriaPrimaApp;
import programa.app.PrincipalApp;
import programa.dao.ClienteDao;
import programa.dao.MateriaPrimaDao;
import programa.entity.Cliente;
import programa.entity.MateriaPrima;
import programa.tabela.ClienteTabela;
import programa.tabela.MateriaPrimaTabela;

public class GerenciaMateriaPrimaController implements Initializable {

	ObservableList<MateriaPrimaTabela> obervablelistPessoaTabela = FXCollections.observableArrayList();
	MateriaPrimaDao clienteDao = new MateriaPrimaDao();
	private static MateriaPrimaTabela materiaSelecionadoTabela;

	List<MateriaPrima> listCliente;

	@FXML
	private TableView<MateriaPrimaTabela> tvCliente;
	@FXML
	private TableColumn<MateriaPrimaTabela, String> tcNome;

	@FXML
	private Button btnEditar = new Button();
	@FXML
	private Button btnExcluir = new Button();
	@FXML
	private TextField tfBuscaCliente = new TextField();
	@FXML
	private Button btnBuscarCliente = new Button();

	public void editar(ActionEvent event) {

		new EditaMateriaPrimaApp().start(new Stage());
		GerenciaMateriaPrimaApp.getStage().close();

	}

	public void excluir(ActionEvent event) {

		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setContentText("Voce deseja realmente excluir esta Materia-Prima?");
		a.setHeaderText("Atenção");
		a.setTitle("Confirmaçao pendente");

		Optional<ButtonType> result = a.showAndWait();
		if (result.get() == ButtonType.OK) {
			clienteDao.removeMateriaPrima(materiaSelecionadoTabela.getIdMateria());
			Alert a1 = new Alert(AlertType.INFORMATION);
			a1.setContentText("Materia-Prima excluida com sucesso");
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
		GerenciaMateriaPrimaApp.getStage().close();

	}

	public void listaCliente() {
		listCliente = clienteDao.listMateriaPrima();

		if (!obervablelistPessoaTabela.isEmpty()) {
			obervablelistPessoaTabela.clear();
		}

		for (MateriaPrima materiaPrima : listCliente) {

			
			MateriaPrimaTabela materiaPrimaTabela  = new MateriaPrimaTabela(materiaPrima.getNome(), materiaPrima.getIdMateria());

			obervablelistPessoaTabela.add(materiaPrimaTabela);
			
		}
		

		tcNome.setCellValueFactory(new PropertyValueFactory<MateriaPrimaTabela, String>("Nome"));
		

		tvCliente.setItems(obervablelistPessoaTabela);
	}

	public void buscarCliente(){

		listCliente = clienteDao.buscarFornecedor(tfBuscaCliente.getText());

		if (!obervablelistPessoaTabela.isEmpty()) {
			obervablelistPessoaTabela.clear();
		}


		for (MateriaPrima materiaPrima : listCliente) {

			
			MateriaPrimaTabela materiaPrimaTabela  = new MateriaPrimaTabela(materiaPrima.getNome(), materiaPrima.getIdMateria());

			obervablelistPessoaTabela.add(materiaPrimaTabela);
			
		}
		

		tcNome.setCellValueFactory(new PropertyValueFactory<MateriaPrimaTabela, String>("Nome"));
		

		tvCliente.setItems(obervablelistPessoaTabela);
		
		
	}
	public void menu() {

	}

	public void clicar(MouseEvent event) {

		try {

			materiaSelecionadoTabela = tvCliente.getSelectionModel().getSelectedItem();
			System.out.println(materiaSelecionadoTabela.getNome());
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
		return materiaSelecionadoTabela.getIdMateria();
	}

}
