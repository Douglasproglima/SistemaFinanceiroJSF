package com.douglasproglima.sistemafinanceiro.conversores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.douglasproglima.sistemafinanceiro.conversores.conversordatapersonalizado")
public class ConversorDataPersonalizado implements Converter{

	private static final DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
	
	private Date getDateHoje(){
		Calendar dataHoje =  Calendar.getInstance();
		dataHoje.set(Calendar.HOUR, 0);
		dataHoje.set(Calendar.MINUTE, 0);
		dataHoje.set(Calendar.SECOND, 0);
		dataHoje.set(Calendar.MILLISECOND, 0);
		
		return dataHoje.getTime();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent componente, String valor) {
		 Date dataConvertida = null;
		 
		 if(valor != null && valor.equals("")){
			 if(valor.equalsIgnoreCase("hoje")){
				 dataConvertida = this.getDateHoje();
			 }else{
				try {
					dataConvertida =  formatador.parse(valor);
				} catch (ParseException e) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data Incorreta", "Informe uma data válida.");
					throw new ConverterException(msg);
				}
			 }
		 }
		
		return dataConvertida;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente, Object valor) {
		if(this.getDateHoje().equals(valor)){
			return "Hoje - " + this.getDateHoje();
		}else{
			return formatador.format((Date) valor);
		}
	}
}