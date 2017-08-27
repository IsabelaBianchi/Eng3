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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import programa.app.NovaOsApp;
import programa.app.PrincipalApp;
import programa.dao.CaixaDao;
import programa.dao.ClienteDao;
import programa.dao.OsDao;
import programa.dao.ProdutoDao;
import programa.entity.Caixa;
import programa.entity.Cliente;
import programa.entity.Os;
import programa.entity.Produto;
import programa.tabela.CriarProdutoTabela;
import programa.tabela.OsTabela;
import programa.util.FormataData;
import programa.valida.ValidaCampo;

public class NovaOsController implements Initializable {
	ClienteDao clienteDao = new ClienteDao();
	ProdutoDao produtoDao = new ProdutoDao();
	OsDao osDao = new OsDao();

	private List<Cliente> listCliente;
	private List<Produto> listProduto;

	private ObservableList<String> observableCliente = FXCollections.observableArrayList();
	private ObservableList<Produto> observableProduto = FXCollections.observableArrayList();
	private ObservableList<String> observableProdutoNome = FXCollections.observableArrayList();
	private ObservableList<String> observableSituacao = FXCollections.observableArrayList("Aberto", "Concluido");
	ArrayList< String > prodList = new ArrayList< String >();
	ArrayList< String > quantidade = new ArrayList< String >();
	ArrayList< CriarProdutoTabela > arrayProduto = new ArrayList< CriarProdutoTabela >();
	private double countTotal=0;
	
	
	
	ObservableList<CriarProdutoTabela> observableProdutoTabela = FXCollections.observableArrayList();
	
	@FXML
	private TextArea taDescricao = new TextArea();
	@FXML
	private TextField tfData = new TextField();
	@FXML
	private TextField tfEnderecoEntrega = new TextField();
	@FXML
	private TextField tfQuantidade = new TextField();
	@FXML
	private TextField tfIdOs = new TextField();
	@FXML
	private Label lblTotal = new Label();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbNomeCliente = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbSituacao = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbProduto = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbDia = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbMes = new ChoiceBox();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbAno = new ChoiceBox();
	
	@FXML
	private TableView<CriarProdutoTabela> tvTabelaProduto = new TableView<CriarProdutoTabela>();
	@FXML
	private TableColumn<CriarProdutoTabela, String> tcProduto = new TableColumn<CriarProdutoTabela, String>();
	@FXML
	private TableColumn<CriarProdutoTabela, Integer> tcQuantidade = new TableColumn<CriarProdutoTabela, Integer>();
	@FXML
	private TableColumn<CriarProdutoTabela, String> tcValor = new TableColumn<CriarProdutoTabela, String>();
	@FXML
	private TableColumn<CriarProdutoTabela, Integer> tcId = new TableColumn<CriarProdutoTabela, Integer>();
	
	

