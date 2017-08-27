package programa.tabela;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CriarProdutoTabela {

	
	private SimpleStringProperty nomeProduto;
	private SimpleDoubleProperty valorProduto;
	private SimpleIntegerProperty qtdProduto;
	private SimpleIntegerProperty idProduto;
	private SimpleStringProperty valorProdutoString;

	public String getNomeProduto() {
		return nomeProduto.getValue();
	}
	public void setNomeProduto(SimpleStringProperty nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Double getValorProduto() {
		return valorProduto.getValue();
	}
	public void setValorProduto(SimpleDoubleProperty valorProduto) {
		this.valorProduto = valorProduto;
	}
	public Integer getQtdProduto() {
		return qtdProduto.getValue();
	}
	public void setQtdProduto(SimpleIntegerProperty qtdProduto) {
		this.qtdProduto = qtdProduto;
	}
	
	public Integer getIdProduto() {
		return idProduto.getValue();
	}
	public void setIdProduto(SimpleIntegerProperty idProduto) {
		this.idProduto = idProduto;
	}
	public String getValorProdutoString(){
		return valorProdutoString.getValue();
	}
	public CriarProdutoTabela(){
		
	}
	
	public CriarProdutoTabela(String nomeProduto, Double valorProduto , int qtdProduto , int idProduto){
		this.nomeProduto =  new SimpleStringProperty(nomeProduto);
		this.valorProduto = new SimpleDoubleProperty(valorProduto);
		this.qtdProduto = new SimpleIntegerProperty(qtdProduto);
		this.idProduto = new SimpleIntegerProperty(idProduto);
		String valor = String.format("%.2f", valorProduto);
		this.valorProdutoString = new SimpleStringProperty("R$ "+ String.valueOf(valor));
	}
	public CriarProdutoTabela(String nomeProduto, Double valorProduto , int idProduto){
		this.nomeProduto =  new SimpleStringProperty(nomeProduto);
		this.valorProduto = new SimpleDoubleProperty(valorProduto);
		this.idProduto = new SimpleIntegerProperty(idProduto);
		String valor = String.format("%.2f", valorProduto);
		this.valorProdutoString = new SimpleStringProperty("R$ "+ String.valueOf(valor));
	}
	
	
}
