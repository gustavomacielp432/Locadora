package org.locadora.dao;



public class DisponivelDAO implements StateFilmeDAO {

	public StateFilmeDAO estoqueDisponibilidade(int estoque) {
		if (estoque == 0) {
			return new IndisponivelDAO();
		}
		return this;
	}

	@Override
	public boolean disponibilidade() {
		return true;
	}

}
