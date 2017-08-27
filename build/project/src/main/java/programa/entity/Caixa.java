package programa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Caixa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int transId;
	@Column(name = "descricao", nullable = false)
	private String descricao;
	@Column(name = "valor")
	private Double valor;
	@Column(name = "isEnt")
	private int isEnt;
	@Column(name = "dia")
	private int dia;
	@Column(name = "mes")
	private int mes;
	@Column(name = "ano")
	private int ano;
	
	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getIsEnt() {
		return isEnt;
	}

	public void setIsEnt(int isEnt) {
		this.isEnt = isEnt;
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

	public Caixa(){
		
	}
	
	public Caixa(Integer transId , String descricao , Double valor, Integer isEnt , Integer dia, Integer mes, Integer ano){
		this.transId = transId;
		this.descricao = descricao;
		this.valor = valor;
		this.isEnt = isEnt;
		this.dia=dia;
		this.mes=mes;
		this.ano=ano;
	}
	
}