	private ObservableList<String> observableDia = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
			"26", "27", "28", "29", "30", "31");
	private ObservableList<String> observableMes = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12");
	private ObservableList<String> observableAno = FXCollections.observableArrayList("2017", "2018", "2019",
			"2020", "2021", "2022");

	public void salvar(ActionEvent event) {

		if (ValidaCampo.comecaCom(cbNomeCliente.getValue().toString())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Selecionar o cliente para abrir a encomenda");
			a.setHeaderText("Cliente nao selecionado");
			a.showAndWait();
		} else if (cbSituacao.getValue().toString().equals("[Aberto, Concluido]")) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Selecionar a situação da encomenda");
			a.setHeaderText("Campo situaçao não selecionado");
			a.showAndWait();
		}else if(cbDia.getValue()=="" || cbMes.getValue()=="" || cbAno.getValue()==""){
				
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Data de Entrega não pode ser em branco");
				a.showAndWait();
			
			
		} else {
		
			double valor = 99;
			int situacao = 99;
			if (cbSituacao.getValue().toString().equals("Aberto")) {
				situacao = 1;
			} else if (cbSituacao.getValue().toString().equals("Concluido")) {
				situacao = 0;
			}
			

			Cliente c = new Cliente();
			Produto p = new Produto();
			Caixa caixa = new Caixa();
			CaixaDao caixaDao = new CaixaDao();
			c = clienteDao.getCliente(cbNomeCliente.getValue().toString());
			p = produtoDao.getProduto(cbProduto.getValue().toString());
			
//			Os o = new Os(c.getId(), tfAssunto.getText(), taDescricao.getText(), FormataData.retornaDia(),
//					FormataData.retornaMes(), FormataData.retornaAno(), valor, situacao,p.getIdProduto());
			
			String listProdStr = prodList.toString();
			Os o = new Os();
			o.setIdCliente(c.getId());
			o.setDescricao(taDescricao.getText());
			o.setDia(FormataData.retornaDia());
			o.setMes(FormataData.retornaMes());
			o.setAno(FormataData.retornaAno());
			o.setSituacao(situacao);
			o.setIdProduto(p.getIdProduto());
			o.setProdItems(listProdStr);
			o.setEndereco(tfEnderecoEntrega.getText());
			o.setQuantidade(quantidade.toString());
			o.setTotal(countTotal);
			o.setDiaEnt(Integer.parseInt(cbDia.getValue().toString()));
			o.setMesEnt(Integer.parseInt(cbMes.getValue().toString()));
			o.setAnoEnt(Integer.parseInt(cbAno.getValue().toString()));
			
			caixa.setDescricao("Encomenda Cliente Id: " + o.getIdCliente());
			caixa.setValor(countTotal);
			caixa.setDia(FormataData.retornaDia());
			caixa.setMes(FormataData.retornaMes());
			caixa.setAno(FormataData.retornaAno());
			caixa.setIsEnt(1);
		
			caixaDao.addCaixa(caixa);
		
			osDao.addOs(o);

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Os inserida com sucesso no sistema");
			a.setHeaderText("Sucesso");
			a.setTitle("Os inserida");
	
			a.showAndWait();
			//new PrincipalApp().start(new Stage());
			NovaOsApp.getStage().close();

		}

	}

	public void cancelar(ActionEvent event) {
		//new PrincipalApp().start(new Stage());
		NovaOsApp.getStage().close();

	}

	@SuppressWarnings("unchecked")
	private void listarCliente() {

		listCliente = clienteDao.listCliente();

		for (Cliente cliente : listCliente) {

			observableCliente.add(cliente.getNomeCliente());

		}

		cbNomeCliente.setItems(observableCliente);
		cbNomeCliente.setValue(observableCliente);
		cbSituacao.setItems(observableSituacao);
		cbSituacao.setValue(observableSituacao);

	}// Fim listar Cliente
	
	
	@SuppressWarnings("unchecked")
	private void listarProduto() {

		listProduto = produtoDao.listProduto();

		for (Produto produto : listProduto) {

			observableProduto.add(produto);
			observableProdutoNome.add(produto.getNomeProduto());

		}
		
		
		cbProduto.setItems(observableProdutoNome);
		//cbProduto.setUserData(observableProduto);
		

	}// Fim listar Produto
	
	Produto criarProd = new Produto();
	

	
	
	public void adicionarTable() {
		if(ValidaCampo.isEmpty(tfQuantidade.getText())){
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Quantidade não pode ser em branco");
			a.setHeaderText("Atenção");
			a.showAndWait();

		}else{
		
		listProduto = produtoDao.listProduto();

		if (!observableProdutoTabela.isEmpty()) {
			observableProdutoTabela.clear();
		}

		System.out.println("Vai entrar no for de produtos");
			
		Integer produtoIndex = cbProduto.getSelectionModel().getSelectedIndex();
		
	//	Double precoProduto = observableProduto.get(produtoIndex).getPrecoProduto();
	//	Integer idProduto = observableProduto.get(produtoIndex).getIdProduto();
	//	String idProdutoS = String.valueOf(idProduto);
		
		String nomeProduto = produtoDao.getProduto((String) cbProduto.getValue()).getNomeProduto();
		Double precoProduto = produtoDao.getProduto((String) cbProduto.getValue()).getPrecoProduto();
		Integer idProduto =  produtoDao.getProduto((String) cbProduto.getValue()).getIdProduto();
		String idProdutoS = String.valueOf(idProduto);
		
		prodList.add(idProdutoS);
		
			CriarProdutoTabela criarProdutoTabela = new CriarProdutoTabela(nomeProduto ,
					precoProduto , Integer.parseInt(tfQuantidade.getText()), idProduto);
			
			arrayProduto.add(criarProdutoTabela);
			quantidade.add(tfQuantidade.getText());
	
			observableProdutoTabela.addAll(arrayProduto);
	
			countTotal =countTotal + precoProduto * Integer.parseInt(tfQuantidade.getText());
			
			DecimalFormat df = new DecimalFormat("0.00");
			String valorFormat = (df.format(countTotal));
			
			lblTotal.setText(String.valueOf("R$ "+valorFormat));

		System.out.println("Saiu do for de OS");
			
		tcProduto.setCellValueFactory((new PropertyValueFactory<CriarProdutoTabela, String>("nomeProduto")));
		tcQuantidade.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, Integer>("qtdProduto"));
		tcValor.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, String>("valorProdutoString"));
		tcId.setCellValueFactory(new PropertyValueFactory<CriarProdutoTabela, Integer>("idProduto"));
		

		tvTabelaProduto.setItems(observableProdutoTabela);
	  }
	}

	public void initialize(URL location, ResourceBundle resources) {
		cbMes.setItems(observableMes);
		cbMes.setValue("");
		cbAno.setItems(observableAno);
		cbAno.setValue("");
		cbDia.setItems(observableDia);
		cbDia.setValue("");
		tfData.setText(FormataData.retornaDia() + "/" + FormataData.retornaMes() + "/" + FormataData.retornaAno());
		tfData.setEditable(false);
		tfIdOs.setEditable(false);
		lblTotal.setText("R$ 0,00");
		listarCliente();
		listarProduto();
	}

}
