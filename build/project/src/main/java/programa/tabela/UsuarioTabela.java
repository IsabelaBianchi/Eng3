package programa.tabela;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UsuarioTabela {

	private SimpleIntegerProperty id;
	private SimpleStringProperty nome;
	private SimpleStringProperty senha;
	private SimpleStringProperty email;
	private SimpleStringProperty usuario;
	private SimpleIntegerProperty permissao;
	
	public Integer getId() {
		return id.getValue();
	}
	public String getNome() {
		return nome.getValue();
	}
	public String getSenha() {
		return senha.getValue();
	}
	public String getEmail() {
		return email.getValue();
	}
	public String getUsuario() {
		return usuario.getValue();
	}
	public Integer getPermissao() {
		return permissao.getValue();
	}
	
	
	public UsuarioTabela(int id, String nome, String senha, String email, String usuario, int permissao){
		
		
		this.id = new SimpleIntegerProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.senha = new SimpleStringProperty(senha);
		this.email = new SimpleStringProperty(email);
		this.usuario = new SimpleStringProperty(usuario);
		this.permissao = new SimpleIntegerProperty(permissao);
		
		
	}
	
	
}


