package programa.controller;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import programa.app.CadastraFornecedorApp;
import programa.app.PrincipalApp;
import programa.dao.FornecedorDao;
import programa.entity.Fornecedor;
import programa.valida.ValidaCampo;
public class CadastrarFornecedorController implements Initializable {

	@FXML
	private TextField tfNomeFornec = new TextField();
	@FXML
	private TextField tfFoneFornec = new TextField();
	@FXML
	private Button btnSalvar = new Button();
	@FXML
	private Button btnCancelar = new Button();
	
	
	
	public void salvar(ActionEvent event) throws ParseException {
		
		if (ValidaCampo.isEmpty(tfNomeFornec.getText())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Nome do fornecedor nao pode ser em branco");
			a.showAndWait();
		} else {

			Fornecedor g = new Fornecedor();
			FornecedorDao cDao = new FornecedorDao();

			g.setNomeFornecedor(tfNomeFornec.getText());
			g.setFoneFornecedor(tfFoneFornec.getText());
			
			System.out.println(g.getNomeFornecedor() + g.getFoneFornecedor());
			cDao.addFornecedor(g);

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Fornecedor " + tfNomeFornec.getText() + " cadastrado com sucesso");
			a.showAndWait();
		//	new PrincipalApp().start(new Stage());
			CadastraFornecedorApp.getStage().close();
		}

	}

	public void cancelar(ActionEvent event) {
	
		new PrincipalApp().start(new Stage());
		CadastraFornecedorApp.getStage().close();
	
	}

	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
