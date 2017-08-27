package programa.controller;

import java.net.URL;
import java.text.DecimalFormat;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import programa.app.EditaOsApp;
import programa.app.ListaCaixaApp;
import programa.app.ListaOsApp;
import programa.app.PrincipalApp;
import programa.dao.CaixaDao;
import programa.dao.ClienteDao;
import programa.dao.OsDao;
import programa.entity.Caixa;
import programa.entity.Cliente;
import programa.entity.Os;
import programa.tabela.CaixaTabela;
import programa.tabela.OsTabela;
import programa.valida.ValidaCampo;

public class ListaCaixaController implements Initializable {

	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		cbMes.setItems(observableMes);
		cbMes.setValue(observableMes);
		cbAno.setItems(observableAno);
		cbAno.setValue(observableAno);
		cbDia.setItems(observableDia);
		cbDia.setValue(observableDia);
		lblTotalEnt.setText("");
		lblTotalSai.setText("");
		
	}


	int dia, mes, ano;
	Os os;
	OsDao osDao = new OsDao();
	
	Caixa caixa = new Caixa();
	CaixaDao caixaDao = new CaixaDao();
	
	//private static OsTabela osTabelaSelecionado;
	private static CaixaTabela caixaTabelaSelecionada;

	List<Caixa> listOs;

	ObservableList<CaixaTabela> observableOsTabela = FXCollections.observableArrayList();

	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbAno = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbMes = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbDia = new ChoiceBox();
	@FXML
	private Label lblTotalEnt = new Label();
	@FXML
	private Label lblTotalSai = new Label();


	private ObservableList<String> observableDia = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
			"26", "27", "28", "29", "30", "31");
	private ObservableList<String> observableMes = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12");
	private ObservableList<String> observableAno = FXCollections.observableArrayList("2016", "2017", "2018", "2019",
			"2020", "2021", "2022");

	@FXML
	private TableView<CaixaTabela> tvTabela = new TableView<CaixaTabela>();

	@FXML
	private TableColumn<CaixaTabela, String> tcDia = new TableColumn<CaixaTabela, String>();	
	@FXML
	private TableColumn<CaixaTabela, String> tcDescricao = new TableColumn<CaixaTabela, String>();
	@FXML
	private TableColumn<CaixaTabela, String> tcSituacao = new TableColumn<CaixaTabela, String>();
	@FXML
	private TableColumn<CaixaTabela, Double> tcValor = new TableColumn<CaixaTabela, Double>();
	@FXML
	private Button btnBuscar = new Button();
	
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

				listOs = caixaDao.getCaixa(mes, ano);

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

			listOs = caixaDao.getCaixa(dia, mes, ano);
			
			listaOs();

		}

	}

//	public void todas() {
//
//		System.out.println("Todas");
//		listOs = osDao.listOs();
//		listaOs();
//
//	}

	public void sair() {

		//new PrincipalApp().start(new Stage());
		ListaCaixaApp.getStage().close();
	}

//	public void clicou(MouseEvent event) {
//		try {
//			osTabelaSelecionado = tvTabela.getSelectionModel().getSelectedItem();
//			System.out.println(osTabelaSelecionado.getIdOs());
//			btnEditar.setVisible(true);
//			btnExcluir.setVisible(true);
//
//		} catch (Exception e) {
//			System.out.println("Erro");
//		}
//	}
	double valorEntrada = 0;
	double valorSaida = 0;
	public void listaOs() {

		if (!observableOsTabela.isEmpty()) {
			observableOsTabela.clear();
		}

		for (Caixa caixa : listOs) {
			System.out.println("......"+listOs);
			CaixaTabela caixaTabela = new CaixaTabela(caixa.getTransId() , caixa.getDescricao() , caixa.getValor(),
					caixa.getIsEnt() , caixa.getDia(), caixa.getMes() , caixa.getAno());

			observableOsTabela.add(caixaTabela);
			if(caixa.getIsEnt()==1){
				valorEntrada = valorEntrada+caixa.getValor();
			}
			if(caixa.getIsEnt()==2){
				valorSaida= valorSaida+caixa.getValor();
			}
			
		}
		
		DecimalFormat df = new DecimalFormat("0.00");
		String valorEntFormat = (df.format(valorEntrada));
		String valorSaidaFormat = (df.format(valorSaida));
		
		lblTotalEnt.setText(("R$  "+valorEntFormat));
		lblTotalSai.setText(("R$  " + valorSaidaFormat));
		
		//tcId.setCellValueFactory(new PropertyValueFactory<OsTabela, Integer>("idOs"));
		//tcCliente.setCellValueFactory(new PropertyValueFactory<String, String>("nomeCliente"));
		tcDescricao.setCellValueFactory(new PropertyValueFactory<CaixaTabela, String>("descricao"));
		tcDia.setCellValueFactory(new PropertyValueFactory<CaixaTabela, String>("data"));
		tcValor.setCellValueFactory(new PropertyValueFactory<CaixaTabela, Double>("valor"));
		tcSituacao.setCellValueFactory(new PropertyValueFactory<CaixaTabela, String>("entrada"));

		tvTabela.setItems(observableOsTabela);

	}
	

	public void limparTabela() {
		if (!observableOsTabela.isEmpty()) {
			observableOsTabela.clear();
		}
		tvTabela.setItems(observableOsTabela);
	}

	public static int getIdOs() {
		return caixaTabelaSelecionada.getTransId();
	}

}
