package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Pessoa;

public class GestaoPessoas {
	private static Map<Integer, Pessoa> mapPessoas = new HashMap<Integer, Pessoa>();
	
	static{
		mapPessoas.put(1, new Pessoa(1, "Douglas Lima"));
		mapPessoas.put(2, new Pessoa(2, "Maria das Dores"));
		mapPessoas.put(3, new Pessoa(3, "João da Padaria"));
	}
	
	public List<Pessoa> exibirTodasPessoas(){
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas.addAll(pessoas);
		
		return pessoas;
	}
	
	public Pessoa filtrarPorCodigo(Integer codigo){
		return mapPessoas.get(codigo);
	}
}