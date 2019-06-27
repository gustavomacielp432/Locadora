package org.locadora.controller;

import java.util.Iterator;

import org.locadora.model.Cliente;
import org.locadora.model.Filme;
import org.locadora.model.FilmeAlugado;

public class LocadoraController {

	private static final int OP_FILMES_DISPONIVEIS = 0;
	private static final String TIPO_CLIENTE = "Cliente";
	private static final String TIPO_FILME = "Filme";
	private static final String TIPO_FILME_ALUGADO = "FilmeAlugado";

	public String visualizaTodos(Iterator<?> iterator, String tipoObjeto) {
		String str = "";
		switch (tipoObjeto) {
		case TIPO_CLIENTE: {
			Cliente cliente;
			while (iterator.hasNext()) {
				cliente = (Cliente) iterator.next();
				str = str + cliente.toString() + "\n";
			}
			break;
		}
		case TIPO_FILME: {
			Filme filme;
			while (iterator.hasNext()) {
				filme = (Filme) iterator.next();
				str = str + filme.toString() + "\n";
			}
			break;

		}
		case TIPO_FILME_ALUGADO: {
			FilmeAlugado filmeAlugado;
			while (iterator.hasNext()) {
				filmeAlugado = (FilmeAlugado) iterator.next();
				str = str + filmeAlugado.toString() + "\n";
			}
			break;
		}
		default: {
			str = "Tipo inválido!";
		}
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
