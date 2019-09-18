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
import br.edu.jati5.exceptions.BOException;
import br.edu.jati5.exceptions.DAOException;
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
	
	public <T extends Entidade> Entidade inserir(Entidade e) throws BOException, DAOException {
		if(e instanceof Usuario){
			return this.boUsuario.inserir((Usuario) e);
		} else if(e instanceof Pesquisa){
			return this.boPesquisa.inserir((Pesquisa) e);
		} else if(e instanceof Dado){
			return this.boDado.inserir((Dado) e);
		} else if(e instanceof EstruturaPesquisa){
			return this.boEstrutura.inserir((EstruturaPesquisa) e);
		}
		return null;
	}
	
	public <T extends Entidade> Entidade atualizar(Entidade e) throws BOException, DAOException {
		if(e instanceof Usuario){
			return this.boUsuario.atualizar((Usuario) e);
		} else if(e instanceof Pesquisa){
			return this.boPesquisa.atualizar((Pesquisa) e);
		} else if(e instanceof Dado){
			return this.boDado.atualizar((Dado) e);
		} else if(e instanceof EstruturaPesquisa){
			return this.boEstrutura.atualizar((EstruturaPesquisa) e);
		}
		return null;
	}
	
	public <T extends Entidade> Entidade deletar(Entidade e) throws BOException, DAOException {
		if(e instanceof Usuario){
			return this.boUsuario.deletar((Usuario) e);
		} else if(e instanceof Pesquisa){
			return this.boPesquisa.deletar((Pesquisa) e);
		} else if(e instanceof Dado){
			return this.boDado.deletar((Dado) e);
		} else if(e instanceof EstruturaPesquisa){
			return this.boEstrutura.deletar((EstruturaPesquisa) e);
		}
		return null;
	}
	
	public <T extends Entidade> Entidade remover(Entidade e) throws BOException, DAOException {
		if(e instanceof Usuario){
			return this.boUsuario.remover((Usuario) e);
		} else if(e instanceof Pesquisa){
			return this.boPesquisa.remover((Pesquisa) e);
		} else if(e instanceof Dado){
			return this.boDado.remover((Dado) e);
		} else if(e instanceof EstruturaPesquisa){
			return this.boEstrutura.remover((EstruturaPesquisa) e);
		}
		return null;
	}
	
	public <T extends Entidade> Entidade buscar(int id, Class<T> classe) throws BOException, DAOException {
		
		if(classe.getSimpleName().equals(Usuario.class.getSimpleName()))
			return this.boUsuario.buscar(id);
		else if(classe.getSimpleName().equals(Pesquisa.class.getSimpleName()))
			return this.boPesquisa.buscar(id);
		else if(classe.getSimpleName().equals(Dado.class.getSimpleName()))
			return this.boDado.buscar(id);
		else if(classe.getSimpleName().equals(EstruturaPesquisa.class.getSimpleName()))
			return this.boEstrutura.buscar(id);
		
		return null;
	}

	public BOUsuario getBoUsuario() {return boUsuario;}
	public BOPesquisa getBoPesquisa() {return boPesquisa;}
	public BOEstrutura getBoEstrutura() {return boEstrutura;}
	public BODado getBoDado() {return boDado;}
	
}
