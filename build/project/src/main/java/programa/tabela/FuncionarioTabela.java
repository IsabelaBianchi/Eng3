package programa.tabela;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FuncionarioTabela {
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty nome;
	private SimpleStringProperty telefone;
	private SimpleStringProperty cpf;
	private SimpleStringProperty endereco;
	private SimpleStringProperty cargo;
	
	
	
	public Integer getId() {
		return id.getValue();
	}
	
	public String getNome() {
		return nome.getValue();
	}
	
	public String getTelefone() {
		return telefone.getValue();
	}
	
	public String getCpf() {
		return cpf.getValue();
	}
	
	public String getEndereco() {
		return endereco.getValue();
	}
	
	public String getCargo() {
		return cargo.getValue();
	}
	
	
	public FuncionarioTabela(Integer id, String nome, String telefone ,  String cpf, String endereco , String cargo){
		this.id = new SimpleIntegerProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.telefone = new SimpleStringProperty(telefone);
		this.cpf = new SimpleStringProperty(cpf);
		this.endereco = new SimpleStringProperty(endereco);
		this.cargo = new SimpleStringProperty(cargo);
	}

}
