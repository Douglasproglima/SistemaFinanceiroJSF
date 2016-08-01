package com.douglasproglima.sistemafinanceiro.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	//Método personalizado para gerar mensagens na tela.
	public static void adicionarMensagem(Severity tipoMensagem, String msgResumida, String msgComDetalhes){
		FacesContext.getCurrentInstance().addMessage(null, 
		new FacesMessage(tipoMensagem, msgResumida, msgComDetalhes));
	}
}
