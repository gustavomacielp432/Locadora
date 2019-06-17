public class Filme {

	private int id;
	private String nome;
	private String classificacao;
	private int estoque;

	public Filme(int id, String nome, String classificacao, int estoque) {
		this.id = id;
		this.nome = nome;
		this.classificacao = classificacao;
		this.estoque = estoque;
	}

	public Filme() {

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

	public void diminuirEstoque() {
		this.estoque--;
	}

	public String visualizarFilmes() {
		return "ID: " + id + " | " + "Nome: " + nome + " | " + "Qtd: " + estoque + " | " + "Class.: " + classificacao;
	}
}
