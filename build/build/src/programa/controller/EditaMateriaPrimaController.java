package programa.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import programa.app.EditaFornecedorApp;
import programa.app.EditaMateriaPrimaApp;
import programa.app.GerenciaFornecedorApp;
import programa.app.GerenciaMateriaPrimaApp;
import programa.dao.FornecedorDao;
import programa.dao.MateriaPrimaDao;
import programa.entity.Fornecedor;
import programa.entity.MateriaPrima;
import programa.valida.ValidaCampo;

public class EditaMateriaPrimaController implements Initializable {

	@FXML
	private TextField tfNome = new TextField();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbFornec = new ChoiceBox();
	
	private ObservableList<String> observableFornecedor = FXCollections.observableArrayList();
	private List<Fornecedor> listFornecedor;
	
	Fornecedor c = new Fornecedor();
	FornecedorDao cDao = new FornecedorDao();
	MateriaPrimaDao mpDao = new MateriaPrimaDao();
	MateriaPrima materiaPrimaEdita = new MateriaPrima();

	

	public void salvar(ActionEvent event) {

		if ((ValidaCampo.isEmpty(tfNome.getText())) || (ValidaCampo.isEmpty(cbFornec.getValue().toString()))) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Nome da Materia-Prima ou Fornecedor nao pode ser em branco");
			a.showAndWait();
		}
		else {
			c = cDao.getFornecedor(cbFornec.getValue().toString());
			materiaPrimaEdita.setNome(tfNome.getText());
			materiaPrimaEdita.setIdFornecedor(c.getIdFornecedor());
			
			mpDao.updateMateriaPrima(materiaPrimaEdita);

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Materia-Prima " + tfNome.getText() + " alterada com sucesso");
			a.showAndWait();
			new GerenciaMateriaPrimaApp().start(new Stage());
			EditaMateriaPrimaApp.getStage().close();
		}

	}

	public void cancelar(ActionEvent event) {

		GerenciaMateriaPrimaApp.getStage().show();
		EditaMateriaPrimaApp.getStage().close();

	}

	
	public void pegaMateria() {

		materiaPrimaEdita = mpDao.getMateriaPrima(GerenciaMateriaPrimaController.getCliente());

	}
	
	@SuppressWarnings("unchecked")
	private void listarFornecedor() {

		listFornecedor = cDao.listFornecedor();

		for (Fornecedor fornecedor : listFornecedor) {

			observableFornecedor.add(fornecedor.getNomeFornecedor());

		}

		cbFornec.setItems(observableFornecedor);
		cbFornec.setValue(observableFornecedor);

	}// Fim listar Cliente
	
	public void initialize(URL location, ResourceBundle resources) {

		pegaMateria();

		tfNome.setText(materiaPrimaEdita.getNome());
		listarFornecedor();
	}

}
