package model;

import java.io.Serializable;

public class Pessoa implements Serializable{
	private Integer codigo;
	private String nome;
	
	//Construtor default
	public Pessoa(){
		
	}
	
	//Construtor com sobrecarga de parâmetros
	public Pessoa(Integer codigo, String nome){
		this.codigo = codigo;
		this.nome = nome;
	}
	
	//Métodos getters e setters Bean
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
