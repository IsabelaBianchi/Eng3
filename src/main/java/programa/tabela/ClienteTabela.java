package programa.tabela;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ClienteTabela {

	private SimpleIntegerProperty idCliente;
	private SimpleStringProperty nomeCliente;
	private SimpleStringProperty sobrenome;
	private SimpleStringProperty telefone;
	private SimpleStringProperty celular;
	private SimpleStringProperty emailCliente;
	private SimpleStringProperty endereco;
	private SimpleStringProperty cidade;
	private SimpleStringProperty cpf;
	
	
	public Integer getIdCliente() {
		return idCliente.getValue();
	}
	
	public String getNomeCliente() {
		return nomeCliente.getValue();
	}
	
	public String getSobrenome() {
		return sobrenome.getValue();
	}
	
	public String getTelefone() {
		return telefone.getValue();
	}
	
	public String getCelular() {
		return celular.getValue();
	}
	
	public String getEmailCliente() {
		return emailCliente.getValue();
	}
	
	public String getEndereco() {
		return endereco.getValue();
	}
	
	public String getCidade() {
		return cidade.getValue();
	}
	
	public String getCpf(){
		return cpf.getValue();
	}

	public ClienteTabela(String nomeCliente, String sobrenome,
			String telefone, String celular, String emailCliente,
			String endereco, String cidade, String cpf) {
		super();
		this.nomeCliente =  new SimpleStringProperty(nomeCliente);
		this.sobrenome = new SimpleStringProperty(sobrenome);
		this.telefone = new SimpleStringProperty(telefone);
		this.celular = new SimpleStringProperty(celular);
		this.emailCliente = new SimpleStringProperty(emailCliente);
		this.endereco = new SimpleStringProperty(endereco);
		this.cidade = new SimpleStringProperty(cidade);
		this.cpf = new SimpleStringProperty(cpf);
	}
	
	public ClienteTabela(Integer id, String nomeCliente, String sobrenome,
			String telefone, String celular, String emailCliente,
			String endereco, String cidade, String cpf) {
		super();
		this.idCliente = new SimpleIntegerProperty(id);
		this.nomeCliente =  new SimpleStringProperty(nomeCliente);
		this.sobrenome = new SimpleStringProperty(sobrenome);
		this.telefone = new SimpleStringProperty(telefone);
		this.celular = new SimpleStringProperty(celular);
		this.emailCliente = new SimpleStringProperty(emailCliente);
		this.endereco = new SimpleStringProperty(endereco);
		this.cidade = new SimpleStringProperty(cidade);
		this.cpf = new SimpleStringProperty(cpf);
	}
	
	
	
	public ClienteTabela(Integer id, String nomeCliente){
		this.idCliente = new SimpleIntegerProperty(id);
		this.nomeCliente = new SimpleStringProperty(nomeCliente);
	}
	
	
	
	
}
