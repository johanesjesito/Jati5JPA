package br.edu.jati5.bussiness;

import br.edu.jati5.dao.DAO;
import br.edu.jati5.entidade.Entidade;
import br.edu.jati5.exceptions.BOException;
import br.edu.jati5.exceptions.DAOException;

public abstract class BO <T extends Entidade>{

	protected DAO<T> daoT;
	protected Class<T> classe;
	
	public BO(DAO daoT, Class<T> classe) {
		this.daoT = daoT;
		this.classe = classe;
	}
	
	public T inserir(T t) throws BOException, DAOException
	{
		if(t == null)
			throw new BOException("Erro de inser��o");
		validarinsercao();
		return this.daoT.inserir(t);
	}
	
	public T atualizar(T t) throws BOException, DAOException
	{
		if(t == null || t.getId() <= 0)
			throw new BOException("Erro de inser��o");
		validaratualizacao();
		return this.daoT.atualizar(t);
	}
	
	public T deletar(T t) throws BOException, DAOException
	{
		if(t == null || t.getId() <= 0)
			throw new BOException("Erro de inser��o");
		return this.daoT.deletar(t);
	}
	
	public T remover(T t) throws BOException, DAOException
	{
		if(t == null || t.getId() <= 0)
			throw new BOException("Erro de inser��o");
		return this.daoT.remover(t);
	}
	
	public T buscar(int id) throws BOException, DAOException
	{
		if(id <= 0)
			throw new BOException("Erro de inser��o");
		return this.daoT.buscar(id, classe);
	}

	DAO<T> getDaoT() {
		return daoT;
	}
	
	public void validarinsercao(){}
	public void validaratualizacao(){}


}
