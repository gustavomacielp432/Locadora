package org.locadora.model;
<<<<<<< HEAD
import javax.persistence.Entity;
=======

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.locadora.controller.StateFilme;
>>>>>>> state-merged

@Entity
public class Filme extends BaseEntity{
	
	private static final long serialVersionUID = -653598860173216554L;

	
	private String nome;
	private String classificacao;
	private int estoque;
	
	
	public Filme(String nome, String classificacao, int estoque) {
		this.nome = nome;
		this.classificacao = classificacao;
		this.estoque = estoque;
<<<<<<< HEAD
=======

>>>>>>> state-merged
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
<<<<<<< HEAD
	
	

	public String visualizarFilmes() {
		return "ID: " + super.getId() + " | " + "Nome: " + nome + " | " + "Qtd: " + estoque + " | " +  "Class.: " + classificacao;
	}
=======

	public StateFilme getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(StateFilme disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public String visualizarFilmes() {
		return "ID: " + super.getId() + " | " + "Nome: " + nome + " | " + "Qtd: " + estoque + " | " + "Class.: "
				+ classificacao;
	}

>>>>>>> state-merged
}
