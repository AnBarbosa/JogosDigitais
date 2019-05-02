package br.edu.ufabc.alunos.model.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Inventory {

	private static Map<String, Stack<Item>> itens;
	
	public Inventory() {
		this.itens = new HashMap<>();
	}
	
	
	public static void add(Item item, int unidades) {
		for(int i = 0; i<unidades; i++) {
			add(item);
		}
	}
	
	public static void add(Item item) {
		Stack<Item> lista = itens.getOrDefault(item.nome,new Stack<>());
		lista.add(item);
		itens.put(item.nome, lista);
	}
	
	public static boolean use(String nome) {
		if(itens.containsKey(nome)) {
			Stack<Item> pilha = itens.get(nome);
			if(pilha.empty()) {
				return false;
			}
			Item item = pilha.pop();
			item.startAction();
			return true;
		}
		return false;
	}
}
