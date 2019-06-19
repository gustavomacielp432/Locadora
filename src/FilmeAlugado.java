public class FilmeAlugado {

	private int id;
	private String nomeFilme;
	private String nomeCli;
	private String cpf;

	public FilmeAlugado(int id, String nomeFilme, String nomeCli, String cpf) {
		super();
		this.id = id;
		this.nomeFilme = nomeFilme;
		this.nomeCli = nomeCli;
		this.cpf = cpf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeFilme() {
		return nomeFilme;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}

	public String getNomeCli() {
		return nomeCli;
	}

	public void setNomeCli(String nomeCli) {
		this.nomeCli = nomeCli;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String visualizarFilmes() {
		return "ID: " + id + " | " + "Nome: " + nomeFilme + " | " + "Nome do cliente: " + nomeCli + " | " + "CPF: "
				+ cpf;
	}

}