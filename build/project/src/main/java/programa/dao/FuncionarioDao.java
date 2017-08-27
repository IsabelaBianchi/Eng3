package programa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import programa.entity.Fornecedor;
import programa.entity.Funcionario;
import programa.entity.HibernateUtil;

public class FuncionarioDao {
	
	public void addFuncionario(Funcionario funcionario) {

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(funcionario);
		s.getTransaction().commit();
		s.close();
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listFuncionario() {
		List<Funcionario> list = new ArrayList<Funcionario>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		list = s.createQuery("from Funcionario").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}
	
	public List<Funcionario> buscarFuncionario(String tfBuscaFunc){
		System.out.println("-----");
		Funcionario funcionarioEncontrado = null;
		List<Funcionario> list = new ArrayList<Funcionario>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		list = s.createQuery("from Funcionario where nome like '%"+tfBuscaFunc+"%'").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}  

	public void removeFuncionario(Integer id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Funcionario c = (Funcionario) s.load(Funcionario.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	public void updateFuncionario(Funcionario funcionario) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(funcionario);
		s.getTransaction().commit();
		s.close();
	}

	public Funcionario getFuncionario(String funcionario) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Funcionario c = null;

		try {

			c = (Funcionario) s.createCriteria(Funcionario.class).add(Restrictions.eq("nome",funcionario)).uniqueResult();

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

	
	public Funcionario getFuncionario(int idFuncionario) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Funcionario c = null;

		try {

			c = (Funcionario) s.createCriteria(Funcionario.class).add(Restrictions.eq("id",idFuncionario)).uniqueResult();

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
