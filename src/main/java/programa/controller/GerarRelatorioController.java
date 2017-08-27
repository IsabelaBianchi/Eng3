package programa.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import programa.app.CadastraFornecedorApp;
import programa.app.CadastraFuncionarioApp;
import programa.app.GeraRelatorioApp;
import programa.app.PrincipalApp;
import programa.dao.CaixaDao;
import programa.dao.ClienteDao;
import programa.dao.FornecedorDao;
import programa.dao.FuncionarioDao;
import programa.dao.OsDao;
import programa.entity.Cliente;
import programa.entity.Fornecedor;
import programa.entity.Funcionario;
import programa.entity.Os;
import programa.entity.Produto;
import programa.valida.ValidaCampo;
public class GerarRelatorioController implements Initializable {

	OsDao osDao = new OsDao();
	List<String> listPrd = new ArrayList<String>();
	String prodsString;
	CaixaDao caixaDao = new CaixaDao();
	
	String resultadoPer;
	@FXML
	Button btnPeriodo = new Button();
	@FXML
	Button btnCliente = new Button();
	@FXML
	Button btnGerar = new Button();
	@FXML
	TextArea taTexto = new TextArea();
	@FXML
	TextField tfMes = new TextField();
	@FXML
	TextField tfAno = new TextField();
	public void cancelar(ActionEvent event) {
	
		new PrincipalApp().start(new Stage());
		GeraRelatorioApp.getStage().close();
	
	}
	
	public void clientes() throws IOException{
		List<Os> list = new ArrayList<Os>();
		list = osDao.clienteMais();
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = new Cliente();
		List<Os> listCli;
		listCli = osDao.listOs();
		String myText = "";

		for(Os os : listCli){
			int idCli = os.getIdCliente();
			cliente = clienteDao.getCliente(idCli);
			myText = myText + "Nome Cliente : "+cliente.getNomeCliente()+" Id:  "+cliente.getId()+ "\n";
			taTexto.setText(myText);
		}
		
	}
	public void gerar(){
		String textoQueSeraEscrito = taTexto.getText();  
        
        FileWriter arquivo;  
          
        try {  
            arquivo = new FileWriter(new File("C:/Users/Isa/Desktop/arquivos/Arquivo.txt"));  
            arquivo.write(textoQueSeraEscrito);  
            arquivo.close();  
            Alert a1 = new Alert(AlertType.INFORMATION);
			a1.setContentText("Texto gerado para impressão em desktop/arquivos/Arquivo.txt");
			a1.setHeaderText(null);
			a1.setTitle("Sucesso");
			a1.showAndWait();
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	public void periodo(){
		taTexto.clear();
		resultadoPer = caixaDao.periodo(Integer.parseInt(tfMes.getText()), Integer.parseInt(tfAno.getText()));
		taTexto.setText("Encomendas geradas para o mês "+tfMes.getText()+":  "+ resultadoPer);
	}

	public void relatProd(){
		System.out.println(osDao.prodMaisVendidos());
		prodsString = osDao.prodMaisVendidos().toString();
//		
//		if(prodsString.contains(",")){
//			prodsString=prodsString.replace("[", "");
//			prodsString=prodsString.replace("]", "");
//			prodsString=prodsString.replace(",", "");
//			prodsString=prodsString.replace(" ", "");
//		}else{
//			prodsString=prodsString.replace("[", "");
//			prodsString=prodsString.replace("]", "");
//		}
		
		
		String frase = "prodsString";
		String[] oito = frase.split("8");
		String[] nove = frase.split("9");
		String[] dez = frase.split("10");
		String[] onze = frase.split("11");
		String[] doze = frase.split("12");
		String[] treze = frase.split("13");
		String[] quatorze = frase.split("14");
		String[] quize = frase.split("15");
		String[] dezesseis = frase.split("16");
		String[] dezessete = frase.split("17");
		
		
		taTexto.setText("Produto Id 8 : "+ oito.length + "\n" +
						"Produto Id 9 : "+ nove.length + "\n" +
						"Produto Id 10 : "+ dez.length + "\n");
		
		
	
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
