package programa.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import programa.app.EditaFornecedorApp;
import programa.app.EditaMateriaPrimaApp;
import programa.app.EditaProdutoApp;
import programa.app.GerenciaFornecedorApp;
import programa.app.GerenciaMateriaPrimaApp;
import programa.app.GerenciaProdutoApp;
import programa.dao.FornecedorDao;
import programa.dao.MateriaPrimaDao;
import programa.dao.ProdutoDao;
import programa.entity.Fornecedor;
import programa.entity.MateriaPrima;
import programa.entity.Produto;
import programa.tabela.CriarProdutoTabela;
import programa.tabela.MateriaPrimaTabela;
import programa.valida.ValidaCampo;

public class EditaProdutoController implements Initializable {

	@FXML
	private TextField tfNomeProduto = new TextField();
	@FXML
	private TextField tfPrecoProduto = new TextField();
	@SuppressWarnings("rawtypes")
	@FXML
	private TableView<MateriaPrimaTabela> tvMateria = new TableView<MateriaPrimaTabela>();
	@FXML
	private TableColumn<MateriaPrimaTabela, String> tcMateriaPrima = new TableColumn<MateriaPrimaTabela, String>();
	
	Produto produtoEdita = new Produto();
	ProdutoDao prodDaoValida = new ProdutoDao();
	MateriaPrimaDao materiaPrimaDao = new MateriaPrimaDao();
	
	private ObservableList<String> observableMateria = FXCollections.observableArrayList();
	private ObservableList<MateriaPrimaTabela> observableMateriaPrima = FXCollections.observableArrayList();
	private ObservableList<MateriaPrima> observableMateriaPrima2 = FXCollections.observableArrayList();
	ArrayList< String > materiaList = new ArrayList< String >();
	ArrayList<MateriaPrimaTabela> arrayMateria = new ArrayList< MateriaPrimaTabela >();
	private List<MateriaPrima> listMateriaPrima;

	
	

	public void salvar(ActionEvent event) {

		if ((ValidaCampo.isEmpty(tfNomeProduto.getText())) || (ValidaCampo.isEmpty(tfPrecoProduto.getText()))) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Nome do Produto ou Preço nao pode ser em branco");
			a.showAndWait();
		}
		else {
					
			
			produtoEdita.setNomeProduto((tfNomeProduto.getText()));
			
			produtoEdita.setPrecoProduto(Double.parseDouble(tfPrecoProduto.getText()));
			
			prodDaoValida.updateProduto(produtoEdita);

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Produto " + tfNomeProduto.getText() + " alterado com sucesso");
			a.showAndWait();
			//new GerenciaProdutoApp().start(new Stage());
			EditaProdutoApp.getStage().close();
		}

	}

	public void cancelar(ActionEvent event) {

		GerenciaProdutoApp.getStage().show();
		EditaProdutoApp.getStage().close();

	}

	
	public void pegaProduto() {

		produtoEdita = prodDaoValida.getProduto(GerenciaProdutoController.getCliente());

	}
	public void preencheTabela(){
		String prodsString = produtoEdita.getMateriaPrima();
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

		int idx = 0;
		
		for (char output : productsCharArray) {
			String prods = Character.toString(output);

			listMateriaPrima = materiaPrimaDao.buscarMateriaId(prods);
			
			
			for (MateriaPrima materiaPrima : listMateriaPrima) {
				
				MateriaPrimaTabela materiaPrimaTabela = new MateriaPrimaTabela(materiaPrima.getNome(),
						materiaPrima.getIdMateria());
				
				observableMateriaPrima.add(materiaPrimaTabela);
				
				tcMateriaPrima.setCellValueFactory(new PropertyValueFactory<MateriaPrimaTabela, String>("Nome"));
				
				tvMateria.setItems(observableMateriaPrima);
				
			}
		}
		
		
	}
		
	public void initialize(URL location, ResourceBundle resources) {

		pegaProduto();
		preencheTabela();
		tfNomeProduto.setText(produtoEdita.getNomeProduto());
		tfPrecoProduto.setText(Double.toString(produtoEdita.getPrecoProduto()));
		
		
		
	}

}
