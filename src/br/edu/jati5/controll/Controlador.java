package br.edu.jati5.controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

import br.edu.jati5.entidade.Dado;
import br.edu.jati5.entidade.EstruturaPesquisa;
import br.edu.jati5.entidade.Pesquisa;
import br.edu.jati5.entidade.Usuario;
import br.edu.jati5.exceptions.ValidacaoException;
import br.edu.jati5.fachada.Facade;
import br.edu.jati5.tabela.CellRenderer;
import br.edu.jati5.tabela.TDado;
import br.edu.jati5.tabela.TEstrutura;
import br.edu.jati5.tabela.TPesquisa;
import br.edu.jati5.util.DateUtil;
import br.edu.jati5.util.EntidadeUtil;
import br.edu.jati5.util.ViewUtil;
import br.edu.jati5.view.JCadastro;
import br.edu.jati5.view.Janela;
import br.edu.jati5.view.MeuJDialog;
import br.edu.jati5.view.TelaCadDado;
import br.edu.jati5.view.TelaCadEstrutura;
import br.edu.jati5.view.TelaCadPesquisa;
import br.edu.jati5.view.TelaEstrutura;
import br.edu.jati5.view.TelaGrafico;
import br.edu.jati5.view.TelaPesquisa;
import br.edu.jati5.view.TelaPesquisaTabela;

public class Controlador implements ActionListener{

	private final Janela janela;
	
	private Usuario usuario_atual;
	private Pesquisa pesquisa_atual;
	private EstruturaPesquisa estrutura_atual;
	
	private TPesquisa t_pesquisa;
	private TEstrutura t_estrutura;
	private TDado t_dado;
	
	public Controlador(Janela janela)
	{
		this.janela = janela;
	}
	
	public void adicionar_tables()
	{
		/*
		t_pesquisa = new TPesquisa();
		t_estrutura = new TEstrutura();
		t_dado = new TDado();
		
		
		JTable table_pesquisa = janela.getTelaInfUsuario().getTable();
		JTable table_estrutura = janela.getTelaInfPesquisa().getTable();
		JTable table_dado = janela.getTelaInfEstrutura().getTable();
		
		table_pesquisa.setRowHeight(50);
		table_estrutura.setRowHeight(50);
		table_dado.setRowHeight(50);
		
		table_pesquisa.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		table_estrutura.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		table_dado.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		
		table_pesquisa.setModel(t_pesquisa);
		table_estrutura.setModel(t_estrutura);
		table_dado.setModel(t_dado);
		
		table_pesquisa.setDefaultRenderer(Object.class, new CellRenderer());
		table_estrutura.setDefaultRenderer(Object.class, new CellRenderer());
		table_dado.setDefaultRenderer(Object.class, new CellRenderer());
		
		adicionar_eventos_table(table_pesquisa);
		adicionar_eventos_table(table_estrutura);
		adicionar_eventos_table(table_dado);
		*/
	}
	
