package br.edu.jati5.entidade;

import java.time.LocalDate;
import java.util.List;

public class Pesquisa {

	private String titulo;
	private String descricao;

	private Usuario usuario;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	
	private List<EstruturaPesquisa> estruturas;
	
}
