package org.locadora.model;

public class Locadora {

	private String nome;

	private Locadora(String nome) {
		this.nome = nome;
	}

	private static Locadora instancia;

	public static synchronized Locadora getInstance() {
		if (instancia == null) {
			instancia = new Locadora("Locadora de Filmes");
		}
		return instancia;
	}

	public String getNome() {
		return nome;
	}

}
