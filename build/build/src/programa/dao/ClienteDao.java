package programa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import programa.entity.Cliente;
import programa.entity.HibernateUtil;

public class ClienteDao {

	public void addUsuario(Cliente cliente) {

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(cliente);
		s.getTransaction().commit();
		s.close();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listCliente() {
		List<Cliente> list = new ArrayList<Cliente>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		list = s.createQuery("from Cliente").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}
	
	public List<Cliente> buscarCliente(String tfBuscaCliente){
		
		Cliente clienteEncontrado = null;
		List<Cliente> list = new ArrayList<Cliente>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		list = s.createQuery("from Cliente where nomeCliente like '%"+tfBuscaCliente+"%'").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}  

	public void removeCliente(Integer id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Cliente c = (Cliente) s.load(Cliente.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	public void updateCliente(Cliente cliente) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(cliente);
		s.getTransaction().commit();
		s.close();
	}

	public Cliente getCliente(String cliente) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Cliente c = null;

		try {

			c = (Cliente) s.createCriteria(Cliente.class).add(Restrictions.eq("nomeCliente",cliente)).uniqueResult();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}

		if (c == null) {
			System.out.println("Retornou null");
			return null;
		} else {

			System.out.println("Retornou o usuario");
			return c;
		}

	}

	
	public Cliente getCliente(int idCliente) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Cliente c = null;

		try {

			c = (Cliente) s.createCriteria(Cliente.class).add(Restrictions.eq("idCliente",idCliente)).uniqueResult();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}

		if (c == null) {
			System.out.println("Retornou null");
			return null;
		} else {

			System.out.println("Retornou o usuario");
			return c;
		}

	}
	
	
}
