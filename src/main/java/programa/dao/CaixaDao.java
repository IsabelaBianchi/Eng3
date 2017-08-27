package programa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import programa.entity.Caixa;
import programa.entity.Fornecedor;
import programa.entity.HibernateUtil;
import programa.entity.Os;

public class CaixaDao {
	
	public void addCaixa(Caixa caixa) {

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(caixa);
		s.getTransaction().commit();
		s.close();
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listCaixa() {
		List<Fornecedor> list = new ArrayList<Fornecedor>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		list = s.createQuery("from Caixa").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}

	public String periodo(int mes,int ano){
		List<String> list = new ArrayList<String>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		//list = s.createQuery("SELECT " +mes+", COUNT("+mes+") AS qtd FROM Caixa WHERE ano=2017");
		String count = (String) (s.createQuery("select count(*) from Caixa where ano="+ano+" and mes="+mes+"").uniqueResult().toString());

		s.getTransaction().commit();
		s.close();
		return count;
	}
//	public List<Fornecedor> buscarCa(String tfBuscaFornec){
//		System.out.println("-----");
//		Fornecedor fornecedorEncontrado = null;
//		List<Fornecedor> list = new ArrayList<Fornecedor>();
//		
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		
//		s.beginTransaction();
//		list = s.createQuery("from Fornecedor where nomeFornecedor like '%"+tfBuscaFornec+"%'").list();
//		s.getTransaction().commit();
//		s.close();
//		return list;
//		
//	}  

//	public void removeFornecedor(Integer id) {
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		s.beginTransaction();
//		Fornecedor c = (Fornecedor) s.load(Fornecedor.class, id);
//		s.delete(c);
//		s.getTransaction().commit();
//		s.close();
//	}
//
//	public void updateFornecedor(Fornecedor fornecedor) {
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		s.beginTransaction();
//		s.update(fornecedor);
//		s.getTransaction().commit();
//		s.close();
//	}

//	public Caixa getCaixa(String caixa) {
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		s.beginTransaction();
//		Fornecedor c = null;
//
//		try {
//
//			c = (Fornecedor) s.createCriteria(Fornecedor.class).add(Restrictions.eq("nomeFornecedor",fornecedor)).uniqueResult();
//
//		} catch (Exception e) {
//
//			e.getMessage();
//
//		} finally {
//			s.close();
//
//		}
//
//		if (c == null) {
//			System.out.println("Retornou null");
//			return null;
//		} else {
//
//			System.out.println("Retornou o usuario");
//			return c;
//		}
//
//	}

	
	public Caixa getCaixa(int transId) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Caixa c = null;

		try {

			c = (Caixa) s.createCriteria(Caixa.class).add(Restrictions.eq("transId",transId)).uniqueResult();

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
	
	@SuppressWarnings("unchecked")
	public List<Caixa> getCaixa(int dia, int mes, int ano) {

		List<Caixa> list = new ArrayList<Caixa>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		try {

			list = s.createCriteria(Caixa.class).add(Restrictions.eq("dia", dia)).add(Restrictions.eq("mes", mes))
					.add(Restrictions.eq("ano", ano)).list();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}
		return list;

	}
	
	@SuppressWarnings("unchecked")
	public List<Caixa> getCaixa(int mes, int ano) {

		List<Caixa> list = new ArrayList<Caixa>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		try {

			list = s.createCriteria(Caixa.class).add(Restrictions.eq("mes", mes)).add(Restrictions.eq("ano", ano)).list();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}
		return list;

	}


}
