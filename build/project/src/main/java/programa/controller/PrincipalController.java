package programa.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import programa.app.CadastraCaixaApp;
import programa.app.CadastraClienteApp;
import programa.app.CadastraFornecedorApp;
import programa.app.CadastraFuncionarioApp;
import programa.app.CadastraMateriaPrimaApp;
import programa.app.CadastraProdutoApp;
import programa.app.CadastraUsuarioApp;
import programa.app.GeraRelatorioApp;
import programa.app.GerenciaClienteApp;
import programa.app.GerenciaFornecedorApp;
import programa.app.GerenciaFuncionarioApp;
import programa.app.GerenciaMateriaPrimaApp;
import programa.app.GerenciaProdutoApp;
import programa.app.GerenciaUsuarioApp;
import programa.app.InfoApp;
import programa.app.ListaCaixaApp;
import programa.app.ListaOsApp;
import programa.app.Main;
import programa.app.NovaOsApp;
import programa.app.PrincipalApp;
import programa.app.SenhaApp;
import programa.dao.ClienteDao;
import programa.dao.OsDao;
import programa.entity.Cliente;
import programa.entity.Os;
import programa.entity.Usuario;
import programa.tabela.ClienteTabela;
import programa.tabela.OsTabela;
import programa.util.FormataData;

public class PrincipalController implements Initializable {

	public void initialize(URL location, ResourceBundle resources) {

		logado = LoginController.getUsuarioLogado();

		lblUsuarioLogado.setText(logado.getUsuario());

		if (logado.getPermissao() == 2) {
			miCadastrarUsuario.setVisible(false);
		//	miGerenciarClientes.setVisible(false);
			miGerenciarUsuarios.setVisible(false);

		}

		listaOs();
		listOsDias();

	}// Fim so initializable

	// Teste
	Cliente cliente;
	ClienteDao clienteDao = new ClienteDao();
	List<Cliente> clienteList = new ArrayList<Cliente>();
	ObservableList<ClienteTabela> observableClienteTabela = FXCollections.observableArrayList();
	// Teste

	Os os;
	OsDao osDao = new OsDao();

	List<Os> listOs;

	ObservableList<OsTabela> observableOsTabela = FXCollections.observableArrayList();
	ObservableList<OsTabela> observableOsTabela2 = FXCollections.observableArrayList();

	private Usuario logado;

	@FXML
	private MenuBar mbMenu = new MenuBar();
	@FXML
	private ImageView ivSair = new ImageView();
	@FXML
	private ImageView ivBanner = new ImageView();
	@FXML
	private Pane pPainel = new Pane();
	@FXML
	private Label lblUsuarioLogado = new Label();
	@FXML
	private MenuItem miNovaOs = new MenuItem();
	@FXML
	private Label lblUsuario = new Label();
	@FXML
	private Label lblUltimo = new Label();
	@FXML
	private MenuItem miCadastrarUsuario = new MenuItem();
	@FXML
	private MenuItem miGerenciarUsuarios = new MenuItem();
	@FXML
	private MenuItem miGerenciarClientes = new MenuItem();
	@FXML
	private MenuItem miGerenciarProduto = new MenuItem();
	@FXML
	private MenuItem miCadastraFornecedor = new MenuItem();
	@FXML
	private MenuItem miGerenciarFornecedor = new MenuItem();
	@FXML
	private MenuItem miGerenciarFunc = new MenuItem();
	@FXML
	private MenuItem miGerenciaCaixa = new MenuItem();
	@FXML
	private MenuItem miCadastrarMp = new MenuItem();
	@FXML
	private MenuItem miGerenciarMp = new MenuItem();
	@FXML
	private MenuItem miNovaTrans = new MenuItem();
	@FXML
	private MenuItem miNovoFunc = new MenuItem();
	@FXML
	private MenuItem miGerarRelat = new MenuItem();
	
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
	private TableView<OsTabela> tvTabela2 = new TableView<OsTabela>();

	@FXML
	private TableColumn<OsTabela, Integer> tcId2 = new TableColumn<OsTabela, Integer>();
	@FXML
	private TableColumn<String, String> tcCliente2 = new TableColumn<String, String>();
	@FXML
	private TableColumn<OsTabela, String> tcDataEnt2 = new TableColumn<OsTabela, String>();
	@FXML
	private TableColumn<OsTabela, String> tcAssunto2 = new TableColumn<OsTabela, String>();
	@FXML
	private TableColumn<OsTabela, String> tcSituacao2 = new TableColumn<OsTabela, String>();
	@FXML
	private TableColumn<OsTabela, String> tcValor2 = new TableColumn<OsTabela, String>();
	@FXML
	private TableColumn<OsTabela, String> tcData2 = new TableColumn<OsTabela,String>();

	public void sair(MouseEvent event) throws IOException {

		new Main().start(new Stage());
		PrincipalApp.getStage().close();

	}

	public void cadastrarUsuario(ActionEvent event) {

		new CadastraUsuarioApp().start(new Stage());
		//PrincipalApp.getStage().close();

	}

