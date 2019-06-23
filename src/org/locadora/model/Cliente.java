package org.locadora.model;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Cliente extends BaseEntity{
	
	
	private static final long serialVersionUID = 6206414649155262471L;

	private String nome;

	@Column(name="cpf", unique=true)
	private String cpf;
	private String endereco;
	private String dataNasc;
	
	
	public Cliente(String nome, String cpf, String endereco, String dataNasc) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.dataNasc = dataNasc;
	}
	
	public Cliente() {
		
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

	@Override
	public String toString() {
		return "id=" + super.getId() + "| nome=" + nome + "| cpf=" + cpf + "| endereco=" + endereco + "| dataNasc="
				+ dataNasc ;
	}
	
	
}