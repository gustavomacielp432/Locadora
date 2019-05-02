import java.util.ArrayList;

import javax.swing.JOptionPane;

/*
 * Andreia Letícia de Faria
 * 117114337
 */

public class Dashboard {

	public static void main(String[] args) {

		Filme filme = new Filme();
		Cliente cliente = new Cliente();
		Locadora locadora = new Locadora("Locadora de Filmes TPII");
		int id = 0;		

		int op = Integer.parseInt(JOptionPane.showInputDialog(
				"Selecione uma opção:\n1 - Cadastrar cliente\n2 - Cadastrar filme\n3 - Alugar Filme\n"
				+ "4 - Visualizar estoque\n5 - Relatório de filmes alugados\nPressione outra tecla para sair."));

		do {
			switch (op) {

			case 1: {

				String nomeCli = JOptionPane.showInputDialog("Digite o nome do cliente");
				String cpf = JOptionPane.showInputDialog("Digite o CPF");
				String endereco = JOptionPane.showInputDialog("Digite o endereço");
				String dataNasc = JOptionPane.showInputDialog("Digite a data de nascimento");
				cliente.cadastrarCliente(nomeCli, cpf, endereco, dataNasc);
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
				id = id + 1;
				filme.cadastrarFilme(id,nomeFilm, classificacao, estoque);
				break;		
			}

			case 3: {
				
				String strEstoque = "";
				ArrayList<Filme> filmes = new ArrayList<>(filme.getFilmes());
				for (int i = 0; i < filmes.size(); i++) {
					strEstoque = strEstoque + filmes.get(i).visualizarFilmes() + "\n";
				}
				
				int idAlugar = Integer.parseInt(JOptionPane.showInputDialog(locadora.getNome() + "\n\n" + strEstoque 
						+ "\n" + "Digite o ID do filme que deseja alugar:\n\n"));

				String cpfAlugar = JOptionPane.showInputDialog(locadora.getNome() + "\n" + "Digite o CPF do cliente");
				if((cliente.encontrarCliente(cpfAlugar) != -1) && (filme.encontrarFilme(idAlugar) != -1)) {
					String nomeCli = cliente.retornaCliente(cpfAlugar).getNome();
					String nomeFilme = filme.retornaFilme(idAlugar).getNome();
					locadora.alugarFilme(filme, cliente, nomeCli, cpfAlugar, idAlugar, nomeFilme);
				}else {
					JOptionPane.showMessageDialog(null, "ID e/ou CPF incorretos.", "Atenção!", 0);
				}		
					
				break;
			}

			case 4: {
				
				String strEstoque = "";
				ArrayList<Filme> filmes = new ArrayList<>(filme.getFilmes());
				for (int i = 0; i < filmes.size(); i++) {
					strEstoque = strEstoque + filmes.get(i).visualizarFilmes() + "\n";
				}
				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strEstoque);
				break;
			}
			
			case 5:{
				
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
			
			op = Integer.parseInt(JOptionPane.showInputDialog(
					"Selecione uma opção:\n1 - Cadastrar cliente\n2 - Cadastrar filme\n3 - Alugar Filme\n"
					+ "4 - Visualizar estoque\n5 - Relatório de filmes alugados\nPressione outra tecla para sair."));
		
		} while (true);
	}

}
