package programa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int idFornecedor;
	@Column(name = "nomeFornecedor")
	private String nomeFornecedor;
	@Column(name = "foneFornecedor")
	private String foneFornecedor;

	public int getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public String getFoneFornecedor() {
		return foneFornecedor;
	}
	public void setFoneFornecedor(String foneFornecedor) {
		this.foneFornecedor = foneFornecedor;
	}


	public Fornecedor(){
		
	}
	
	public Fornecedor(String nomeFornecedor , String foneFornecedor){
		this.nomeFornecedor = nomeFornecedor;
		this.foneFornecedor = foneFornecedor;
	}
	
}
