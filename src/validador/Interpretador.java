package validador;

import ListaEncadeada.ListaEncadeada;
import Pilha.PilhaLista;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Interpretador {
	private String file;
	private ListaEncadeada<Tag> tags;
	private PilhaLista<String> pilha;
	private boolean valido;
	
	public Interpretador(String path) throws IOException, HTMLSyntaxException {
		pilha = new PilhaLista<String>();
		tags = new ListaEncadeada<Tag>();
		analisar(path);
	}
	
	private void lerArquivo(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		file = new String(encoded, Charset.defaultCharset());
	}
	
	private void analisar(String path) throws IOException, HTMLSyntaxException {
		lerArquivo(path);
		lerTodasTags();
		
		valido = true;
	}
	
	private boolean isSingleton(String tagName) {
		switch (tagName) {
			case "meta": {return true;}
			case "base": {return true;}
			case "br": {return true;}
			case "col": {return true;}
			case "command": {return true;}
			case "embed": {return true;}
			case "hr": {return true;}
			case "img": {return true;}
			case "input": {return true;}
			case "link": {return true;}
			case "param": {return true;}
			case "source": {return true;}
			case "!DOCTYPE": {return true;}
			default:
				return false;
		}
	}
	
	private void lerTodasTags() throws HTMLSyntaxException {
		boolean foundTagInit = false;
		boolean foundTagClose = false;
		boolean hasAttributes = false;
		String tagName = "";
		
		int newLines = 1;
		int characters = 0;
		
		for(int i = 0; i < file.length(); i++) {
			if ("\n".equals(file.substring(i, i + 1))) {
				newLines++;
				characters = 0;
			}else {
				characters++;
				if (foundTagInit) {
					if (">".equals(file.substring(i, i + 1))) {
						if (foundTagClose) {
							String ultimaTag = pilha.peek();
							
							if (ultimaTag.equals(tagName)) {
								pilha.pop();
							}
							else {
								throw new HTMLSyntaxException(newLines, characters, tagName, ultimaTag);
							}
							
							foundTagClose = false;
						}else {
							if (!isSingleton(tagName)) {
								pilha.push(tagName);
							}
		
							boolean achou = false;
							for(int j = 0; j < tags.obterComprimento(); j++) {
								Tag no = tags.obterNo(j).getInfo();
								if (tagName.equals(no.getNome())) {
									no.setCount(no.getCount() + 1);
									achou = true;
								}
							}
							
							if (!achou) {
								tags.inserir(new Tag(tagName));
							}
						}
						foundTagInit = false;
						hasAttributes = false;
					}
					else if ("/".equals(file.substring(i, i + 1))) {
						foundTagClose = true;
					}
					
					else if (" ".equals(file.substring(i, i + 1))) {
						hasAttributes = true;
					}
						
					else if (!hasAttributes){
						tagName += file.substring(i, i + 1);
					}
				}else {
					if ("<".equals(file.substring(i, i + 1))) {
						foundTagInit = true;
						tagName = "";
					}
				}
			}
		}
		
		if (!pilha.estaVazia()) {
			throw new HTMLSyntaxException(pilha);
		}
	}
	
	public boolean isValido() {
		return valido;
	}
	
	public ListaEncadeada<Tag> getTags(){
		return tags;
	}
}