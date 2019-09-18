package br.edu.jati5.fachada;

import br.edu.jati5.bussiness.BODado;
import br.edu.jati5.bussiness.BOEstrutura;
import br.edu.jati5.bussiness.BOPesquisa;
import br.edu.jati5.bussiness.BOUsuario;
import br.edu.jati5.entidade.Dado;
import br.edu.jati5.entidade.Entidade;
import br.edu.jati5.entidade.EstruturaPesquisa;
import br.edu.jati5.entidade.Pesquisa;
import br.edu.jati5.entidade.Usuario;
import br.edu.jati5.exceptions.ValidacaoException;

public class Facade {

	private static Facade instance;
	
	public static Facade getInstance()
	{
		if(instance == null)
			instance = new Facade();
		return instance;
	}
	
	private final BOUsuario boUsuario;
	private final BOPesquisa boPesquisa;
	private final BOEstrutura boEstrutura;
	private final BODado boDado;
	
	private Facade() 
	{
		boUsuario = new BOUsuario();
		boPesquisa = new BOPesquisa();
		boEstrutura = new BOEstrutura();
		boDado = new BODado();
	}

	public BOUsuario getBoUsuario() {return boUsuario;}
	public BOPesquisa getBoPesquisa() {return boPesquisa;}
	public BOEstrutura getBoEstrutura() {return boEstrutura;}
	public BODado getBoDado() {return boDado;}
	
}
