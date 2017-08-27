package programa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import programa.entity.Cliente;
import programa.entity.HibernateUtil;
import programa.entity.Produto;

public class ProdutoDao {
	
	public void addProduto(Produto produto) {

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(produto);
		s.getTransaction().commit();
		s.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> listProduto() {
		List<Produto> list = new ArrayList<Produto>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		list = s.createQuery("from Produto").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}
	
	public List<Produto> buscarProduto(String tfBuscaProduto){
		System.out.println("-----");
		Produto produtoEncontrado = null;
		List<Produto> list = new ArrayList<Produto>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		list = s.createQuery("from Produto where nomeProduto like '%"+tfBuscaProduto+"%'").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}
	public List<Produto> buscarProdutoId(String tfBuscaProduto){
		System.out.println("-----");
		Produto produtoEncontrado = null;
		List<Produto> list = new ArrayList<Produto>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		list = s.createQuery("from Produto where idProduto like '%"+tfBuscaProduto+"%'").list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}
	
	public List<Produto> buscarProdutoObj(String tfNomeProduto){
		List<Produto> list = new ArrayList<Produto>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		list = s.createQuery("from Produto where idProduto like '%"+tfNomeProduto+"%'").list();
		s.getTransaction().commit();
		s.close();		
		return list;
		
	}
	
	public boolean verificarProdutoIgual(String nomeProduto){
		System.out.println(nomeProduto);
		boolean prodIgual;
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		List<Produto> list = new ArrayList<Produto>();
		
		list  = s.createQuery("from Produto where nomeProduto like '%"+nomeProduto+"%'").list();
		s.getTransaction().commit();
		s.close();
		System.out.println(list);
		if( list == null || list.isEmpty()){
			prodIgual=false;
			return prodIgual;
		 }else{
			prodIgual=true;
			return prodIgual;
		 }
		
		
	}
	
	
	public void removeProduto(Integer id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Produto c = (Produto) s.load(Produto.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	public void updateProduto(Produto produto) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(produto);
		s.getTransaction().commit();
		s.close();
	}

	public Produto getProduto(String produto) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Produto c = null;

		try {

			c = (Produto) s.createCriteria(Produto.class).add(Restrictions.eq("nomeProduto",produto)).uniqueResult();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}

		if (c == null) {
			System.out.println("Retornou null");
			return null;
		} else {

			System.out.println("Retornou o produto");
			return c;
		}

	}

	
	public Produto getProduto(int idProduto) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Produto c = null;

		try {

			c = (Produto) s.createCriteria(Produto.class).add(Restrictions.eq("idProduto",idProduto)).uniqueResult();

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
