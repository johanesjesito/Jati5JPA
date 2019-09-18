package br.edu.jati5.bussiness;

import br.edu.jati5.dao.DAO;
import br.edu.jati5.dao.DAOPesquisa;
import br.edu.jati5.entidade.Pesquisa;

public class BOPesquisa extends BO<Pesquisa>{

	public BOPesquisa() {
		super(new DAOPesquisa(), Pesquisa.class);
		// TODO Auto-generated constructor stub
	}
	
}
