import java.util.ArrayList;

public class Cliente {
	
	private String nome;
	private String cpf;
	private String endereco;
	private String dataNasc;
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	public Cliente(String nome, String cpf, String endereco, String dataNasc) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.dataNasc = dataNasc;
	}
	
	public Cliente() {
		
	}
	
	public int encontrarCliente(String cpf) {
		for(int i = 0; i < clientes.size(); i++) {
			if(clientes.get(i).getCpf().equals(cpf)) {
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
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
}
