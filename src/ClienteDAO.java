import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ClienteDAO extends GenericDAO< Cliente, Integer>{
	
	public ClienteDAO() {
       super(Cliente.class);
    }  
	public void salvarCliente(Cliente cliente) {
		super.salvar(cliente);
	}
	
	public Cliente buscarClientePorCPF(String cpf) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(Cliente.class);
		criteria.add(Restrictions.eq("cpf", cpf));
		return (Cliente) criteria.uniqueResult();
	}
	
	
	
}
