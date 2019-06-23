package org.locadora.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.locadora.model.Cliente;
import org.locadora.model.Filme;
import org.locadora.model.FilmeAlugado;


public class Dashboard {

	public static boolean isNumero(String dado) {
		if (dado.matches("[0-9]*")) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

		Locadora locadora = new Locadora("Locadora de Filmes TPII");
		int id = 0;
		int op = 0;

		do {

			String strOp = JOptionPane
					.showInputDialog("Selecione uma op��o:\n"
							+ "1 - Cadastrar cliente\n"
							+ "2 - Cadastrar filme\n"
							+ "3 - Alugar Filme\n"
							+ "4 - Visualizar estoque\n"
							+ "5 - Relat�rio de filmes alugados\n"
							+ "6 - Lista de clientes\n"
							+ "Pressione outra tecla para sair.");

			if (Dashboard.isNumero(strOp)) {
				op = Integer.parseInt(strOp);
			} else {
				System.exit(0);
			}
			switch (op) {

			case 1: {

				String nomeCli = JOptionPane.showInputDialog("Digite o nome do cliente");
				String cpf;
				do {
					cpf = JOptionPane.showInputDialog("Digite o CPF");
					if (Dashboard.isNumero(cpf)) {
						break;
					} else {
						JOptionPane.showMessageDialog(null, "CPF inv�lido.", "Aten��o!", 0);
					}
				} while (true);

				String endereco = JOptionPane.showInputDialog("Digite o endere�o");

				String dataNasc;
				do {
					dataNasc = JOptionPane.showInputDialog("Digite a data de nascimento");
					if (Dashboard.isNumero(dataNasc)) {
						break;
					} else {
						JOptionPane.showMessageDialog(null, "Data inv�lida.", "Aten��o!", 0);
					}
				} while (true);

				locadora.cadastrarCliente(nomeCli, cpf, endereco, dataNasc);
				break;
			}

			case 2: {

				String nomeFilm = JOptionPane.showInputDialog("Digite o nome do filme");
				String classificacao = JOptionPane.showInputDialog("Digite a classifica��o - Livre ou Adulto");
				String strEstoque;
				int estoque;
				do {
					strEstoque = JOptionPane.showInputDialog("informe a quantidade em estoque");
					if (Dashboard.isNumero(strEstoque)) {
						estoque = Integer.parseInt(strEstoque);
						break;

					} else {
						JOptionPane.showMessageDialog(null, "Quantidade inv�lida.", "Aten��o!", 0);
					}

				} while (true);

				locadora.cadastrarFilme(nomeFilm, classificacao, estoque);
				break;
			}

			case 3: {

				try {

					String strEstoque = "";

					int idAlugar;

					do {
						String strIdAlugar = JOptionPane.showInputDialog(
								locadora.getNome() + "\n" + "Digite o ID do filme que deseja alugar:\n\n");
						if (Dashboard.isNumero(strIdAlugar)) {
							idAlugar = Integer.parseInt(strIdAlugar);
							break;
						} else {
							JOptionPane.showMessageDialog(null, "Id inv�lido!.", "Aten��o!", 0);
						}

					} while (true);

					String cpfAlugar = JOptionPane
							.showInputDialog(locadora.getNome() + "\n" + "Digite o CPF do cliente");

					if ((locadora.encontrarCliente(cpfAlugar) != null) && (locadora.encontrarFilme(idAlugar) != null)) {
						Filme filmeAlugar = locadora.encontrarFilme(idAlugar);
						Cliente clienteAlugar = locadora.encontrarCliente(cpfAlugar);

						locadora.alugarFilme(filmeAlugar, clienteAlugar);

					} else {
						JOptionPane.showMessageDialog(null, "ID e/ou CPF incorretos.", "Aten��o!", 0);
					}

					break;
				} catch (NullPointerException e) {
					break;
				}
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
				List<Cliente> clientes = locadora.listarClientes();
				String strClientes = "";
				for (Cliente cliente : clientes) {
					strClientes += cliente.toString() + "\n";
				}
				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strClientes);
				break;
			default: {
				System.exit(0);
			}
			}

		} while (true);
	}

}
