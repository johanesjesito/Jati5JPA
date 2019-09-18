package br.edu.jati5.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dado")
public class Dado extends Entidade{

	@Column
	private String col_1_nome_familia, col_2_nome, col_3_valor;
	
	@ManyToOne
	@JoinColumn(name = "estrutura_pesquisa_jk")
	private EstruturaPesquisa estruturaPesquisa;
	
}