	public void cadastrarCliente(ActionEvent event) {

		new CadastraClienteApp().start(new Stage());
		//PrincipalApp.getStage().close();

	}
	
	public void cadastrarMp(ActionEvent event) {

		new CadastraMateriaPrimaApp().start(new Stage());
		//PrincipalApp.getStage().close();

	}

	public void novaOs(ActionEvent event) {
		new NovaOsApp().start(new Stage());
		//PrincipalApp.getStage().close();
	}
	
	public void novaTrans(ActionEvent event) {
		new CadastraCaixaApp().start(new Stage());
		//PrincipalApp.getStage().close();
	}
	
	public void novoFunc(ActionEvent event) {
		new CadastraFuncionarioApp().start(new Stage());
		//PrincipalApp.getStage().close();
	}
	

	public void gerenciarCliente() {
		new GerenciaClienteApp().start(new Stage());
		//PrincipalApp.getStage().close();
	}

	public void gerenciarUsuario() {
		new GerenciaUsuarioApp().start(new Stage());
		//PrincipalApp.getStage().close();
	}
	
	public void gerenciarFornecedor() {
		new GerenciaFornecedorApp().start(new Stage());
		//PrincipalApp.getStage().close();
	}
	
	public void gerenciarMp() {
		new GerenciaMateriaPrimaApp().start(new Stage());
		//PrincipalApp.getStage().close();
	}
	
	public void gerenciarProduto() {
		new GerenciaProdutoApp().start(new Stage());
		//PrincipalApp.getStage().close();
	}
	public void gerenciaCaixa() {
		new ListaCaixaApp().start(new Stage());
		//PrincipalApp.getStage().close();

	}
	public void gerenciarFunc() {
		new GerenciaFuncionarioApp().start(new Stage());
		//PrincipalApp.getStage().close();

	}
	public void gerarRelat() {
		new GeraRelatorioApp().start(new Stage());
//		PrincipalApp.getStage().close();

	}

	public void listarOs() {
		new ListaOsApp().start(new Stage());
	//	PrincipalApp.getStage().close();

	}

	public void info() {
		new InfoApp().start(new Stage());
	}

	public void alterar() {
		new SenhaApp().start(new Stage());

	}
	public void cadastrarProduto(){
		new CadastraProdutoApp().start(new Stage());
		
	}

	public void listaOs() {
		System.out.println("Entrou no listOs");

		if (!observableOsTabela.isEmpty()) {
			observableOsTabela.clear();
		}
		
		listOs = osDao.lastOs();
		for (Os os : listOs) {

			OsTabela osTabela = new OsTabela(os.getIdOs(), os.getIdCliente(), os.getAssunto(), os.getDescricao(),
					os.getDia(), os.getMes(), os.getAno(), os.getSituacao(), os.getTotal(),
					clienteDao.getCliente(os.getIdCliente()).getNomeCliente(),os.getDiaEnt() , os.getMesEnt(), os.getAnoEnt());

			observableOsTabela.add(osTabela);

		}
		tcId.setCellValueFactory(new PropertyValueFactory<OsTabela, Integer>("idOs"));
		tcCliente.setCellValueFactory(new PropertyValueFactory<String, String>("nomeCliente"));
		tcAssunto.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("descricao"));
		tcData.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("data"));
		tcValor.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("valorString"));
		tcSituacao.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("situacaoString"));
		tcDataEnt.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("dataEnt"));
		tvTabela.setItems(observableOsTabela);
	}
	
	public void listOsDias(){
		if (!observableOsTabela2.isEmpty()) {
			observableOsTabela2.clear();
		}
		
		listOs = osDao.getOsEnt(FormataData.retornaDia(), FormataData.retornaMes(), FormataData.retornaAno());
		
		for (Os os : listOs) {

			OsTabela osTabela = new OsTabela(os.getIdOs(), os.getIdCliente(), os.getAssunto(), os.getDescricao(),
					os.getDia(), os.getMes(), os.getAno(), os.getSituacao(), os.getTotal(),
					clienteDao.getCliente(os.getIdCliente()).getNomeCliente(),os.getDiaEnt() , os.getMesEnt(), os.getAnoEnt());

			observableOsTabela2.add(osTabela);

		}
		tcId2.setCellValueFactory(new PropertyValueFactory<OsTabela, Integer>("idOs"));
		tcCliente2.setCellValueFactory(new PropertyValueFactory<String, String>("nomeCliente"));
		tcAssunto2.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("descricao"));
		tcData2.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("data"));
		tcValor2.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("valorString"));
		tcSituacao2.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("situacaoString"));
		tcDataEnt2.setCellValueFactory(new PropertyValueFactory<OsTabela, String>("dataEnt"));
		tvTabela2.setItems(observableOsTabela2);
		
	}
	
	public void cadastrarFornecedor() {
		new CadastraFornecedorApp().start(new Stage());
		
		//PrincipalApp.getStage().close();
	}
	

}
