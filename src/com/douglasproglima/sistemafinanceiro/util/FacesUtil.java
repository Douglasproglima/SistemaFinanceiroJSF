package com.douglasproglima.sistemafinanceiro.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class FacesUtil {
	
	//Método personalizado para gerar mensagens na tela.
	public static void adicionarMensagem(Severity tipoMensagem, String msgResumida, String msgComDetalhes){
		FacesContext.getCurrentInstance().addMessage(null, 
		new FacesMessage(tipoMensagem, msgResumida, msgComDetalhes));
	}
	
	public static Object getAtributosDaRequisicao(String nomeAtributoSessaoDoFilter){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		//A partir do contexto externo, consegue pegar o escopo de requisição
		ExternalContext externalContext = facesContext.getExternalContext();  
		
		//Criando um objeto da API do Servlet
		HttpServletRequest requisicao = (HttpServletRequest) externalContext.getRequest();
		
		//Todo o procedimento acima necessário para buscar o objeto request da Class
		return requisicao.getAttribute(nomeAtributoSessaoDoFilter);
	}
}
