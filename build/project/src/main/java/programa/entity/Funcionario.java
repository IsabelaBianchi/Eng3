package programa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "telefone")
	private String telefone;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "endereco")
	private String endereco;
	@Column(name = "cargo")
	private String cargo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Funcionario(){
		
	}
	
	public Funcionario(Integer id, String nome , String telefone , String cpf, String endereco, String cargo){
		this.id = id;
		this.nome= nome;
		this.telefone = telefone;
		this.cpf = cpf;
		this.endereco = endereco;
		this.cargo = cargo;
		
	}
	
}
