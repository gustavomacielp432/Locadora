package org.locadora.dao;
<<<<<<< HEAD
=======

import org.locadora.controller.Indisponivel;
import org.locadora.controller.StateFilme;
>>>>>>> state-merged
import org.locadora.model.Filme;

public class FilmeDAO extends GenericDAO<Filme, Integer> {
	public FilmeDAO() {
		super(Filme.class);
	}

	public void diminuirEstoque(Filme filme) {
		filme.setEstoque(filme.getEstoque() - 1);
		super.atualizar(filme);

	}
<<<<<<< HEAD
=======

	public void atualizarDisponibilidade(Filme filme) {
		StateFilme disponibilidade;
		disponibilidade = new Indisponivel();
		filme.setDisponibilidade(disponibilidade.estoqueDisponibilidade(filme.getEstoque()));

	}

	public boolean disponibilidade(Filme filme) {
		StateFilme disponibilidade;
		disponibilidade = filme.getDisponibilidade();
		return disponibilidade.disponibilidade();
	}
>>>>>>> state-merged
}
