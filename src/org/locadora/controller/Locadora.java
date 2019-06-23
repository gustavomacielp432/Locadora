package org.locadora.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
				filmeDAO.atualizarDisponibilidade(filme);

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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<FilmeAlugado> filmesAlugados() {
		return filmeAlugadoDAO.getList();
	}

	public Cliente encontrarCliente(String cpf) {
		return clienteDAO.buscarClientePorCPF(cpf);
	}

	public Cliente retornaCliente(int id) {
		return clienteDAO.buscarPorChavePrimaria(id);
	}

	public List<Cliente> listarClientes() {
		return clienteDAO.getList();
	}

	public void cadastrarCliente(String nome, String cpf, String endereco, LocalDate dataNasc) {
		Cliente cliente = new Cliente(nome, cpf, endereco, dataNasc);
		clienteDAO.salvar(cliente);
	}

	public void cadastrarFilme(String nome, String classificacao, int estoque) {
		Filme filme = new Filme(nome, classificacao, estoque);
		filmeDAO.atualizarDisponibilidade(filme);
		filmeDAO.salvar(filme);
	}

	public Filme encontrarFilme(int id) {
		return filmeDAO.buscarPorChavePrimaria(id);
	}

	public List<Filme> getFilmes() {
		return filmeDAO.getList();
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
	
	public String iteratorFilme(Iterator<Filme> itFilmes) {
		String strEstoque="";
		
		Filme filme;
		while(itFilmes.hasNext()) {
			filme=(Filme) itFilmes.next();
			filmeDAO.atualizarDisponibilidade(filme);
			if(filme.getDisponibilidade().disponibilidade()) {
				strEstoque = strEstoque + filme.visualizarFilmes() + "\n";
				
			}
			
		}
		return strEstoque;
	}

}
