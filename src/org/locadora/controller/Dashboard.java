package org.locadora.controller;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.locadora.model.Cliente;
import org.locadora.model.Filme;
import org.locadora.model.FilmeAlugado;

/*
 * Andreia Letícia de Faria
 * 117114337
 * Gustavo Maciel Pimenta
 * 11621522
 */

public class Dashboard {

	public static void main(String[] args) {

		Locadora locadora = new Locadora("Locadora de Filmes TPII");

		int op = Integer.parseInt(JOptionPane
				.showInputDialog("Selecione uma opção:\n"
						+ "1 - Cadastrar cliente\n"
						+ "2 - Cadastrar filme\n"
						+ "3 - Alugar Filme\n"
						+ "4 - Visualizar estoque\n"
						+ "5 - Relatório de filmes alugados\n"
						+ "6 - Lista de clientes\n"
						+ "Pressione outra tecla para sair."));

		do {
			switch (op) {

				case 1: {
	
					String nomeCli = JOptionPane.showInputDialog("Digite o nome do cliente");
					String cpf = JOptionPane.showInputDialog("Digite o CPF");
					String endereco = JOptionPane.showInputDialog("Digite o endereço");
					String dataNasc = JOptionPane.showInputDialog("Digite a data de nascimento");
					locadora.cadastrarCliente(nomeCli, cpf, endereco, dataNasc);
					break;
				}
	
				case 2: {
	
					String nomeFilm = JOptionPane.showInputDialog("Digite o nome do filme");
					String classificacao = JOptionPane.showInputDialog("Digite a classificação - Livre ou Adulto");
					int estoque;
					do {
						estoque = Integer.parseInt(JOptionPane.showInputDialog("informe a quantidade em estoque"));
						if (estoque < 0) {
							JOptionPane.showMessageDialog(null, "O estoque não pode ser negativo.", "Atenção!", 0);
						} else {
							break;
						}
					} while (true);
					locadora.cadastrarFilme(nomeFilm, classificacao, estoque);
					break;
				}
	
				case 3: {
	
					String strEstoque = "";
	
					int idAlugar = Integer.parseInt(JOptionPane.showInputDialog(locadora.getNome() + "\n\n" + strEstoque
							+ "\n" + "Digite o ID do filme que deseja alugar:\n\n"));
	
					String cpfAlugar = JOptionPane.showInputDialog(locadora.getNome() + "\n" + "Digite o CPF do cliente");
	
					if ((locadora.encontrarCliente(cpfAlugar)!= null) && (locadora.encontrarFilme(idAlugar) != null)) {
						Filme filmeAlugar = locadora.encontrarFilme(idAlugar);
						Cliente clienteAlugar = locadora.encontrarCliente(cpfAlugar);
	
						locadora.alugarFilme(filmeAlugar, clienteAlugar);
					} else {
						JOptionPane.showMessageDialog(null, "ID e/ou CPF incorretos.", "Atenção!", 0);
					}
	
					break;
				}
	
				case 4: {
	
					String strEstoque = "";
					ArrayList<Filme> filmes = new ArrayList<>(locadora.getFilmes());
					for (int i = 0; i < filmes.size(); i++) {
						strEstoque = strEstoque + filmes.get(i).visualizarFilmes() + "\n";
					}
					JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strEstoque);
					break;
				}
	
				case 5: {
	
					String strAlugados = "";
					List<FilmeAlugado> filmesAlugados = locadora.filmesAlugados();
					for (int i = 0; i < filmesAlugados.size(); i++) {
						strAlugados = strAlugados + filmesAlugados.get(i).visualizarFilmes() + "\n";
					}
	
					JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strAlugados);
	
					break;
				}
				case 6:
					List<Cliente>clientes = locadora.listarClientes();
					String strClientes = "";
					for(Cliente cliente:clientes) {
						strClientes+=cliente.toString()+"\n";
					}
					JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strClientes);
					break;
				default: {
					System.exit(0);
				}
			}

			op = Integer.parseInt(JOptionPane
					.showInputDialog("Selecione uma opção:\n"
							+ "1 - Cadastrar cliente\n"
							+ "2 - Cadastrar filme\n"
							+ "3 - Alugar Filme\n"
							+ "4 - Visualizar estoque\n"
							+ "5 - Relatório de filmes alugados\n"
							+ "6 - Lista de clientes\n"
							+ "Pressione outra tecla para sair."));


		} while (true);
	}

}
