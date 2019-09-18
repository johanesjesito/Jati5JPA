package br.edu.jati5.bussiness;

import br.edu.jati5.dao.DAO;
import br.edu.jati5.dao.DAODado;
import br.edu.jati5.entidade.Dado;

public class BODado extends BO<Dado>{

	public BODado() {
		super(new DAODado(), Dado.class);
	}
	
}
