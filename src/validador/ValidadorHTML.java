package validador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ListaEncadeada.ListaEncadeada;
import Pilha.PilhaLista;

public class ValidadorHTML {
	private PilhaLista<String> pilhaTags;
	private ListaEncadeada<String> tags;

	public void lerArquivo(String caminhoArquivo) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
			String linha;
			int numeroLinha = 1;
			while ((linha = reader.readLine()) != null) {
				linha = linha.trim().toLowerCase();
				if (!linha.isBlank()) {
					//Falta implementar
				}
				numeroLinha++;
			}
		}
	}
	
	//será privado
	public ListaEncadeada<String> extrairTags(String linha) {
	    ListaEncadeada<String> tags = new ListaEncadeada();
	    int inicio = 0;
	    while ((inicio = linha.indexOf('<', inicio)) != -1) {
	        int fim = linha.indexOf('>', inicio);
	        if (fim != -1) {
	            String tag = linha.substring(inicio + 1, fim).split("\\s")[0];
	            tags.inserir(tag);
	            inicio = fim + 1;
	        } else {
	            break;
	        }
	    }
	    return tags;
	}

	public static void main(String[] args) throws IOException {
		ValidadorHTML analisador = new ValidadorHTML();
		
		String caminhoArquivo;
	    
	    if (args.length > 0) {
	        
	        caminhoArquivo = args[0];
	    } else {
	        
	        caminhoArquivo = "src//texto.txt";
	    }
	    
	    analisador.lerArquivo(caminhoArquivo);
	}

}