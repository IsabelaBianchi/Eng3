package programa.valida;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import programa.entity.HibernateUtil;
import programa.entity.Usuario;

public class UsuarioValidaDao {

	public static boolean verificaUsuario(String usuario, String senha) {

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Usuario u = null;

		try {

			u = (Usuario) s.createCriteria(Usuario.class).add(Restrictions.eq("usuario", usuario))
					.add(Restrictions.eq("senha", senha)).uniqueResult();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}

		if (u == null) {
			return false;
		} else {
			return true;
		}

	}// Fim do metodo verificaUsuario

}// Fim da classe
