package br.edu.jati5.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.jati5.entidade.Entidade;
import br.edu.jati5.exceptions.DAOException;

public abstract class DAO <T extends Entidade> {
	
	private EntityManagerFactory entityManagerFactory;
	
	public DAO() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("postgresql");
	}
	
	public EntityManager createEntityManager(){
		return entityManagerFactory.createEntityManager();
	}

	public T inserir(T t) throws DAOException{
		EntityManager entityManager = createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(t);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			throw new DAOException("Erro da inserção no banco de dados");
		} finally {
			entityManager.close();
		}
		return t;	
	}
	
	public T atualizar(T t) throws DAOException{
		EntityManager entityManager = createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(t);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			throw new DAOException("Erro da atualizacao no banco de dados");
		} finally {
			entityManager.close();
		}
		return t;	
	}
	
	public T deletar(T t) throws DAOException{
		EntityManager entityManager = createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(t);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			throw new DAOException("Erro de deletar no banco de dados");
		} finally {
			entityManager.close();
		}
		return t;	
	}
	
	public T remover(T t) throws DAOException{
		EntityManager entityManager = createEntityManager();
		
		try {
			t.setAtivado(false);
			entityManager.getTransaction().begin();
			entityManager.merge(t);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			throw new DAOException("Erro de remover no banco de dados");
		} finally {
			entityManager.close();
		}
		return t;	
	}
	
	public T buscar(int id, Class<T> classe) throws DAOException{
		EntityManager entityManager = createEntityManager();
		
		T t = null;
		
		try {
			t = entityManager.find(classe, id);
			
		}
		catch (NoResultException e) {
			e.printStackTrace();
			t = null;
		}
		catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			throw new DAOException("Erro da busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return t;	
	}
	
	public T getConsultaHQL(String sql, Class<T> classe){
		
		EntityManager entityManager = createEntityManager();
		T t = null;
		Query query = entityManager.createNativeQuery(sql, classe);
		t = (T) query.getSingleResult();
		return t;
	}
}
