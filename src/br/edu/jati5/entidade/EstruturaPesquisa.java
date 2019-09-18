
package br.edu.jati5.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "estrutura_pesquisa")
public class EstruturaPesquisa extends Entidade{

	@Column(nullable = true)
	private String col_1_nome_familia;
	@Column(nullable = false)
	private String col_2_nome, col_3_valor, categoria_dados, titulo_estrutura;
	@ManyToOne
	@JoinColumn(name = "pesquisa_tk", nullable = false)
	private Pesquisa pesquisa;
	
	@Transient
	private List<Dado> dados;
	
}
