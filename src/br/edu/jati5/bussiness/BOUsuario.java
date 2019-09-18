package br.edu.jati5.bussiness;

import br.edu.jati5.dao.DAO;
import br.edu.jati5.dao.DAOUsuario;
import br.edu.jati5.entidade.Usuario;

public class BOUsuario extends BO<Usuario>{

	public BOUsuario() {
		super(new DAOUsuario(), Usuario.class);
		// TODO Auto-generated constructor stub
	}
	
	

}
