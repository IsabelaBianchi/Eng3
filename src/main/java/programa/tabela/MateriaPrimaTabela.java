package programa.tabela;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MateriaPrimaTabela {
	
	
	private SimpleStringProperty nome;	
	private SimpleIntegerProperty idMateria;
	

	public String getNome() {
		return nome.getValue();
	}
	
	public Integer getIdMateria(){
		return idMateria.getValue();
	}
	
	public MateriaPrimaTabela(String nome , Integer idMateria){
		this.nome = new SimpleStringProperty(nome);
		this.idMateria = new SimpleIntegerProperty(idMateria);
	}

}
