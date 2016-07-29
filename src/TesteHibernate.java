

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.douglasproglima.sistemafinanceiro.model.Pessoa;
import com.douglasproglima.sistemafinanceiro.util.HibernateUtil;

public class TesteHibernate {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		Session sessao = HibernateUtil.getSessao();
		
		List<Pessoa> pessoas = sessao.createCriteria(Pessoa.class).add(Restrictions.gt("codigo", 2)).list();
		
		for(Pessoa p : pessoas){
			System.out.println("Nome: " + p.getNome());
		}
		
		sessao.close();
	}
}