package programa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MateriaPrima implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int idMateria;
	@Column(name = "nome")
	private String nome;
	@Column(name = "idFornecedor")
	private int idFornecedor;

	public int getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public MateriaPrima(){
		
	}
	
	public MateriaPrima(Integer idMateria , String nome ,Integer idFornecedor){
		this.idMateria = idMateria;
		this.nome = nome;
		this.idFornecedor = idFornecedor;
	}
	
}
