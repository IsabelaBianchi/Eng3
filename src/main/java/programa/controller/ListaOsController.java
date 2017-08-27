package programa.controller;

import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import programa.app.EditaEncomendaApp;
import programa.app.ListaEncomendaApp;
import programa.app.PrincipalApp;
import programa.dao.ClienteDao;
import programa.dao.OsDao;
import programa.entity.Cliente;
import programa.entity.Os;
import programa.tabela.OsTabela;
import programa.valida.ValidaCampo;

public class ListaOsController implements Initializable {

	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		cbMes.setItems(observableMes);
		cbMes.setValue(observableMes);
		cbAno.setItems(observableAno);
		cbAno.setValue(observableAno);
		cbDia.setItems(observableDia);
		cbDia.setValue(observableDia);
		cbMesEnt.setItems(observableMes);
		cbMesEnt.setValue(observableMes);
		cbAnoEnt.setItems(observableAno);
		cbAnoEnt.setValue(observableAno);
		cbDiaEnt.setItems(observableDia);
		cbDiaEnt.setValue(observableDia);
		
		
		btnEditar.setVisible(false);
		btnExcluir.setVisible(false);

	}

	ClienteDao clienteDao = new ClienteDao();

	int dia, mes, ano;
	Os os;
	OsDao osDao = new OsDao();
	private static OsTabela osTabelaSelecionado;

	List<Os> listOs;

	ObservableList<OsTabela> observableOsTabela = FXCollections.observableArrayList();

	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbAnoEnt = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbMesEnt = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbDiaEnt = new ChoiceBox();
	
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbAno = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbMes = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbDia = new ChoiceBox();

	private ObservableList<String> observableDia = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
			"26", "27", "28", "29", "30", "31");
	private ObservableList<String> observableMes = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12");
	private ObservableList<String> observableAno = FXCollections.observableArrayList("2016", "2017", "2018", "2019",
			"2020", "2021", "2022");

	@FXML
	private TableView<OsTabela> tvTabela = new TableView<OsTabela>();

	@FXML
	private TableColumn<OsTabela, Integer> tcId = new TableColumn<OsTabela, Integer>();
	@FXML
	private TableColumn<String, String> tcCliente = new TableColumn<String, String>();
	@FXML
	private TableColumn<OsTabela, String> tcDataEnt = new TableColumn<OsTabela, String>();
	@FXML
	private TableColumn<OsTabela, String> tcAssunto = new TableColumn<OsTabela, String>();
	@FXML
	private TableColumn<OsTabela, String> tcSituacao = new TableColumn<OsTabela, String>();
	@FXML
	private TableColumn<OsTabela, String> tcValor = new TableColumn<OsTabela, String>();
	@FXML
	private TableColumn<OsTabela, String> tcData = new TableColumn<OsTabela,String>();
	@FXML
	private Button btnTodas = new Button();
	@FXML
	private Button btnEditar = new Button();
	@FXML
	private Button btnExcluir = new Button();
	@FXML
	private Button btnBuscaCliente = new Button();
	@FXML
	private TextField tfCliente = new TextField();
	@FXML
	private Button btnBuscarEnt = new Button();
	
	public void buscar() {

		if (cbDia.getValue().toString().equals(
				"[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31]")) {
			if (cbMes.getValue().toString().equals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]")
					|| cbAno.getValue().toString().equals("[2016, 2017, 2018, 2019, 2020, 2021, 2022]")) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Selecione ao menos o mes e o ano");
				a.setTitle("Erro");
				a.setHeaderText(null);
				a.showAndWait();
			} else {
				System.out.println("Buscou sem o dia");
				mes = Integer.parseInt(cbMes.getValue().toString());
				ano = Integer.parseInt(cbAno.getValue().toString());

				listOs = osDao.getOs(mes, ano);

				listaOs();

			}
		} else if (cbMes.getValue().toString().equals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]")
				|| cbAno.getValue().toString().equals("[2016, 2017, 2018, 2019, 2020, 2021, 2022]")) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Selecione ao menos o mes e o ano");
			a.setTitle("Erro");
			a.setHeaderText(null);
			a.showAndWait();
		} else {
			System.out.println("Buscou com dia");
			dia = Integer.parseInt(cbDia.getValue().toString());
			mes = Integer.parseInt(cbMes.getValue().toString());
			ano = Integer.parseInt(cbAno.getValue().toString());

			listOs = osDao.getOs(dia, mes, ano);

			listaOs();

		}

	}
	public void buscarEnt() {

		if (cbDiaEnt.getValue().toString().equals(
				"[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31]")) {
			if (cbMesEnt.getValue().toString().equals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]")
					|| cbAnoEnt.getValue().toString().equals("[2016, 2017, 2018, 2019, 2020, 2021, 2022]")) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Selecione ao menos o mes e o ano");
				a.setTitle("Erro");
				a.setHeaderText(null);
				a.showAndWait();
			} else {
				System.out.println("Buscou sem o dia");
				mes = Integer.parseInt(cbMesEnt.getValue().toString());
				ano = Integer.parseInt(cbAnoEnt.getValue().toString());

				listOs = osDao.getOsEnt(mes, ano);

				listaOs();

			}
		} else if (cbMesEnt.getValue().toString().equals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]")
				|| cbAnoEnt.getValue().toString().equals("[2016, 2017, 2018, 2019, 2020, 2021, 2022]")) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Selecione ao menos o mes e o ano");
			a.setTitle("Erro");
			a.setHeaderText(null);
			a.showAndWait();
		} else {
			System.out.println("Buscou com dia");
			dia = Integer.parseInt(cbDiaEnt.getValue().toString());
			mes = Integer.parseInt(cbMesEnt.getValue().toString());
			ano = Integer.parseInt(cbAnoEnt.getValue().toString());

			listOs = osDao.getOsEnt(dia, mes, ano);

			listaOs();

		}

	}

	public void todas() {

		System.out.println("Todas");
		listOs = osDao.listOs();
		listaOs();

	}

	public void editar() {
		new EditaEncomendaApp().start(new Stage());
		ListaEncomendaApp.getStage().hide();

	}

	public void excluir() {

		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setContentText("Voce deseja realmente excluir esta os?");
		a.setHeaderText("Atenção");
		a.setTitle("Confirmaçao pendente");

		Optional<ButtonType> result = a.showAndWait();
		if (result.get() == ButtonType.OK) {
			osDao.removeOs(osTabelaSelecionado.getIdOs());
			Alert a1 = new Alert(AlertType.INFORMATION);
			a1.setContentText("Os excluida com sucesso");
			a1.setHeaderText(null);
			a1.setTitle("Sucesso");
			a1.showAndWait();
			limparTabela();

		} else {

			btnEditar.setVisible(false);
			btnExcluir.setVisible(false);
		}

	}

	public void buscarCliente() {
		if (ValidaCampo.isEmpty(tfCliente.getText())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Erro");
			a.setContentText("Campo cliente nao pode ser em branco");
			a.setHeaderText(null);
			a.showAndWait();
		} else {

			Cliente cliente = clienteDao.getCliente(tfCliente.getText());

			if (cliente == null) {
				Alert a2 = new Alert(AlertType.ERROR);
				a2.setTitle("Erro");
				a2.setContentText("Nao existe o cliente informado");
				a2.setHeaderText(null);
				a2.showAndWait();
				tfCliente.clear();
			} else {

				listOs = osDao.getOsCliente(cliente.getId());
				listaOs();

			}

		}
	}

	public void sair() {

		new PrincipalApp().start(new Stage());
		ListaEncomendaApp.getStage().close();
	}

	public void clicou(MouseEvent event) {
		try {
			osTabelaSelecionado = tvTabela.getSelectionModel().getSelectedItem();
			System.out.println(osTabelaSelecionado.getIdOs());
			btnEditar.setVisible(true);
			btnExcluir.setVisible(true);

		} catch (Exception e) {
			System.out.println("Erro");
		}
	}

	public void listaOs() {

		if (!observableOsTabela.isEmpty()) {
			observableOsTabela.clear();
		}

		for (Os os : listOs) {

			OsTabela osTabela = new OsTabela(os.getIdOs(), os.getIdCliente(), os.getAssunto(), os.getDescricao(),
					os.getDia(), os.getMes(), os.getAno(), os.getSituacao(), os.getTotal(),
					clienteDao.getCliente(os.getIdCliente()).getNomeCliente(),os.getDiaEnt() , os.getMesEnt(), os.getAnoEnt());

			observableOsTabela.add(osTabela);

		}
		tcId.setCellValueFactory(new PropertyValueFactory<OsTabela, Integer>("idOs"));
		tcCliente.setCellValueFactory(new PropertyValueFactory<String, String>("nomeCliente"));
		tcAssunto.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("assunto"));
		tcData.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("data"));
		tcValor.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("valorString"));
		tcSituacao.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("situacaoString"));
		tcDataEnt.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("dataEnt"));
		tvTabela.setItems(observableOsTabela);

	}

	public void limparTabela() {
		if (!observableOsTabela.isEmpty()) {
			observableOsTabela.clear();
		}
		tvTabela.setItems(observableOsTabela);
	}

	public static int getIdOs() {
		return osTabelaSelecionado.getIdOs();
	}

}
