package org.locadora.view;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.locadora.controller.BaseController;
import org.locadora.controller.ClienteController;
import org.locadora.controller.FilmeController;
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
		ClienteController clienteController = new ClienteController();
		FilmeController filmeController = new FilmeController();
		
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
			
			if (BaseController.isNumero(strOp)) {
				op = Integer.parseInt(strOp);
			} else {
				System.exit(0);
			}
			switch (op) {

			case 1: {
				ArrayList<Cliente> cliente = new ArrayList<>(clienteController.getClientes());
				Iterator<Cliente> iterator = cliente.iterator();

				String nomeCli = JOptionPane.showInputDialog("Digite o nome do cliente");
				String cpf;
				do {

					cpf = JOptionPane.showInputDialog("Digite o CPF");
					if (clienteController.validarDuplicidadeCpf(iterator, cpf) == true) {
						JOptionPane.showMessageDialog(null, "CPF já cadastrado.", "Atenção!", 0);
					} else {
						if (BaseController.isNumero(cpf)) {
							break;
						} else {
							JOptionPane.showMessageDialog(null, "CPF inválido.", "Atenção!", 0);
						}
					}
					;

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

				clienteController.cadastrarCliente(nomeCli, cpf, endereco, dataNasc);
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
					if (BaseController.isNumero(strEstoque)) {
						estoque = Integer.parseInt(strEstoque);
						break;

					} else {
						JOptionPane.showMessageDialog(null, "Quantidade inválida.", "Atenção!", 0);
					}

				} while (true);

				filmeController.cadastrarFilme(nomeFilm, classificacao, estoque);
				break;
			}
			case 3: {
				try {

					int idAlugar;

					do {
						String strIdAlugar = JOptionPane.showInputDialog(
								locadora.getNome() + "\n" + "Digite o ID do filme que deseja alugar:\n\n");
						if (BaseController.isNumero(strIdAlugar)) {
							idAlugar = Integer.parseInt(strIdAlugar);
							break;
						} else {
							JOptionPane.showMessageDialog(null, "Id inválido!.", "Atenção!", 0);
						}

					} while (true);

					String cpfAlugar = JOptionPane
							.showInputDialog(locadora.getNome() + "\n" + "Digite o CPF do cliente");

					if ((clienteController.encontrarCliente(cpfAlugar) != null)
							&& (filmeController.encontrarFilme(idAlugar) != null)) {

						Filme filmeAlugar = filmeController.encontrarFilme(idAlugar);
						Cliente clienteAlugar = clienteController.encontrarCliente(cpfAlugar);

						if (clienteController.isClienteElegivelParaAlugarFilme(filmeAlugar.getClassificacao(),
								clienteController.calcularIdade(clienteAlugar.getDataNasc())) == true) {
							filmeController.alugarFilme(filmeAlugar, clienteAlugar);
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
				ArrayList<Filme> filmes = new ArrayList<>(filmeController.getFilmes());
				Iterator<Filme> itFilmes = filmes.iterator();
				strEstoque = locadoraController.visualizaSelecionados(itFilmes, OP_FILMES_DISPONIVEIS);

				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strEstoque);
				break;
			}
			case 5: {

				String strEstoque = "";
				ArrayList<Filme> filmes = new ArrayList<>(filmeController.getFilmes());
				Iterator<Filme> iterator = filmes.iterator();
				strEstoque = locadoraController.visualizaTodos(iterator, TIPO_FILME);

				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strEstoque);
				break;
			}

			case 6: {

				String strAlugados = "";
				List<FilmeAlugado> filmesAlugados = filmeController.getFilmesAlugados();
				Iterator<FilmeAlugado> iterator = filmesAlugados.iterator();
				strAlugados = locadoraController.visualizaTodos(iterator, TIPO_FILME_ALUGADO);

				JOptionPane.showMessageDialog(null, locadora.getNome() + "\n\n" + strAlugados);

				break;
			}
			case 7:
				List<Cliente> clientes = clienteController.getClientes();
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
