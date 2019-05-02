import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Locadora {
	
	private String nome;
	private ArrayList<FilmeAlugado> filmesAlugados = new ArrayList<FilmeAlugado>();

	public Locadora(String nome) {
		this.nome = nome;
	}
	
	public void alugarFilme(Filme filme, Cliente cliente, String nomeCli, String cpf, int id, String nomeFilme) {
		
		if(cliente.encontrarCliente(cpf) != -1) {
			
			if(filme.encontrarFilme(id) != -1) {
				
				if(filme.verificaEstoque(id) > 0) {
					
					filme.diminuirEstoque(id);
					FilmeAlugado filmeAlugado = new FilmeAlugado(id, nomeFilme, nomeCli, cpf);
					filmesAlugados.add(filmeAlugado);
					JOptionPane.showMessageDialog(null, "Filme alugado com sucesso.", "Atenção!", 1);
				
				}else {
					JOptionPane.showMessageDialog(null, "Estoque insuficiente.", "Atenção!", 0);
				}
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "CPF não cadastrado.", "Atenção!", 0);
		}
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
