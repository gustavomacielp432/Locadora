import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FilmeAlugado extends BaseEntity{
	
	private static final long serialVersionUID = -5664509326437291197L;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "filme_id")
	private Filme filme;
	
	
	public FilmeAlugado() {
		
	}
	
	public FilmeAlugado(Cliente cliente, Filme filme) {
		this.cliente = cliente;
		this.filme = filme;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Filme getFilme() {
		return filme;
	}


	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	
	public String visualizarFilmes() {
		return cliente.toString()+"| "+filme.visualizarFilmes();
	}
	
}