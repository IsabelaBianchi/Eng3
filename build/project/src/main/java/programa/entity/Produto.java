package programa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int idProduto;
	@Column(name = "nomeProduto" ,nullable = false)
	private String nomeProduto;
	@Column(name="precoProduto")
	private double precoProduto;
	@Column(name = "materiaPrima")
	private String materiaPrima;
	
	
	
	
	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}	


	public double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	
	public String getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(String materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + idProduto;
		result = prime * result + ((nomeProduto == null) ? 0 : nomeProduto.hashCode());
		
		
		long preco;
		preco = Double.doubleToLongBits(precoProduto);
		result = prime * result + (int) (preco ^ (preco >>> 32));
		return result;
	}
	
	public Produto(){
		
	}
	
	public Produto(String nomeProduto , double precoProduto, String materiaPrima){
		this.nomeProduto = nomeProduto;
		this.precoProduto = precoProduto;
		this.materiaPrima = materiaPrima;
	}

}