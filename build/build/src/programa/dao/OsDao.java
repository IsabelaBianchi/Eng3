package programa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import programa.entity.Cliente;
import programa.entity.HibernateUtil;
import programa.entity.Os;
import programa.entity.Produto;

public class OsDao {

	public void addOs(Os os) {

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(os);
		s.getTransaction().commit();
		s.close();
	}

	@SuppressWarnings("unchecked")
	public List<Os> listOs() {

		List<Os> list = new ArrayList<Os>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		list = s.createQuery("from Os").list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	public void removeOs(Integer id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Os o = (Os) s.load(Os.class, id);
		s.delete(o);
		s.getTransaction().commit();
		s.close();
	}

	public void updateOs(Os os) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(os);
		s.getTransaction().commit();
		s.close();
	}

	public Os getOs(int id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Os o = null;

		try {

			o = (Os) s.createCriteria(Os.class).add(Restrictions.eq("idOs", id)).uniqueResult();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}

		if (o == null) {
			System.out.println("Nao encontrou a Os de idOs = "+id+" Retornou null");
			return null;
		} else {

			System.out.println("Retornou a os");
			return o;
		}

	}
	
	public List<String> prodMaisVendidos(){
		List<String> list = new ArrayList<String>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		list = s.createQuery("Select prodItems from Os").list();
		s.getTransaction().commit();
		s.close();
		
		return list;
	}
	
	public List<Os> clienteMais(){
		List<Os> list = new ArrayList<Os>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		list = s.createQuery("SELECT idCliente, COUNT(*) AS magnitude FROM Os GROUP BY idCliente ORDER BY magnitude DESC").setMaxResults(5).list();
		s.getTransaction().commit();
		s.close();
		
		return list;
	}
	
	public List<Os> lastOs(){
		List<Os> list = new ArrayList<Os>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		list = s.createQuery("from Os ORDER BY idCliente DESC").setMaxResults(5).list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Os> getOs(int mes, int ano) {

		List<Os> list = new ArrayList<Os>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		try {

			list = s.createCriteria(Os.class).add(Restrictions.eq("mes", mes)).add(Restrictions.eq("ano", ano)).list();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}
		return list;

	}
	@SuppressWarnings("unchecked")
	public List<Os> getOsEnt(int mes, int ano) {

		List<Os> list = new ArrayList<Os>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		try {

			list = s.createCriteria(Os.class).add(Restrictions.eq("mesEnt", mes)).add(Restrictions.eq("anoEnt", ano)).list();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<Os> getOs(int dia, int mes, int ano) {

		List<Os> list = new ArrayList<Os>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		try {

			list = s.createCriteria(Os.class).add(Restrictions.eq("dia", dia)).add(Restrictions.eq("mes", mes))
					.add(Restrictions.eq("ano", ano)).list();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}
		return list;

	}
	@SuppressWarnings("unchecked")
	public List<Os> getOsEnt(int dia, int mes, int ano) {

		List<Os> list = new ArrayList<Os>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		try {

			list = s.createCriteria(Os.class).add(Restrictions.eq("diaEnt", dia)).add(Restrictions.eq("mesEnt", mes))
					.add(Restrictions.eq("anoEnt", ano)).list();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}
		return list;

	}
	
	@SuppressWarnings("unchecked")
	public List<Os> getOsCliente(int id) {

		List<Os> list = new ArrayList<Os>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		try {

			list = s.createCriteria(Os.class).add(Restrictions.eq("idCliente", id)).list();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}
		return list;

	}

}
