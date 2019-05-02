package br.edu.ufabc.alunos.model.inventory;

import br.edu.ufabc.alunos.model.Action;

public class Item implements Action{
	private Action effect;
	public final String nome;	// Identificador único do item
	public final String desc;
	public Item(String nome, String descricao, Action efeito) {
		this.nome = nome;
		this.desc = descricao;
		this.effect = efeito;
	}
	@Override
	public void startAction() {
		effect.startAction();
		
	}
	
	public String toString() {
		return String.format("%s: %s", nome, desc);
	}
	
	@Override
	public boolean equals(Object b) {
		if (b==null) {
			return false;
		}
		if(! (b instanceof Item)) {
			return false; 
		}
		Item outro = (Item)b;
		return this.nome.equals(outro.nome);
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}

	
	

}
