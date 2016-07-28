package com.douglasproglima.sistemafinanceiro.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	
	//Vai ter apenas uma única fabrica de sessão
	private static final SessionFactory fabricaSessao;
	
	static{
		try {
			//Instânciando a class Configuration para representar uma configuração do hibernate
			Configuration configuracao = new Configuration();
			
			//Vai ler o arquivo hibernate.cfg.xml
			configuracao.configure();
			
			// passa as configurações do hibernate através do objeto configuracao
			ServiceRegistry servicoRegistro =  new ServiceRegistryBuilder().applySettings(configuracao.getProperties()).buildServiceRegistry();
				
			//O método buildSessionFactory irá buscar uma fabrica de sessão que por final será atribuido a váriável fabricaSessao
			fabricaSessao = configuracao.buildSessionFactory(servicoRegistro);	
		} catch (Throwable erro) {
			throw new ExceptionInInitializerError("Na inicialização da class Fabrica de Sessão ocorreu o seguinte erro: " + erro);
		}
	}
	
	//Abre uma conexão com o banco em formato de sessão
	public static Session getSesao(){
		
		return fabricaSessao.openSession();
	}
}