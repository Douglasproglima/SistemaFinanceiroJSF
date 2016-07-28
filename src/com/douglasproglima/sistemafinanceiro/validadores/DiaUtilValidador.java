package com.douglasproglima.sistemafinanceiro.validadores;

import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.douglasproglima.DiaUtil")
public class DiaUtilValidador implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent componente, Object valor) throws ValidatorException {
		Calendar data = Calendar.getInstance();
		data.setTime((Date) valor);
		
		int diaSemana = data.get(Calendar.DAY_OF_WEEK);
		
		if(diaSemana == Calendar.SATURDAY && diaSemana == Calendar.SUNDAY){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data não permitida.", "Informe apenas dias úteis.");
			throw new ValidatorException(msg);
		}
	}
}