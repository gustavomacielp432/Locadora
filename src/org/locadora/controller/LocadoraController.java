package org.locadora.controller;

import java.util.Iterator;

import org.locadora.model.Cliente;
import org.locadora.model.Filme;
import org.locadora.model.FilmeAlugado;

public class LocadoraController {

	private static final int OP_FILMES_DISPONIVEIS = 0;

	public String visualizaTodos(Iterator<?> iterator) {
		String str = "";
		while (iterator.hasNext()) {
			str = str + iterator.next().toString() + "\n";
		}
		return str;
	}

	public String visualizaSelecionados(Iterator<?> iterator, int operacao) {
		String str = "";

		switch (operacao) {
		case OP_FILMES_DISPONIVEIS: {
			Filme filme;
			while (iterator.hasNext()) {
				filme = (Filme) iterator.next();
				FilmeController.atualizarDisponibilidade(filme);
				if (filme.getStatusFilme().disponibilidade()) {
					str = str + filme.toString() + "\n";
				}
			}
			break;
		}
		default:
			str = "Operação inválida!";
			break;
		}

		return str;
	}

}
