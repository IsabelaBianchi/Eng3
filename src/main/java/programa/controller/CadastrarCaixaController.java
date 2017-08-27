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
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import programa.app.CadastraCaixaApp;
import programa.app.CadastraFornecedorApp;
import programa.app.PrincipalApp;
import programa.dao.CaixaDao;
import programa.dao.FornecedorDao;
import programa.entity.Caixa;
import programa.entity.Fornecedor;
import programa.util.FormataData;
import programa.valida.ValidaCampo;
public class CadastrarCaixaController implements Initializable {

	@FXML
	private TextField tfDescricao = new TextField();
	@FXML
	private TextField tfValor = new TextField();
	@FXML
	private TextField tfData = new TextField();
	@FXML
	private Button btnSalvar = new Button();
	@FXML
	private Button btnCancelar = new Button();
	@FXML
	private RadioButton radEnt = new RadioButton();
	@FXML
	private RadioButton radSai = new RadioButton();

	
	public void salvar(ActionEvent event) throws ParseException {
		
		if ((ValidaCampo.isEmpty(tfDescricao.getText())) 
				|| (ValidaCampo.isEmpty(tfValor.getText())
				|| (!(radSai.isSelected() || radEnt.isSelected())))){
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Descrição,valor ou tipo não podem ser em branco");
			a.showAndWait();
		}else {

			Caixa caixa = new Caixa();
			CaixaDao caixaDao = new CaixaDao();

			caixa.setDescricao(tfDescricao.getText());
			caixa.setValor(Double.parseDouble(tfValor.getText()));
			caixa.setDia(FormataData.retornaDia());
			caixa.setMes(FormataData.retornaMes());
			caixa.setAno(FormataData.retornaAno());
			if(radEnt.isSelected()){
				caixa.setIsEnt(1);
			}else{
				caixa.setIsEnt(2);
			}
			
			
			caixaDao.addCaixa(caixa);

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Transação adicionada");
			a.showAndWait();
			//new PrincipalApp().start(new Stage());
			CadastraCaixaApp.getStage().close();
		}

	}

	public void cancelar(ActionEvent event) {
	
		new PrincipalApp().start(new Stage());
		CadastraCaixaApp.getStage().close();
	
	}

	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tfData.setText(FormataData.retornaDia() + "/" + FormataData.retornaMes() + "/" + FormataData.retornaAno());
		
	}

}
