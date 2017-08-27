package programa.controller;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import programa.app.CadastraProdutoApp;
import programa.app.PrincipalApp;
import programa.dao.MateriaPrimaDao;
import programa.dao.ProdutoDao;
import programa.entity.Cliente;
import programa.entity.MateriaPrima;
import programa.entity.Produto;
import programa.tabela.CriarProdutoTabela;
import programa.tabela.MateriaPrimaTabela;
import programa.valida.ValidaCampo;

public class CadastrarProdutoController implements Initializable{


	
	@FXML
	private TextField tfNomeProduto;
	@FXML
	private TextField tfPrecoProduto;
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnAdicionar;
	@FXML
	private Label lblNomeProduto;
	@FXML
	private Label lblPrecoProduto;
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbMateria = new ChoiceBox();
	@FXML
	private TableView<MateriaPrimaTabela> tvMateria = new TableView<MateriaPrimaTabela>();
	@FXML
	private TableColumn<MateriaPrimaTabela, String> tcMateriaPrima = new TableColumn<MateriaPrimaTabela, String>();
	
	
	ProdutoDao prodDaoValida = new ProdutoDao();
	MateriaPrimaDao materiaPrimaDao = new MateriaPrimaDao();
	
	private ObservableList<String> observableMateria = FXCollections.observableArrayList();
	private ObservableList<MateriaPrimaTabela> observableMateriaPrima = FXCollections.observableArrayList();
	private ObservableList<MateriaPrima> observableMateriaPrima2 = FXCollections.observableArrayList();
	ArrayList< String > materiaList = new ArrayList< String >();
	ArrayList<MateriaPrimaTabela> arrayMateria = new ArrayList< MateriaPrimaTabela >();
	private List<MateriaPrima> listMateriaPrima;
	
	public void salvar(ActionEvent event) throws ParseException {
			
		
		
			if (ValidaCampo.isEmpty(tfNomeProduto.getText())) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Nome do produto nao pode ser em branco");
				a.showAndWait();
			}else if(prodDaoValida.verificarProdutoIgual(tfNomeProduto.getText())){
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Nome do produto já cadastrado");
				a.showAndWait();
			}
			else {
	
				Produto c = new Produto();
				ProdutoDao cDao = new ProdutoDao();
	
				c.setNomeProduto(tfNomeProduto.getText());
				c.setPrecoProduto(Double.parseDouble(tfPrecoProduto.getText()));
				c.setMateriaPrima(materiaList.toString());
				
				
				cDao.addProduto(c);
	
				Alert a = new Alert(AlertType.CONFIRMATION);
				a.setContentText("Produto " + tfNomeProduto.getText() + " cadastrado com sucesso");
				a.showAndWait();
				//new PrincipalApp().start(new Stage());
				CadastraProdutoApp.getStage().close();
				
			}
	
	}
	
	
	
	public void cancelar(ActionEvent event) {

		new PrincipalApp().start(new Stage());
		CadastraProdutoApp.getStage().close();

	}
	@SuppressWarnings("unchecked")
	private void listarMateria() {

		listMateriaPrima = materiaPrimaDao.listMateriaPrima();

		for (MateriaPrima materiaPrima : listMateriaPrima) {

			observableMateria.add(materiaPrima.getNome());
			observableMateriaPrima2.add(materiaPrima);

		}
		System.out.println(observableMateria);
		cbMateria.setItems(observableMateria);
		//cbMateria.setValue(observableMateria);
	}
	public void adicionar() {
		
		if (!observableMateriaPrima.isEmpty()) {
			observableMateriaPrima.clear();
		}
			
		
		String nome = materiaPrimaDao.getMateriaPrima((String) cbMateria.getValue()).getNome();
	
		Integer idMateria =  materiaPrimaDao.getMateriaPrima((String) cbMateria.getValue()).getIdMateria();
		
		String idMat = idMateria.toString();
		materiaList.add(idMat);
		
			MateriaPrimaTabela materiaPrimaTabela = new MateriaPrimaTabela(nome,idMateria);
			
			arrayMateria.add(materiaPrimaTabela);
	
			observableMateriaPrima.addAll(arrayMateria);
				
		tcMateriaPrima.setCellValueFactory((new PropertyValueFactory<MateriaPrimaTabela, String>("nome")));
		
		

		tvMateria.setItems(observableMateriaPrima);

	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		listarMateria();
		
	}
}