	private void adicionar_eventos_table(JTable table)
	{
		table.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int linha = table.rowAtPoint(e.getPoint());
				int coluna = table.columnAtPoint(e.getPoint());
				
				if(!SwingUtilities.isRightMouseButton(e) && !(linha < 0 || coluna < 0)) 
				{
					table.setRowSelectionInterval(linha, linha);
					table.setColumnSelectionInterval(coluna, coluna);
					
					if(table.getModel() instanceof TPesquisa)
					{
						if(coluna == 3)
						{
							
							exibir_tela(pesquisa_atual);
						}
						else if(coluna == 4)
						{
							if(MeuJDialog.exibirAlertaPergunta(null, "Remoção", "Tem certeza que deseja remover?") == 1)
							try 
							{

							} 
							catch (Exception e1)
							{
								MeuJDialog.exibirAlertaErro(null, "Erro", e1.getMessage());
							}
						}
						
						if(coluna != 4)
						{
							String titulo = "";
							String desc = "";
							
							TelaPesquisaTabela tela = janela.getTelaInfUsuario().getTelaPesquisaTabela();
							tela.getCt_titulo().setTexto(titulo);
							tela.getCampoTextoArea().setTexto(desc);
						}
					}
					else if(table.getModel() instanceof TEstrutura)
					{
						if(coluna == 1)
						{
							
							exibir_tela(estrutura_atual);
							
						}
						else if(coluna == 2)
						{
							if(MeuJDialog.exibirAlertaPergunta(null, "Remoção", "Tem certeza que deseja remover?") == 1)
							try 
							{

							} 
							catch (Exception e1)
							{
								MeuJDialog.exibirAlertaErro(null, "Erro", e1.getMessage());
							}
						}
					}
					else if(table.getModel() instanceof TDado)
					{
						if(coluna == 3)
						{
							if(MeuJDialog.exibirAlertaPergunta(null, "Remoção", "Tem certeza que deseja remover?") == 1)
							try 
							{
							} 
							catch (Exception e1)
							{
								MeuJDialog.exibirAlertaErro(null, "Erro", e1.getMessage());
							}
						}
					}
				}
			}
		});
		
		table.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if(table.getModel() instanceof TDado)
					{
						//if(t_dado.getSelectedItem() != null)
						try 
						{
							//t_dado.setSelectedItem(null);
							atualiza_graficos(estrutura_atual, janela.getTelaInfEstrutura().getTelaGrafico());
						}
						catch (Exception e1) 
						{
							MeuJDialog.exibirAlertaErro(null, "Erro", e1.getMessage());
						}
					}
				}
			}
		});
	}
	
	
	public void adicionar_eventos()
	{
		janela.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				if(MeuJDialog.exibirAlertaPergunta(null, "Sair do Sistema", "Deseja sair do Sistema?") == 1)
					System.exit(0);
			}
		});
		
		janela.getTelaLogin().getBtnLogar()
			.addActionListener(ActionEvent->
			{
				try 
				{
					janela.getTelaLogin().getLoginField().getTexto(); 
					janela.getTelaLogin().getSenhaField().getTexto();
					
					
					janela.getTelaInfUsuario().getTelaUsuarioTabela().getNomeField().setTexto("");
					janela.getTelaInfUsuario().getTelaUsuarioTabela().getLoginField().setTexto("");
					
					janela.show_tela_usuario();
					janela.getTelaLogin().limpar_campos();
				}
				catch (Exception e)
				{
					janela.getTelaLogin().exibirErro(e.getMessage());
				}
			});
		
		janela.getTelaCadUsuario().getTelaCadastroEdicao().getBtBotao1()
			.addActionListener(ActionEvent->
			{
				try 
				{
					Usuario usuario = new Usuario();
					
					janela.getTelaCadUsuario().getNomeField().getTexto();
					janela.getTelaCadUsuario().getLoginField().getTexto();
					janela.getTelaCadUsuario().getSenhaField().getTexto();
					
					
					janela.getTelaCadUsuario().limpar_campos();

				} 
				catch (Exception e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
				}
			});
		
		janela.getTelaInfUsuario().getTelaUsuarioTabela()
			.getTelaCadastroEdicao().getBtBotao1()
			.addActionListener(ActionEvent->
			{
				try 
				{
							janela.getTelaInfUsuario().getTelaUsuarioTabela()
							.getNomeField().getTexto();
					
					janela.getTelaInfUsuario().getTelaUsuarioTabela()
					.getTelaCadastroEdicao().escondeBtn();
				} 
				catch (Exception e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
				}
				
			});
		
		janela.getTelaInfUsuario().getTelaUsuarioTabela().getBtConcluir()
		.addActionListener(ActionEvent->
		{
			try 
			{
				Usuario userTemp = new Usuario();
				
				
				janela.getTelaInfUsuario().getTelaUsuarioTabela().escondeBtn();
			} 
			catch (Exception e)
			{
				MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
			}
			
		});
		
		janela.getTelaInfUsuario().getBtBuscar()
			.addActionListener(ActionEvent->
			{
				try 
				{
					
				} 
				catch (Exception e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
				}
			});
		
		janela.getTelaInfUsuario().getBtCadastrar()
			.addActionListener(ActionEvent->
			{
				JCadastro.exibir_cadastro_pesquisa();
			});
		
		janela.getTelaInfPesquisa().getTelaPesquisa()
			.getTelaCadastroEdicao().getBtBotao1()
			.addActionListener(ActionEvent->
			{
				try
				{
					TelaPesquisa tela = janela.getTelaInfPesquisa().getTelaPesquisa();
					
					Pesquisa pesquisaTemp = new Pesquisa();
					
					/*
					pesquisaTemp.setId(pesquisa_atual.getId());
					pesquisaTemp.setUsuario(usuario_atual);
					pesquisaTemp.setEstruturaPesquisas(pesquisa_atual.getEstruturaPesquisas());
					pesquisaTemp.setTitulo(tela.getCt_titulo().getTexto());
					pesquisaTemp.setDescricao(tela.getCampoTextoArea().getTexto());
					pesquisaTemp.setDataInicio(DateUtil.parseToLocalDate(tela.getDc_inicio().getDate()));
					pesquisaTemp.setDataFim(DateUtil.parseToLocalDate(tela.getDc_fim().getDate()));
					*/
					
					pesquisa_atual = pesquisaTemp;
					
					atualiza_graficos(pesquisa_atual, janela.getTelaInfPesquisa().getTelaGraficoPesquisa());
				} 
				catch (Exception e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
				}
				
				janela.getTelaInfPesquisa().getTelaPesquisa()
				.getTelaCadastroEdicao().escondeBtn();
			});
		
		janela.getTelaInfPesquisa().getBtBuscar()
			.addActionListener(ActionEvent->
			{
				try 
				{
				} 
				catch (Exception e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
				}
			});
		
		janela.getTelaInfPesquisa().getBtCadastrar()
			.addActionListener(ActionEvent->
			{
				JCadastro.exibir_cadastro_estrutura();
			});
		
		janela.getTelaInfEstrutura().getTelaEstrutura()
			.getTelaCadastroEdicao().getBtBotao1()
			.addActionListener(ActionEvent->
			{
				try 
				{
					TelaEstrutura tela = janela.getTelaInfEstrutura().getTelaEstrutura();
							
					EstruturaPesquisa estruturaTemp = new EstruturaPesquisa();
					/*
					estruturaTemp.setId(estrutura_atual.getId());
					estruturaTemp.setTitulo_estrutura(tela.getCt_titulo().getTexto());
					estruturaTemp.setPesquisa(pesquisa_atual);
					estruturaTemp.setDados(estrutura_atual.getDados());
					estruturaTemp.setCol_3_valor(tela.getCt_valor().getTexto());
					estruturaTemp.setCol_2_nome(tela.getCt_nome().getTexto());
					estruturaTemp.setCol_1_nome_familia(tela.getCt_familia().getTexto());
					estruturaTemp.setCategoria_dados(tela.getCt_categoria().getTexto());
					*/
					estrutura_atual = estruturaTemp;
					
					janela.getTelaInfEstrutura().getTelaEstrutura()
					.getTelaCadastroEdicao().escondeBtn();
					
					exibir_tela(estrutura_atual);
				} 
				catch (Exception e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
				}
			});
		
		janela.getTelaInfEstrutura().getBtBuscar()
			.addActionListener(ActionEvent->
			{
				try 
				{
					
					atualiza_graficos(estrutura_atual, janela.getTelaInfEstrutura().getTelaGrafico());
				} 
				catch (Exception e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
				}
			});
	
		janela.getTelaInfEstrutura().getBtCadastrar()
			.addActionListener(ActionEvent->
			{
				JCadastro.exibir_cadastro_dado();
			});
	}
	
	public void adicionar_eventos_cadastro()
	{
		JCadastro.getInstance().getTelaCadPesquisa()
			.getTelaCadastroEdicao().getBtBotao1()
				.addActionListener(this);
		JCadastro.getInstance().getTelaCadEstrutura()
			.getTelaCadastroEdicao().getBtBotao1()
				.addActionListener(this);
		JCadastro.getInstance().getTelaCadDado()
			.getTelaCadastroEdicao().getBtBotao1()
				.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == JCadastro.getInstance().getTelaCadPesquisa()
			.getTelaCadastroEdicao().getBtBotao1())
		{
			try
			{
				TelaCadPesquisa tela = JCadastro.getInstance().getTelaCadPesquisa();
				
				Pesquisa pesquisa = new Pesquisa();
	
				tela.getCt_titulo().getTexto();
				tela.getCampoTextoArea().getTexto();
				tela.getDc_inicio().getDate();
				tela.getDc_fim().getDate();
				
				
				tela.limpar_campos();
			} 
			catch (Exception e1) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro", e1.getMessage());
			}
		}
		else if(e.getSource() == JCadastro.getInstance().getTelaCadEstrutura()
			.getTelaCadastroEdicao().getBtBotao1())
		{
			try
			{
				TelaCadEstrutura tela = JCadastro.getInstance().getTelaCadEstrutura();
				
				EstruturaPesquisa estruturaPesquisa = new EstruturaPesquisa();
				
				tela.getCt_titulo().getTexto();
				tela.getCt_valor().getTexto();
				tela.getCt_nome().getTexto();
				tela.getCt_familia().getTexto();
				tela.getCt_categoria().getTexto();
				
				
				tela.limpar_campos();
			} 
			catch (Exception e1) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro", e1.getMessage());
			}
		}
		else if(e.getSource() == JCadastro.getInstance().getTelaCadDado()
			.getTelaCadastroEdicao().getBtBotao1())
		{
			try
			{
				TelaCadDado tela = JCadastro.getInstance().getTelaCadDado();
				Dado dado = new Dado();
				
				tela.getCt_valor().getTexto();
				tela.getCt_nome().getTexto();
				tela.getCt_familia().getTexto();
				
				
				tela.limpar_campos();
				
				janela.getTelaInfEstrutura().getTelaGrafico().addValues(null, "", "");
			} 
			catch (Exception e1) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro", e1.getMessage());
			}
		}
	}
	
	public void exibir_tela(Object object)
	{
		if(object instanceof Pesquisa)
		{
			Pesquisa obj = (Pesquisa) object;
			
			janela.getTelaInfPesquisa().getTelaPesquisa().getCt_titulo().setTexto("");
			janela.getTelaInfPesquisa().getTelaPesquisa().getCampoTextoArea().setTexto("");
			janela.getTelaInfPesquisa().getTelaPesquisa().getDc_inicio().setDate(null);
			janela.getTelaInfPesquisa().getTelaPesquisa().getDc_fim().setDate(null);
			
			atualiza_graficos(obj, janela.getTelaInfPesquisa().getTelaGraficoPesquisa());
			janela.show_tela_pesquisa();
		}
		else if(object instanceof EstruturaPesquisa)
		{
			EstruturaPesquisa obj = (EstruturaPesquisa) object;
			
			janela.getTelaInfEstrutura().getTelaEstrutura().getCt_titulo().setTexto("");
			janela.getTelaInfEstrutura().getTelaEstrutura().getCt_categoria().setTexto("");
			janela.getTelaInfEstrutura().getTelaEstrutura().getCt_familia().setTexto("");
			janela.getTelaInfEstrutura().getTelaEstrutura().getCt_nome().setTexto("");
			janela.getTelaInfEstrutura().getTelaEstrutura().getCt_valor().setTexto("");
			
			janela.getTelaInfEstrutura().getTelaGrafico().setNomeCategoria("", "", "");
			
			janela.show_tela_estrutura();
		}
	}
	
	private void atualiza_graficos(Object object, Object panel)
	{
		if(object instanceof Pesquisa)
		{
			Pesquisa obj = (Pesquisa) object;
			((TelaGrafico)panel).clearValues();
			
			double d = 0;
			if(d > 100) d = 100;
			((TelaGrafico)panel).addValues(d, "Decorridos", "");
			((TelaGrafico)panel).addValues(100 - d, "Restantes", "");
			
		}
		else if(object instanceof EstruturaPesquisa)
		{
			((TelaGrafico)panel).clearValues();
			
		}
	}
}
