package org.locadora.controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
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
		int op = 0;

		do {

			String strOp = JOptionPane
					.showInputDialog("Selecione uma opção:\n"
							+ "1 - Cadastrar cliente\n"
							+ "2 - Cadastrar filme\n"
							+ "3 - Alugar filme\n"
							+ "4 - Visualizar filmes disponiveis\n"
							+ "5 - Visualizar estoque\n"
							+ "6 - Relatório de filmes alugados\n"
							+ "7 - Lista de clientes\n"
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
						JOptionPane.showMessageDialog(null, "CPF invï¿½lido.", "Atenção!", 0);
					}
				} while (true);

				String endereco = JOptionPane.showInputDialog("Digite o endereço");
				LocalDate dataNasc;

				do {

					DateTimeFormatter formateDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

					try {

						String data = JOptionPane.showInputDialog("Digite a data de nascimento");
						dataNasc = LocalDate.parse(data, formateDate);

						break;

					} catch (DateTimeException ex) {
						JOptionPane.showMessageDialog(null, "Data inválida.", "Atenção!", 0);

					}

				} while (true);

				locadora.cadastrarCliente(nomeCli, cpf, endereco, dataNasc);
				break;
			}

			case 2: {

				String nomeFilm = JOptionPane.showInputDialog("Digite o nome do filme");

				String[] options = new String[] { "18", "16", "14", "12", "10", "L" };

				int botoes = JOptionPane.showOptionDialog(null, "Qual a classificação indicativa de " + nomeFilm + "?",
						"\n" + "Selecione a opção desejada. ", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

				String classificacao = "";

				switch (botoes) {
				case 0:
					classificacao = "18";
					break;
				case 1:
					classificacao = "16";
					break;
				case 2:
					classificacao = "14";
					break;
				case 3:
					classificacao = "12";
					break;
				case 4:
					classificacao = "10";
					break;
				case 5:
					classificacao = "L";
					break;
				}

				String strEstoque;
				int estoque;

				do {
					strEstoque = JOptionPane.showInputDialog("informe a quantidade em estoque");
					if (Dashboard.isNumero(strEstoque)) {
						estoque = Integer.parseInt(strEstoque);
						break;

					} else {
						JOptionPane.showMessageDialog(null, "Quantidade invï¿½lida.", "Atenção!", 0);
					}

				} while (true);

				locadora.cadastrarFilme(nomeFilm, classificacao, estoque);
				break;
			}
			case 3: {
				try {

					int idAlugar;

					do {
						String strIdAlugar = JOptionPane.showInputDialog(
								locadora.getNome() + "\n" + "Digite o ID do filme que deseja alugar:\n\n");
						if (Dashboard.isNumero(strIdAlugar)) {
							idAlugar = Integer.parseInt(strIdAlugar);
							break;
						} else {
							JOptionPane.showMessageDialog(null, "Id inválido!.", "Atenção!", 0);
						}

					} while (true);

					String cpfAlugar = JOptionPane
							.showInputDialog(locadora.getNome() + "\n" + "Digite o CPF do cliente");

					if ((locadora.encontrarCliente(cpfAlugar) != null) && (locadora.encontrarFilme(idAlugar) != null)) {

						Filme filmeAlugar = locadora.encontrarFilme(idAlugar);
						Cliente clienteAlugar = locadora.encontrarCliente(cpfAlugar);

						if (locadora.isClienteElegivelParaAlugarFilme(filmeAlugar.getClassificacao(),
								locadora.calcularIdade(clienteAlugar.getDataNasc())) == true) {
							locadora.alugarFilme(filmeAlugar, clienteAlugar);
						} else {
							JOptionPane.showMessageDialog(null,
									"Cliente não tem idade suficiente para alugar esse filme", "Atenção!", 0);
						}
					} else {
						JOptionPane.showMessageDialog(null, "ID e/ou CPF incorretos.", "Atenção!", 0);
					}

					break;

				} catch (NullPointerException e) {
					e.printStackTrace();
					break;
				}
			}

			
			case 4: {

				String strEstoque = "";
				ArrayList<Filme> filmes = new ArrayList<>(locadora.getFilmes());
				Iterator<Filme> itFilmes=filmes.iterator();
				strEstoque = locadora.iteratorFilme(itFilmes);
				
				
				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strEstoque);
				break;
			}
			case 5: {

				String strEstoque = "";
				ArrayList<Filme> filmes = new ArrayList<>(locadora.getFilmes());
				for (int i = 0; i < filmes.size(); i++) {
					strEstoque = strEstoque + filmes.get(i).visualizarFilmes() + "\n";
				}
				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strEstoque);
				break;
			}


			case 6: {

				String strAlugados = "";
				List<FilmeAlugado> filmesAlugados = locadora.filmesAlugados();
				for (int i = 0; i < filmesAlugados.size(); i++) {
					strAlugados = strAlugados + filmesAlugados.get(i).visualizarFilmes() + "\n";
				}

				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strAlugados);

				break;
			}
			case 7:
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
