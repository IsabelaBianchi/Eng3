package programa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import programa.entity.Fornecedor;
import programa.entity.HibernateUtil;

public class FornecedorDao {
	
	public void addFornecedor(Fornecedor fornecedor) {

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(fornecedor);
		s.getTransaction().commit();
		s.close();
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listFornecedor() {
		List<Fornecedor> list = new ArrayList<Fornecedor>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		list = s.createQuery("from Fornecedor").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}
	
	public List<Fornecedor> buscarFornecedor(String tfBuscaFornec){
		System.out.println("-----");
		Fornecedor fornecedorEncontrado = null;
		List<Fornecedor> list = new ArrayList<Fornecedor>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		list = s.createQuery("from Fornecedor where nomeFornecedor like '%"+tfBuscaFornec+"%'").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}  

	public void removeFornecedor(Integer id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Fornecedor c = (Fornecedor) s.load(Fornecedor.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	public void updateFornecedor(Fornecedor fornecedor) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(fornecedor);
		s.getTransaction().commit();
		s.close();
	}

	public Fornecedor getFornecedor(String fornecedor) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Fornecedor c = null;

		try {

			c = (Fornecedor) s.createCriteria(Fornecedor.class).add(Restrictions.eq("nomeFornecedor",fornecedor)).uniqueResult();

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

	
	public Fornecedor getFornecedor(int idFornecedor) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Fornecedor c = null;

		try {

			c = (Fornecedor) s.createCriteria(Fornecedor.class).add(Restrictions.eq("idFornecedor",idFornecedor)).uniqueResult();

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
