import java.util.ArrayList;

import javax.swing.JOptionPane;

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

			String strOp = JOptionPane.showInputDialog("Selecione uma opção:\n1 - Cadastrar cliente\n2 - "
					+ "Cadastrar filme\n3 - Alugar Filme\n" + "4 - Visualizar estoque\n5 - Relatório de "
					+ "filmes alugados\nPressione outra tecla para sair.");

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
						JOptionPane.showMessageDialog(null, "CPF inválido.", "Atenção!", 0);
					}
				} while (true);

				String endereco = JOptionPane.showInputDialog("Digite o endereço");
				String dataNasc;
				do {
					dataNasc = JOptionPane.showInputDialog("Digite a data de nascimento");
					if (Dashboard.isNumero(dataNasc)) {
						break;
					} else {
						JOptionPane.showMessageDialog(null, "Data inválida.", "Atenção!", 0);
					}
				} while (true);

				locadora.cadastrarCliente(nomeCli, cpf, endereco, dataNasc);
				break;
			}

			case 2: {

				try {
					String nomeFilm = JOptionPane.showInputDialog("Digite o nome do filme");
					String classificacao = JOptionPane.showInputDialog("Digite a classificação - Livre ou Adulto");
					String strEstoque;
					int estoque;

					do {
						strEstoque = JOptionPane.showInputDialog("informe a quantidade em estoque");
						if (Dashboard.isNumero(strEstoque)) {
							estoque = Integer.parseInt(strEstoque);
							break;

						} else {
							JOptionPane.showMessageDialog(null, "Quantidade inválida.", "Atenção!", 0);
						}

					} while (true);

					id = id + 1;
					locadora.cadastrarFilme(id, nomeFilm, classificacao, estoque);
					break;

				} catch (NullPointerException e) {
					break;
				}

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
							JOptionPane.showMessageDialog(null, "Id inválido!.", "Atenção!", 0);
						}

					} while (true);

					String cpfAlugar = JOptionPane
							.showInputDialog(locadora.getNome() + "\n" + "Digite o CPF do cliente");

					if ((locadora.encontrarCliente(cpfAlugar) != -1) && (locadora.encontrarFilme(idAlugar) != -1)) {
						Filme filmeAlugar = locadora.retornaFilme(idAlugar);
						Cliente clienteAlugar = locadora.retornaCliente(cpfAlugar);

						locadora.alugarFilme(filmeAlugar, clienteAlugar);
					} else {
						JOptionPane.showMessageDialog(null, "ID e/ou CPF incorretos.", "Atenção!", 0);
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
				ArrayList<FilmeAlugado> filmesAlugados = new ArrayList<>(locadora.getFilmesAlugados());
				for (int i = 0; i < filmesAlugados.size(); i++) {
					strAlugados = strAlugados + filmesAlugados.get(i).visualizarFilmes() + "\n";
				}

				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strAlugados);

				break;
			}

			default: {
				System.exit(0);
			}
			}
		} while (true);
	}

}
