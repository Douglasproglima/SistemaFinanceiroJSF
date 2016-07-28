package com.douglasproglima.sistemafinanceiro.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class EstoqueBean{
	private ItemEstoqueBean itemEstoqueBean = new ItemEstoqueBean();
	private List<ItemEstoqueBean> itensEstoque = new ArrayList<ItemEstoqueBean>();
	

	//Métodos Getters
	public ItemEstoqueBean getItemEstoqueBean() {
		return itemEstoqueBean;
	}
	
	public List<ItemEstoqueBean> getItensEstoque() {
		return itensEstoque;
	}
	
	//Métodos Funcionais
	public void incluir(){
		this.itensEstoque.add(this.itemEstoqueBean);
		
		//Instância um novo itemEstoque apenas para limpar o grid
		this.itemEstoqueBean = new ItemEstoqueBean();
	}

	public void limpar(){
		this.itensEstoque.clear();
	}
}