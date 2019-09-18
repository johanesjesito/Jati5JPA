package br.edu.jati5.app;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import br.edu.jati5.controll.Controlador;
import br.edu.jati5.entidade.Usuario;
import br.edu.jati5.exceptions.DAOException;
import br.edu.jati5.fachada.Facade;
import br.edu.jati5.view.Janela;

public class App {

	public static void main(String[] args) throws DAOException 
	{
		
		
		Usuario u = null;
		
		Facade.getInstance().getBoUsuario().getConsultaHQL("select u from Usuario u where u.ativado = true and u.login = 'admin2'");
		
		System.out.println(u.getId());
		
		/*
		try 
		{
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} 
		catch (UnsupportedLookAndFeelException e) 
		{
			e.printStackTrace();
		}
		
		
		Facade.getInstance();
		
		Janela janela = new Janela();
		
		Controlador controll = new Controlador(janela);
		controll.adicionar_tables();
		controll.adicionar_eventos();
		controll.adicionar_eventos_cadastro();
		
		janela.setVisible(true);
		*/
	}
}
