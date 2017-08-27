package programa.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import programa.app.EditaOsApp;
import programa.app.ListaOsApp;
import programa.dao.ClienteDao;
import programa.dao.OsDao;
import programa.dao.ProdutoDao;
import programa.entity.Cliente;
import programa.entity.Os;
import programa.entity.Produto;
import programa.tabela.ClienteTabela;
import programa.tabela.CriarProdutoTabela;
import programa.valida.ValidaCampo;

public class EditaOsController implements Initializable {
	ClienteDao clienteDao = new ClienteDao();
	ProdutoDao produtoDao = new ProdutoDao();
	OsDao osDao = new OsDao();
	Os osEdita;

	@SuppressWarnings("unused")
	private List<Cliente> listCliente;
	private List<Produto> listProduto;
	

	private ObservableList<String> observableSituacao = FXCollections.observableArrayList("Aberto", "Concluido");
	private ObservableList<CriarProdutoTabela> observableProdutoTabela = FXCollections.observableArrayList();
	private ObservableList<Produto> observableProduto = FXCollections.observableArrayList();
	private ObservableList<String> observableProdutoNome = FXCollections.observableArrayList();
	ArrayList< String > prodList = new ArrayList< String >();
	ArrayList< String > quantidade = new ArrayList< String >();
	ArrayList< CriarProdutoTabela > arrayProduto = new ArrayList< CriarProdutoTabela >();
	private double countTotal=0;
	private int indexToSave=0;
	
	
	@FXML
	private TextField tfAssunto = new TextField();
	@FXML
	private TextArea taDescricao = new TextArea();
	@FXML
	private TextField tfValor = new TextField();
	@FXML
	private TextField tfData = new TextField();
	@FXML
	private TextField tfCliente = new TextField();
	@FXML
	private TextField tfDataEntrega = new TextField();
	@FXML
	private TextField tfQuantidade= new TextField();
	@FXML 
	private Label lblTotal = new Label();
	
	

	@FXML
	private TextField tfIdOs = new TextField();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbNomeCliente = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbSituacao = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbProduto = new ChoiceBox();
	@FXML
	private Button btnAdiciona = new Button();
	@FXML
	private Button btnDelete = new Button();
	@FXML
	private TableView<CriarProdutoTabela> tvTabelaProduto = new TableView<CriarProdutoTabela>();
	@FXML
	private TableColumn<CriarProdutoTabela, String> tcProduto = new TableColumn<CriarProdutoTabela, String>();
	@FXML
	private TableColumn<CriarProdutoTabela, Integer> tcQuantidade = new TableColumn<CriarProdutoTabela, Integer>();
	@FXML
	private TableColumn<CriarProdutoTabela, Double> tcValor = new TableColumn<CriarProdutoTabela, Double>();
	@FXML
	private TableColumn<CriarProdutoTabela, Integer> tcId = new TableColumn<CriarProdutoTabela, Integer>();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbDia = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbMes = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbAno = new ChoiceBox();
	
