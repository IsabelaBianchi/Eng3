package programa.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programa.app.CadastraClienteApp;
import programa.app.CadastraMateriaPrimaApp;
import programa.app.PrincipalApp;
import programa.dao.ClienteDao;
import programa.dao.FornecedorDao;
import programa.dao.MateriaPrimaDao;
import programa.entity.Cliente;
import programa.entity.Fornecedor;
import programa.entity.MateriaPrima;
import programa.entity.Usuario;
import programa.valida.ValidaCampo;
import programa.util.*;

public class CadastrarMateriaPrimaController implements Initializable {

	@FXML
	private TextField tfNome = new TextField();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox cbFornec = new ChoiceBox();
	
	private ObservableList<String> observableFornecedor = FXCollections.observableArrayList();
	private List<Fornecedor> listFornecedor;
	
	FornecedorDao fornecedorDao = new FornecedorDao();
	
	public void salvar(ActionEvent event) throws ParseException {
		
		if ((ValidaCampo.isEmpty(tfNome.getText())) || (ValidaCampo.isEmpty(cbFornec.getValue().toString()))) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Nome da Materia-Prima ou Fornecedor nao pode ser em branco");
			a.showAndWait();
		} else {

			Fornecedor c = new Fornecedor();
			FornecedorDao cDao = new FornecedorDao();
			MateriaPrimaDao mpDao = new MateriaPrimaDao();

			c = fornecedorDao.getFornecedor(cbFornec.getValue().toString());
			
			MateriaPrima mp = new MateriaPrima();
			mp.setNome(tfNome.getText());
			mp.setIdFornecedor(c.getIdFornecedor());
			
			mpDao.addMateriaPrima(mp);
			
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Materia-Prima " + tfNome.getText() + " cadastrada com sucesso");
			a.showAndWait();
			//new PrincipalApp().start(new Stage());
			CadastraMateriaPrimaApp.getStage().close();
		}

	}

	public void cancelar(ActionEvent event) {

		new PrincipalApp().start(new Stage());
		CadastraMateriaPrimaApp.getStage().close();

	}
	
	@SuppressWarnings("unchecked")
	private void listarFornecedor() {

		listFornecedor = fornecedorDao.listFornecedor();

		for (Fornecedor fornecedor : listFornecedor) {

			observableFornecedor.add(fornecedor.getNomeFornecedor());

		}

		cbFornec.setItems(observableFornecedor);
		cbFornec.setValue(observableFornecedor);

	}// Fim listar Cliente

	public void initialize(URL location, ResourceBundle resources) {
		listarFornecedor();
		
	}
	
}
