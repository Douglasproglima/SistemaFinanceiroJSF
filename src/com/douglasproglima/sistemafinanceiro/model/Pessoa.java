package com.douglasproglima.sistemafinanceiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="pessoa")
public class Pessoa implements Serializable{
	@Column(name="codigo", nullable=false)
	@Id
	@GeneratedValue
	private Integer codigo;
	
	@Column(name="nome", length=100, nullable=false)
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

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
}
