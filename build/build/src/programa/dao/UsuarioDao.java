package programa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import programa.entity.HibernateUtil;
import programa.entity.Usuario;

public class UsuarioDao {

	public void addUsuario(Usuario usuario) {

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(usuario);
		s.getTransaction().commit();
		s.close();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listUsuario() {
		List<Usuario> list = new ArrayList<Usuario>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		list = s.createQuery("from Usuario").list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	public void removeUsuario(Integer id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Usuario c = (Usuario) s.load(Usuario.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	public void updateUsuario(Usuario usuario) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(usuario);
		s.getTransaction().commit();
		s.close();
	}

	public Usuario getUsuario(String usuario, String senha) {
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
			System.out.println("Retornou null");
			return null;
		} else {

			System.out.println("Retornou o usuario");
			return u;
		}

	}

}
