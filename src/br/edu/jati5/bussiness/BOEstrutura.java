package br.edu.jati5.bussiness;

import br.edu.jati5.dao.DAO;
import br.edu.jati5.dao.DAOEstruturaPesquisa;
import br.edu.jati5.entidade.EstruturaPesquisa;

public class BOEstrutura extends BO<EstruturaPesquisa>{

	public BOEstrutura() {
		super(new DAOEstruturaPesquisa(), EstruturaPesquisa.class);
	}

}
