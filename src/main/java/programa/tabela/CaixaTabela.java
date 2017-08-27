package programa.tabela;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CaixaTabela {
	
	private SimpleIntegerProperty transId;
	private SimpleStringProperty descricao;
	private SimpleDoubleProperty valor;
	private SimpleIntegerProperty isEnt;
	private SimpleIntegerProperty dia;
	private SimpleIntegerProperty mes;
	private SimpleIntegerProperty ano;
	private SimpleStringProperty data;
	private SimpleStringProperty entrada;
	private SimpleStringProperty saida;
	
	

	public Integer getTransId() {
		return transId.getValue();
	}
	
	public String getDescricao() {
		return descricao.getValue();
	}
	
	public Double getValor(){
		return valor.getValue();
	}
	
	public Integer getIsEnt(){
		return isEnt.getValue();
	}
	
	public Integer getDia(){
		return dia.getValue();
	}
	public Integer getMes(){
		return mes.getValue();
	}
	public Integer getAno(){
		return ano.getValue();
	}
	public String getData(){
		return data.getValue();
	}
	public String getEntrada(){
		return entrada.getValue();
	}
	public String getSaida(){
		return saida.getValue();
	}
	public CaixaTabela() {

	}

	public CaixaTabela(int transId, String descricao, Double valor, int isEnt,int dia ,int mes, int ano) {
		this.transId = new SimpleIntegerProperty(transId);
		this.valor = new SimpleDoubleProperty(valor);
		this.descricao= new SimpleStringProperty(descricao);
		this.dia = new SimpleIntegerProperty(dia);
		this.mes = new SimpleIntegerProperty(mes);
		this.ano = new SimpleIntegerProperty(ano);
		this.isEnt = new SimpleIntegerProperty(isEnt);	
		if(isEnt == 1){
			this.entrada = new SimpleStringProperty("Entrada");
		}
		if(isEnt==2){
			this.entrada = new SimpleStringProperty("Saída");
		}
		this.data = new SimpleStringProperty(String.valueOf(dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(ano));

	}

}
