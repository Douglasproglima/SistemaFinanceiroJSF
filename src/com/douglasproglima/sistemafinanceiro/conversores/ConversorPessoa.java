package com.douglasproglima.sistemafinanceiro.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.Session;

import com.douglasproglima.sistemafinanceiro.model.Pessoa;
import com.douglasproglima.sistemafinanceiro.util.FacesUtil;

//Este conversor não precisa espeficicar na página, porque estamos falando que ele irá sempre ser utilizado pela class pessoa
@FacesConverter(forClass=Pessoa.class)
public class ConversorPessoa implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent componente, String valor) {
		Pessoa retornoPessoa = null;
		
		if(valor != null){
//			GestaoPessoas gestaoPessoas = new GestaoPessoas();
//			retornoPessoa = gestaoPessoas.filtrarPorCodigo(new Integer(valor));
			
			Session sessao = (Session) FacesUtil.getAtributosDaRequisicao("atributoSessaoDoFilter");
			
			/*A diferença entre o session.load() e session.get():
			 * session.load(): O load não busca a informação diretamente no banco, ele retorna um proxy 
			 * 				   (especie de um objeto que vai encapsular a class X, vc terá os dados apenas na primeiro vez que utilizar)
			 * session.get().: Retorna na hora o objeto caso o mesmo tenha no banco de dados, no caso seria a o 
			 * 				   parâmetro nome que representa o nome da class pessoa.
			*/
			
//			retornoPessoa = (Pessoa) sessao.load(Pessoa.class, new Integer(valor));
			retornoPessoa = (Pessoa) sessao.get(Pessoa.class, new Integer(valor));
		}
		
		return retornoPessoa;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente, Object valor) {
		if (valor != null) {
			return ((Pessoa) valor).getCodigo().toString();	
		}
		
		return null;
	}
}