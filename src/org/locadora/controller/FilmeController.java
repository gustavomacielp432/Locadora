package org.locadora.controller;

import java.util.List;

import javax.swing.JOptionPane;

import org.locadora.dao.FilmeAlugadoDAO;
import org.locadora.dao.FilmeDAO;
import org.locadora.model.Cliente;
import org.locadora.model.Filme;
import org.locadora.model.FilmeAlugado;

public class FilmeController {

	private FilmeDAO filmeDAO = new FilmeDAO();
	private FilmeAlugadoDAO filmeAlugadoDAO = new FilmeAlugadoDAO();

	public void alugarFilme(Filme filme, Cliente cliente) {

		if (filme != null && filme.getId() != -1 && cliente != null && !cliente.getCpf().isEmpty()) {
			if (filme.getEstoque() > 0) {
				FilmeAlugado filmeAlugado = new FilmeAlugado(cliente, filme);
				filmeAlugadoDAO.salvar(filmeAlugado);
				filmeDAO.diminuirEstoque(filme);

				JOptionPane.showMessageDialog(null, "Filme alugado com sucesso.", "Atenção!", 1);

			} else {
				JOptionPane.showMessageDialog(null, "Estoque insuficiente.", "Atenção!", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "CPF não cadastrado.", "Atenção!", 0);
		}
	}
	
	public void cadastrarFilme(String nome, String classificacao, int estoque) {
		Filme filme = new Filme(nome, classificacao, estoque);
		filmeDAO.salvar(filme);
	}

	public static void atualizarDisponibilidade(Filme filme) {
		StateFilme disponibilidade;
		disponibilidade = new Indisponivel();
		filme.setStatusFilme(disponibilidade.estoqueDisponibilidade(filme.getEstoque()));
	}

	public List<Filme> getFilmes() {
		return filmeDAO.getList();
	}

	public List<FilmeAlugado> getFilmesAlugados() {
		return filmeAlugadoDAO.getList();
	}

	public Filme encontrarFilme(int id) {
		return filmeDAO.buscarPorChavePrimaria(id);
	}

}
