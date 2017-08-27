package programa.tabela;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OsTabela {

	private SimpleStringProperty nomeCliente;
	private SimpleStringProperty assunto;
	private SimpleStringProperty descricao;
	private SimpleIntegerProperty idOs;
	private SimpleIntegerProperty idCliente;
	private SimpleIntegerProperty dia;
	private SimpleIntegerProperty mes;
	private SimpleIntegerProperty ano;
	private SimpleStringProperty data;
	private SimpleIntegerProperty situacao;
	private SimpleDoubleProperty valor;
	private SimpleStringProperty situacaoString;
	private SimpleIntegerProperty diaEnt;
	private SimpleIntegerProperty mesEnt;
	private SimpleIntegerProperty anoEnt;
	private SimpleStringProperty dataEnt;
	private SimpleStringProperty valorStirng;
	
	public String getAssunto() {
		return assunto.getValue();
	}

	public String getDescricao() {
		return descricao.getValue();
	}

	public Integer getIdOs() {
		return idOs.getValue();
	}

	public Integer getIdCliente() {
		return idCliente.getValue();
	}

	public Integer getDia() {
		return dia.getValue();
	}

	public Integer getMes() {
		return mes.getValue();
	}

	public Integer getAno() {
		return ano.getValue();
	}

	public Integer getSituacao() {
		return situacao.getValue();
	}

	public Double getValor() {
		return valor.getValue();
	}

	public String getNomeCliente() {
		return nomeCliente.getValue();
	}
	
	public String getSituacaoString() {
		return situacaoString.getValue();
	}
	
	public Integer getDiaEnt(){
		return diaEnt.getValue();
	}
	
	public Integer getMesEnt(){
		return mesEnt.getValue();
	}

	public Integer getAnoEnt(){
		return anoEnt.getValue();
	}
	public String getDataEnt(){
		return dataEnt.getValue();
	}
	public String getData(){
		return data.getValue();
	}
	public String getValorString(){
		return valorStirng.getValue();
	}
	
	
	
	public OsTabela() {

	}

	public OsTabela(int idOs, int idCliente, String assunto, String descricao, int dia, int mes, int ano, int situacao,
			double valor, String nomeCliente , int diaEnt, int mesEnt , int anoEnt) {

		this.idOs = new SimpleIntegerProperty(idOs);
		this.idCliente = new SimpleIntegerProperty(idCliente);
		this.data = new SimpleStringProperty(String.valueOf(dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(ano));
		this.situacao = new SimpleIntegerProperty(situacao);
		this.valor = new SimpleDoubleProperty(valor);
		this.assunto = new SimpleStringProperty(assunto);
		this.descricao = new SimpleStringProperty(descricao);
		this.dataEnt = new SimpleStringProperty(String.valueOf(diaEnt)+"/"+String.valueOf(mesEnt)+"/"+String.valueOf(anoEnt));
		// teste
		this.nomeCliente = new SimpleStringProperty(nomeCliente);
		if(situacao == 0){
			this.situacaoString = new SimpleStringProperty("Concluido");
		}else{
			this.situacaoString = new SimpleStringProperty("Aberto");
		}
		String valorFormat = String.format("%.2f", valor);
		this.valorStirng = new SimpleStringProperty("R$ "+ String.valueOf(valorFormat));

	}

}
