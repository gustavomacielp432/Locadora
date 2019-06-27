package org.locadora.view;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.locadora.controller.LocadoraController;
import org.locadora.model.Cliente;
import org.locadora.model.Filme;
import org.locadora.model.FilmeAlugado;
import org.locadora.model.Locadora;

public class Dashboard {

	private static final int OP_FILMES_DISPONIVEIS = 0;
	private static final String TIPO_CLIENTE = "Cliente";
	private static final String TIPO_FILME = "Filme";
	private static final String TIPO_FILME_ALUGADO = "FilmeAlugado";
	
	private static Locadora locadora = Locadora.getInstance();

	public static void main(String[] args) {
		
		LocadoraController locadoraController = new LocadoraController();

		int op = 0;

		do {

			String strOp = JOptionPane
					.showInputDialog("Selecione uma op��o:\n"
							+ "1 - Cadastrar cliente\n"
							+ "2 - Cadastrar filme\n"
							+ "3 - Alugar filme\n"
							+ "4 - Visualizar filmes disponiveis\n"
							+ "5 - Visualizar estoque\n"
							+ "6 - Relat�rio de filmes alugados\n"
							+ "7 - Lista de clientes\n"
							+ "Pressione outra tecla para sair.");
			if (Dashboard.isNumero(strOp)) {
				op = Integer.parseInt(strOp);
			} else {
				System.exit(0);
			}
			switch (op) {

			case 1: {
				ArrayList<Cliente> cliente = new ArrayList<>(locadoraController.getClientes());
				Iterator<Cliente> iterator = cliente.iterator();

				String nomeCli = JOptionPane.showInputDialog("Digite o nome do cliente");
				String cpf;
				do {

					cpf = JOptionPane.showInputDialog("Digite o CPF");
					if (locadoraController.validarDuplicidadeCpf(iterator, cpf) == true) {
						JOptionPane.showMessageDialog(null, "CPF j� cadastrado.", "Aten��o!", 0);
					} else {
						if (Dashboard.isNumero(cpf)) {
							break;
						} else {
							JOptionPane.showMessageDialog(null, "CPF inv�lido.", "Aten��o!", 0);
						}
					}
					;

				} while (true);

				String endereco = JOptionPane.showInputDialog("Digite o endere�o");
				LocalDate dataNasc;

				do {

					DateTimeFormatter formateDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

					try {

						String data = JOptionPane.showInputDialog("Digite a data de nascimento");
						dataNasc = LocalDate.parse(data, formateDate);

						break;

					} catch (DateTimeException ex) {
						JOptionPane.showMessageDialog(null, "Data inv�lida.", "Aten��o!", 0);

					}

				} while (true);

				locadoraController.cadastrarCliente(nomeCli, cpf, endereco, dataNasc);
				break;
			}

			case 2: {

				String nomeFilm = JOptionPane.showInputDialog("Digite o nome do filme");

				String[] options = new String[] { "18", "16", "14", "12", "10", "L" };

				int botoes = JOptionPane.showOptionDialog(null, "Qual a classifica��o indicativa de " + nomeFilm + "?",
						"\n" + "Selecione a op��o desejada. ", JOptionPane.DEFAULT_OPTION,
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
						JOptionPane.showMessageDialog(null, "Quantidade inv�lida.", "Aten��o!", 0);
					}

				} while (true);

				locadoraController.cadastrarFilme(nomeFilm, classificacao, estoque);
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
							JOptionPane.showMessageDialog(null, "Id inv�lido!.", "Aten��o!", 0);
						}

					} while (true);

					String cpfAlugar = JOptionPane
							.showInputDialog(locadora.getNome() + "\n" + "Digite o CPF do cliente");

					if ((locadoraController.encontrarCliente(cpfAlugar) != null) && (locadoraController.encontrarFilme(idAlugar) != null)) {

						Filme filmeAlugar = locadoraController.encontrarFilme(idAlugar);
						Cliente clienteAlugar = locadoraController.encontrarCliente(cpfAlugar);

						if (locadoraController.isClienteElegivelParaAlugarFilme(filmeAlugar.getClassificacao(),
								locadoraController.calcularIdade(clienteAlugar.getDataNasc())) == true) {
							locadoraController.alugarFilme(filmeAlugar, clienteAlugar);
						} else {
							JOptionPane.showMessageDialog(null,
									"Cliente n�o tem idade suficiente para alugar esse filme", "Aten��o!", 0);
						}
					} else {
						JOptionPane.showMessageDialog(null, "ID e/ou CPF incorretos.", "Aten��o!", 0);
					}

					break;

				} catch (NullPointerException e) {
					e.printStackTrace();
					break;
				}
			}

			case 4: {

				String strEstoque = "";
				ArrayList<Filme> filmes = new ArrayList<>(locadoraController.getFilmes());
				Iterator<Filme> itFilmes = filmes.iterator();
				strEstoque = locadoraController.visualizaSelecionados(itFilmes, OP_FILMES_DISPONIVEIS);

				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strEstoque);
				break;
			}
			case 5: {

				String strEstoque = "";
				ArrayList<Filme> filmes = new ArrayList<>(locadoraController.getFilmes());
				Iterator<Filme> iterator = filmes.iterator();
				strEstoque = locadoraController.visualizaTodos(iterator, TIPO_FILME);

				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strEstoque);
				break;
			}

			case 6: {

				String strAlugados = "";
				List<FilmeAlugado> filmesAlugados = locadoraController.getFilmesAlugados();
				Iterator<FilmeAlugado> iterator = filmesAlugados.iterator();
				strAlugados = locadoraController.visualizaTodos(iterator, TIPO_FILME_ALUGADO);

				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strAlugados);

				break;
			}
			case 7:
				List<Cliente> clientes = locadoraController.getClientes();
				String strClientes = "";
				Iterator<Cliente> iterator = clientes.iterator();
				strClientes = locadoraController.visualizaTodos(iterator, TIPO_CLIENTE);

				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strClientes);
				break;
			default: {
				System.exit(0);
			}
			}

		} while (true);
	}

}
