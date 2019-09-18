package br.edu.jati5.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
}
