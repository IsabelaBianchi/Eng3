package programa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


import programa.entity.MateriaPrima;
import programa.entity.HibernateUtil;


public class MateriaPrimaDao {

	public void addMateriaPrima(MateriaPrima materiaPrima) {

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(materiaPrima);
		s.getTransaction().commit();
		s.close();
	}

	@SuppressWarnings("unchecked")
	public List<MateriaPrima> listMateriaPrima() {
		List<MateriaPrima> list = new ArrayList<MateriaPrima>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		list = s.createQuery("from MateriaPrima").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}
	
	public List<MateriaPrima> buscarFornecedor(String tfBuscaMateria){
		System.out.println("-----");
		MateriaPrima materiaPrimaEncontrada = null;
		List<MateriaPrima> list = new ArrayList<MateriaPrima>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		list = s.createQuery("from MateriaPrima where nome like '%"+tfBuscaMateria+"%'").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}  
	
	public List<MateriaPrima> buscarMateriaId(String tfBuscaMateria){
		System.out.println("-----");
		MateriaPrima materiaPrimaEncontrada = null;
		List<MateriaPrima> list = new ArrayList<MateriaPrima>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		list = s.createQuery("from MateriaPrima where idMateria like '%"+tfBuscaMateria+"%'").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}  


	public void removeMateriaPrima(Integer id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		MateriaPrima c = (MateriaPrima) s.load(MateriaPrima.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	public void updateMateriaPrima(MateriaPrima materiaPrima) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(materiaPrima);
		s.getTransaction().commit();
		s.close();
	}

	public MateriaPrima getMateriaPrima(String materiaPrima) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		MateriaPrima c = null;

		try {

			c = (MateriaPrima) s.createCriteria(MateriaPrima.class).add(Restrictions.eq("nome",materiaPrima)).uniqueResult();

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

	
	public MateriaPrima getMateriaPrima(int idMateria) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		MateriaPrima c = null;

		try {

			c = (MateriaPrima) s.createCriteria(MateriaPrima.class).add(Restrictions.eq("idMateria",idMateria)).uniqueResult();

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


