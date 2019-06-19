import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Locadora {

	private String nome;
	private ArrayList<FilmeAlugado> filmesAlugados = new ArrayList<FilmeAlugado>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private ArrayList<Filme> filmes = new ArrayList<Filme>();

	public Locadora(String nome) {
		this.nome = nome;
	}

	public void alugarFilme(Filme filme, Cliente cliente) {

		if (filme != null && filme.getId() != -1 && cliente != null && !cliente.getCpf().isEmpty()) {
			if (filme.getEstoque() > 0) {
				filme.diminuirEstoque();
				FilmeAlugado filmeAlugado = new FilmeAlugado(filme.getId(), filme.getNome(), cliente.getNome(),
						cliente.getCpf());
				filmesAlugados.add(filmeAlugado);
				JOptionPane.showMessageDialog(null, "Filme alugado com sucesso.", "Atenção!", 1);

			} else {
				JOptionPane.showMessageDialog(null, "Estoque insuficiente.", "Atenção!", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "CPF não cadastrado.", "Atenção!", 0);
		}
	}

	public int encontrarCliente(String cpf) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCpf().equals(cpf)) {
				return i;
			}
		}
		return -1;
	}

	public Cliente retornaCliente(String cpf) {
		return clientes.get(encontrarCliente(cpf));
	}

	public void cadastrarCliente(String nome, String cpf, String endereco, String dataNasc) {
		Cliente cliente = new Cliente(nome, cpf, endereco, dataNasc);
		clientes.add(cliente);
	}

	public void cadastrarFilme(int id, String nome, String classificacao, int estoque) {
		Filme filme = new Filme(id, nome, classificacao, estoque);
		System.out.println(id + "|" + nome + "|" + classificacao + "|" + estoque);
		filmes.add(filme);
	}

	public int encontrarFilme(int id) {
		for (int i = 0; i < filmes.size(); i++) {
			if (filmes.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	public ArrayList<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(ArrayList<Filme> filmes) {
		this.filmes = filmes;
	}

	public Filme retornaFilme(int id) {
		return filmes.get(encontrarFilme(id));
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<FilmeAlugado> getFilmesAlugados() {
		return filmesAlugados;
	}

	public void setFilmesAlugados(ArrayList<FilmeAlugado> filmesAlugados) {
		this.filmesAlugados = filmesAlugados;
	}

}
