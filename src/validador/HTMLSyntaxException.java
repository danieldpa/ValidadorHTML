package validador;

import Pilha.PilhaLista;

public class HTMLSyntaxException extends Exception {
	
	public HTMLSyntaxException(int linha, int character, String tagFound, String tagExpected) {
		super("Tag final \"" + tagFound + "\" inesperada. Esperado \"" + tagExpected + "\" em Linha: " + linha +
				" Caractere: " + character);
	}
	
	public HTMLSyntaxException(PilhaLista<String> pilha) {
		super("Tags sem fechamento: " + pilha.toString());		
	}
}
