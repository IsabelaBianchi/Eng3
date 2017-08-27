import java.security.NoSuchAlgorithmException;

import programa.dao.UsuarioDao;
import programa.entity.Usuario;
import programa.util.Md5Converte;

public class TestaUsuarioValida {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
			
		UsuarioDao uDao = new UsuarioDao();
		Usuario u ;
		u = uDao.getUsuario("isabela ", Md5Converte.converte("admin"));
		
		System.out.println("Imprimindo dados do usuario");
		System.out.println(u.getNome());
		System.out.println(u.getEmail());
		System.out.println(u.getUsuario());
		System.out.println(u.getSenha());
		System.out.println(u.getPermissao());
		
	}

}
