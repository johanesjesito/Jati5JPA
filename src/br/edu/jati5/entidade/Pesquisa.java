package br.edu.jati5.entidade;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pesquisa")
public class Pesquisa {

	@Column
	private String titulo;
	@Column
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "usuario_fk", nullable = false)
	private Usuario usuario;
	@Column(name = "data_inicio")
	private LocalDate dataInicio;
	@Column(name = "data_fim")
	private LocalDate dataFim;
	
	@Transient
	private List<EstruturaPesquisa> estruturas;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public List<EstruturaPesquisa> getEstruturas() {
		return estruturas;
	}

	public void setEstruturas(List<EstruturaPesquisa> estruturas) {
		this.estruturas = estruturas;
	}
	
}
