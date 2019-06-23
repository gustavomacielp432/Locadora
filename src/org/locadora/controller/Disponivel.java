package org.locadora.controller;



public class Disponivel implements StateFilme {

	public StateFilme estoqueDisponibilidade(int estoque) {
		if (estoque == 0) {
			return new Indisponivel();
		}
		return this;
	}

	@Override
	public boolean disponibilidade() {
		return true;
	}

}