	private ObservableList<String> observableDia = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
			"26", "27", "28", "29", "30", "31");
	private ObservableList<String> observableMes = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12");
	private ObservableList<String> observableAno = FXCollections.observableArrayList("2017", "2018", "2019",
			"2020", "2021", "2022");

	public void salvar(ActionEvent event) {
			
			if (cbSituacao.getValue().toString().equals("Aberto")) {
				osEdita.setSituacao(1);
			}else if (cbSituacao.getValue().toString().equals("Concluido")) {
				osEdita.setSituacao(0);
			}	
		osEdita.setDescricao(taDescricao.getText());
		if(cbDia.getValue()=="" || cbMes.getValue()=="" || cbAno.getValue()==""){
			osEdita.setDiaEnt(osEdita.getDiaEnt());
			osEdita.setMesEnt(osEdita.getMesEnt());
			osEdita.setAnoEnt(osEdita.getAnoEnt());
		}else{
		osEdita.setDiaEnt(Integer.parseInt(cbDia.getValue().toString()));
		osEdita.setMesEnt(Integer.parseInt(cbMes.getValue().toString()));
		osEdita.setAnoEnt(Integer.parseInt(cbAno.getValue().toString()));
		}
		osEdita.setEndereco(tfAssunto.getText());

		osDao.updateOs(osEdita);

		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setTitle("Sucesso");
		a.setContentText("Encomenda atualizada");
		a.setHeaderText(null);
		a.showAndWait();

		new ListaOsApp().start(new Stage());
		EditaOsApp.getStage().close();
		
	}

	public void cancelar(ActionEvent event) {
		new ListaOsApp().start(new Stage());
		EditaOsApp.getStage().close();

	}

	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {

		tfCliente.setEditable(false);
		tfData.setEditable(false);
		tfIdOs.setEditable(false);
		tvTabelaProduto.setEditable(false);
		cbSituacao.setItems(observableSituacao);
		cbSituacao.setValue(observableSituacao);
		cbMes.setItems(observableMes);
		cbMes.setValue("");
		cbAno.setItems(observableAno);
		cbAno.setValue("");
		cbDia.setItems(observableDia);
		cbDia.setValue("");
		

		pegaIdOsParaEditar();
		listarProduto();
	}

	@SuppressWarnings("unchecked")
	public void pegaIdOsParaEditar() {

		osEdita = osDao.getOs(ListaOsController.getIdOs());
		tfIdOs.setText(Integer.toString(osEdita.getIdOs()));
		tfIdOs.setEditable(false);
		tfCliente.setText(String.valueOf(osEdita.getIdCliente()));
		// tfCliente.setText(osEdita.getIdCliente());
		tfCliente.setEditable(false);
		tfAssunto.setText(osEdita.getDescricao());
		tfAssunto.setText(osEdita.getEndereco());
		taDescricao.setText(osEdita.getDescricao());
		DecimalFormat df = new DecimalFormat("0.00");
		String valorFormat = (df.format(osEdita.getTotal()));
		
		lblTotal.setText(String.valueOf("R$ "+valorFormat));
		
		
		String prodsString = osEdita.getProdItems();
		
		if(prodsString.contains(",")){
			prodsString=prodsString.replace("[", "");
			prodsString=prodsString.replace("]", "");
			prodsString=prodsString.replace(",", "");
			prodsString=prodsString.replace(" ", "");
		}else{
			prodsString=prodsString.replace("[", "");
			prodsString=prodsString.replace("]", "");
		}
		
		char[] productsCharArray = prodsString.toCharArray();
		
		String prodsQuant = osEdita.getQuantidade();
		
		
		prodsQuant=prodsQuant.replace("[", "");
		prodsQuant=prodsQuant.replace("]", "");
	
		
		String[] resultado = prodsQuant.split(", ");

		int idx = 0;
		
		for (char output : productsCharArray) {
			String prods = Character.toString(output);

			listProduto = produtoDao.buscarProdutoId(prods);
			System.out.println("......"+listProduto);
			
			for (Produto produto : listProduto) {
				
				int qtdString = Integer.parseInt(resultado[idx++]);
			
				CriarProdutoTabela criarProdutoTabela = new CriarProdutoTabela(produto.getNomeProduto(),
				produto.getPrecoProduto(),qtdString,produto.getIdProduto());
				
				observableProdutoTabela.add(criarProdutoTabela);
				tcProduto.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, String>("nomeProduto"));
				tcQuantidade.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, Integer>("qtdProduto"));
				tcValor.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, Double>("valorProduto"));
				tcId.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, Integer>("idProduto"));
				
				tvTabelaProduto.setItems(observableProdutoTabela);
				indexToSave = idx;
				
			}
		}
		
		
			
		if (osEdita.getSituacao() == 1) {
			cbSituacao.setValue("Aberto");

		} else if (osEdita.getSituacao() == 0) {
			cbSituacao.setValue("Concluido");
		}

		tfData.setText(osEdita.getData());

	}
	private void listarProduto() {

		listProduto = produtoDao.listProduto();

		for (Produto produto : listProduto) {

			observableProduto.add(produto);
			observableProdutoNome.add(produto.getNomeProduto());

		}
		
		
		cbProduto.setItems(observableProdutoNome);
		cbProduto.setUserData(observableProduto);
		

	}
	

	
	public void adicionarTable() {

		listProduto = produtoDao.listProduto();

	

		System.out.println("Vai entrar no for de produtos");
		
			
		Integer produtoIndex = cbProduto.getSelectionModel().getSelectedIndex();
		
		Double precoProduto = observableProduto.get(produtoIndex).getPrecoProduto();
		Integer idProduto = observableProduto.get(produtoIndex).getIdProduto();
		String idProdutoS = String.valueOf(idProduto);
		
			prodList.add(idProdutoS);
		
			CriarProdutoTabela criarProdutoTabela = new CriarProdutoTabela((String) cbProduto.getValue() ,
					precoProduto , Integer.parseInt(tfQuantidade.getText()) , idProduto);
			
			arrayProduto.add(criarProdutoTabela);
			quantidade.add(tfQuantidade.getText());
	
			observableProdutoTabela.addAll(arrayProduto);
	
			countTotal =countTotal + precoProduto * Integer.parseInt(tfQuantidade.getText());
			lblTotal.setText(String.valueOf(countTotal));

		System.out.println("Saiu do for de OS");
		
		tcProduto.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, String>("nomeProduto"));
		tcQuantidade.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, Integer>("qtdProduto"));
		tcValor.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, Double>("valorProduto"));
		tcId.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, Integer>("idProduto"));
		

		tvTabelaProduto.setItems(observableProdutoTabela);

	}

	public void deleteSelected(){
//		int selectedIndex = tvTabelaProduto.getSelectionModel().getSelectedIndex();
//		if (selectedIndex > 0) {
//	        tvTabelaProduto.getItems().remove(selectedIndex);
//		}
		
		for(int i=0;i<=indexToSave;i++){
			
			System.out.println(tcId.getCellData(i));
			
		}

	}
		

}
