package org.locadora.dao;

public class IndisponivelDAO implements StateFilmeDAO {

	public StateFilmeDAO estoqueDisponibilidade(int estoque) {
		
		if (estoque > 0) {
			return new DisponivelDAO();
		}
		return this;
	}

	@Override
	public boolean disponibilidade() {
		return false;
	}

}