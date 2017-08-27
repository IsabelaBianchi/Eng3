package programa.tabela;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FornecedorTabela {
	
	private SimpleIntegerProperty idFornecedor;
	private SimpleStringProperty nomeFornecedor;
	private SimpleStringProperty telefone;
	
	
	public Integer getIdFornecedor() {
		return idFornecedor.getValue();
	}
	
	public String getNomeFornecedor() {
		return nomeFornecedor.getValue();
	}
	
	public String getTelefone() {
		return telefone.getValue();
	}
	
	
	public FornecedorTabela(Integer idFornecedor, String nomeFornecedor, String telefone){
		this.idFornecedor = new SimpleIntegerProperty(idFornecedor);
		this.nomeFornecedor = new SimpleStringProperty(nomeFornecedor);
		this.telefone = new SimpleStringProperty(telefone);
	}

}
