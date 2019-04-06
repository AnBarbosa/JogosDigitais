package br.edu.ufabc.alunos.utils;

public class Log {
	public static boolean debug = false;
	
	public static void println(String s) {
		if(debug) {
			System.out.println(s);
		}
	}
	
	public static void printf(String s, Object... objs){
		if(debug) {
			System.out.printf(s, objs);
		}
	}
}
