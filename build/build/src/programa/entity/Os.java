package programa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Os implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Id
	private int idOs;
	@Column(name = "idCliente")
	private int idCliente;
	@Column(name = "assunto")
	private String assunto;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "dia")
	private int dia;
	@Column(name = "mes")
	private int mes;
	@Column(name = "ano")
	private int ano;
	@Column(name = "valor")
	private double valor;
	@Column(name = "situacao")
	private int situacao;
	@Column(name = "idProduto")
	private int idProduto;
	@Column (name = "prodItems")
	private String prodItems;
	@Column(name="endereco")
	private String endereco;
	@Column(name="quantidade")
	private String quantidade;
	@Column(name = "total")
	private Double total;
	@Column(name = "diaEnt")
	private int diaEnt;
	@Column(name = "mesEnt")
	private int mesEnt;
	@Column(name = "anoEnt")
	private int anoEnt;
	
	public int getIdOs() {
		return idOs;
	}
	public void setIdOs(int idOs) {
		this.idOs = idOs;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public String getData(){
		return this.dia+"/"+this.mes+"/"+this.ano;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getSituacao() {
		return situacao;
	}
	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
		
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	
	
	public String getProdItems() {
		return prodItems;
	}
	public void setProdItems(String prodItems) {
		this.prodItems = prodItems;
	}
		
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public int getDiaEnt() {
		return diaEnt;
	}
	public void setDiaEnt(int diaEnt) {
		this.diaEnt = diaEnt;
	}
	public int getMesEnt() {
		return mesEnt;
	}
	public void setMesEnt(int mesEnt) {
		this.mesEnt = mesEnt;
	}
	public int getAnoEnt() {
		return anoEnt;
	}
	public void setAnoEnt(int anoEnt) {
		this.anoEnt = anoEnt;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		
		result = prime * result + idCliente;
		result = prime * result + idProduto;
		result = prime * result + idOs;
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	
	
	public Os(){
		
		
	}
	
	public Os(int idCliente, String assunto, String descricao, int dia, int mes, int ano, double valor, int situacao, int idProduto, 
			String prodItems,String endereco,String quantidade,Double total, int diaEnt , int mesEnt, int anoEnt) {
		super();
		this.idCliente = idCliente;
		this.assunto = assunto;
		this.descricao = descricao;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.valor = valor;
		this.situacao = situacao;
		this.idProduto = idProduto;
		this.prodItems = prodItems;
		this.endereco = endereco;
		this.quantidade = quantidade;
		this.total = total;
		this.diaEnt = diaEnt;
		this.mesEnt = mesEnt;
		this.anoEnt = anoEnt;
	}
	
	
	
	
	
	
}
