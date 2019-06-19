
public class FilmeDAO extends GenericDAO<Filme, Integer> {
	public FilmeDAO() {
       super(Filme.class);
    } 
	
	public void diminuirEstoque(Filme filme) {
		filme.setEstoque(filme.getEstoque()-1);
		super.atualizar(filme);
		
	}
}
