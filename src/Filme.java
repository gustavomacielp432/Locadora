import java.util.ArrayList;

public class Filme {
	
	private
	int id;
	private String nome;
	private String classificacao;
	private int estoque;
	private static ArrayList<Filme> filmes = new ArrayList<Filme>();
	
	public Filme(int id, String nome, String classificacao, int estoque) {
		this.id = id;
		this.nome = nome;
		this.classificacao = classificacao;
		this.estoque = estoque;
	}
	
	public Filme() {
		
	}
	
	public void cadastrarFilme(int id, String nome, String classificacao, int estoque) {
		Filme filme = new Filme(id, nome, classificacao, estoque);
		filmes.add(filme);		
	}
	
	public int encontrarFilme(int id) {
		for(int i = 0; i < filmes.size(); i++) {
			if(filmes.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	public void diminuirEstoque(int id) {
		filmes.get(encontrarFilme(id)).setEstoque(getEstoque() - 1);
	}
	
	public int verificaEstoque(int id) {
		return filmes.get(encontrarFilme(id)).getEstoque();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public ArrayList<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(ArrayList<Filme> filmes) {
		Filme.filmes = filmes;
	}
	
	public Filme retornaFilme(int id) {
		return filmes.get(encontrarFilme(id));
	}

	public String visualizarFilmes() {
		return "ID: " + id + " | " + "Nome: " + nome + " | " + "Qtd: " + estoque + " | " +  "Class.: " + classificacao;
	}
	
	
	
	
}
