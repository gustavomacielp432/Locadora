package org.locadora.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.locadora.dao.ClienteDAO;
import org.locadora.dao.FilmeAlugadoDAO;
import org.locadora.dao.FilmeDAO;
import org.locadora.model.Cliente;
import org.locadora.model.Filme;
import org.locadora.model.FilmeAlugado;

public class Locadora {

	private static final int OP_FILMES_DISPONIVEIS = 0;
	private static final String TIPO_CLIENTE = "Cliente";
	private static final String TIPO_FILME = "Filme";
	private static final String TIPO_FILME_ALUGADO = "FilmeAlugado";

	private FilmeDAO filmeDAO = new FilmeDAO();
	private FilmeAlugadoDAO filmeAlugadoDAO = new FilmeAlugadoDAO();
	private ClienteDAO clienteDAO = new ClienteDAO();
	private String nome;

	public Locadora(String nome) {
		this.nome = nome;
	}

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

	public String getNome() {
		return nome;
	}

	public List<Filme> getFilmes() {
		return filmeDAO.getList();
	}

	public List<Cliente> getClientes() {
		return clienteDAO.getList();
	}

	public List<FilmeAlugado> getFilmesAlugados() {
		return filmeAlugadoDAO.getList();
	}

	public Cliente retornaCliente(int id) {
		return clienteDAO.buscarPorChavePrimaria(id);
	}

	public Filme encontrarFilme(int id) {
		return filmeDAO.buscarPorChavePrimaria(id);
	}

	public Cliente encontrarCliente(String cpf) {
		return clienteDAO.buscarClientePorCPF(cpf);
	}

	public void cadastrarCliente(String nome, String cpf, String endereco, LocalDate dataNasc) {
		Cliente cliente = new Cliente(nome, cpf, endereco, dataNasc);
		clienteDAO.salvar(cliente);
	}

	public void cadastrarFilme(String nome, String classificacao, int estoque) {
		Filme filme = new Filme(nome, classificacao, estoque);
		filmeDAO.salvar(filme);
	}

	public int calcularIdade(LocalDate date) {
		int idade = Period.between(date, LocalDate.now()).getYears();
		return idade;
	}

	public boolean isClienteElegivelParaAlugarFilme(String classificacao, int idade) {

		if (idade >= 18 && classificacao.equals("18")) {
			return true;
		} else if (idade >= 16 && classificacao.equals("16")) {
			return true;
		} else if (idade >= 14 && classificacao.equals("14")) {
			return true;
		} else if (idade >= 12 && classificacao.equals("12")) {
			return true;
		} else if (idade >= 10 && classificacao.equals("10")) {
			return true;
		} else if (idade < 10 && classificacao.equals("L")) {
			return true;
		} else {
			return false;
		}
	}

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

	public static void atualizarDisponibilidade(Filme filme) {
		StateFilme disponibilidade;
		disponibilidade = new Indisponivel();
		filme.setStatusFilme(disponibilidade.estoqueDisponibilidade(filme.getEstoque()));

	}

	public String visualizaSelecionados(Iterator<?> iterator, int operacao) {
		String str = "";

		switch (operacao) {
		case OP_FILMES_DISPONIVEIS: {
			Filme filme;
			while (iterator.hasNext()) {
				filme = (Filme) iterator.next();
				Locadora.atualizarDisponibilidade(filme);
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
	
	public boolean validarDuplicidadeCpf(Iterator<?> iterator, String cpf) {
		boolean valido = false;
		Cliente cliente;
		while (iterator.hasNext()) {
			cliente = (Cliente) iterator.next();
			if(cliente.getCpf().equals(cpf)) {
				valido = true;
			}
		}
		return valido;
	}


}
