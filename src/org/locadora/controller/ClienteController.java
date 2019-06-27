package org.locadora.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.List;

import org.locadora.dao.ClienteDAO;
import org.locadora.model.Cliente;
import org.locadora.model.Filme;

public class ClienteController {

	private ClienteDAO clienteDAO = new ClienteDAO();

	public List<Cliente> getClientes() {
		return clienteDAO.getList();
	}

	public Cliente retornaCliente(int id) {
		return clienteDAO.buscarPorChavePrimaria(id);
	}

	public Cliente encontrarCliente(String cpf) {
		return clienteDAO.buscarClientePorCPF(cpf);
	}

	public void cadastrarCliente(String nome, String cpf, String endereco, LocalDate dataNasc) {
		Cliente cliente = new Cliente(nome, cpf, endereco, dataNasc);
		clienteDAO.salvar(cliente);
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

	public boolean validarDuplicidadeCpf(Iterator<?> iterator, String cpf) {
		boolean valido = false;
		Cliente cliente;
		while (iterator.hasNext()) {
			cliente = (Cliente) iterator.next();
			if (cliente.getCpf().equals(cpf)) {
				valido = true;
			}
		}
		return valido;
	}

}
