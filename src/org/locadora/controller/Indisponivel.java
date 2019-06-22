package org.locadora.controller;

public class Indisponivel implements StateFilme {

	public StateFilme estoqueDisponibilidade(int estoque) {
		
		if (estoque > 0) {
			return new Disponivel();
		}
		return this;
	}

	@Override
	public boolean disponibilidade() {
		return false;
	}

}