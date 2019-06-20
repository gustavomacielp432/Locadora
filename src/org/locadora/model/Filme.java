package org.locadora.model;

import javax.persistence.Entity;

@Entity
public class Filme extends BaseEntity {

	private static final long serialVersionUID = -653598860173216554L;

	private String nome;
	private String classificacao;
	private int estoque;
	private StateFilme disponibilidade;

	public Filme(String nome, String classificacao, int estoque) {
		this.nome = nome;
		this.classificacao = classificacao;
		this.estoque = estoque;
		disponibilidade=new Indisponivel();
	}

	public Filme() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public String visualizarFilmes() {
		String status;
		if(disponibilidade.disponibilidade()) {status= "disponivel";}else {status= "indisponivel";}
		return "ID: " + super.getId() + " | " + "Nome: " + nome + " | " + "Qtd: " + estoque + " | " + "Class.: "
				+ classificacao + " = "+status ;
	}

	public void atualizarDisponibilidade(int estoque) {
		disponibilidade = disponibilidade.estoqueDisponibilidade(estoque);
	}

	public boolean disponibilidade() {

		return disponibilidade.disponibilidade();
	}

	

	

	
}
