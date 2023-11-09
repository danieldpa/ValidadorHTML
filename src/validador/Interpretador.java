package validador;

import ListaEncadeada.ListaEncadeada;
import Pilha.PilhaLista;
import ordenacoes.OrdenacaoQuickSort;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Interpretador {
	private String file;
	private ListaEncadeada<Tag> tags;
	private PilhaLista<String> pilha;
	private boolean valido;
	
	public Interpretador(String path) throws IOException, HTMLSyntaxException, HTMLInvalidFile {
		pilha = new PilhaLista<String>();
		tags = new ListaEncadeada<Tag>();
		analisar(path);
	}
	
	private void lerArquivo(String path) throws IOException, HTMLInvalidFile {
		if (!path.substring(path.length() - 5, path.length()).equalsIgnoreCase(".html")) {
			throw new HTMLInvalidFile(path);
		}
		
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		file = new String(encoded, Charset.defaultCharset());
	}
	
	private void analisar(String path) throws IOException, HTMLSyntaxException, HTMLInvalidFile {
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
		boolean closedTagInit = false;
		String tagName = "";
		
		int newLines = 1;
		int characters = 0;
		
		for(int i = 0; i < file.length(); i++) {
			String sub = file.substring(i, i + 1);
			
			if ("\n".equalsIgnoreCase(sub)) {
				newLines++;
				characters = 0;
				
			}else {
				characters++;
				if (foundTagInit) {
					if (">".equalsIgnoreCase(sub)) {
						if (foundTagClose) {
							String ultimaTag = pilha.peek();
							
							if (ultimaTag.equalsIgnoreCase(tagName)) {
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
							for (Tag tag : tags) {
								
								if (tagName.equalsIgnoreCase(tag.getNome())) {
									tag.setCount(tag.getCount() + 1);
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
					else if ("/".equalsIgnoreCase(sub) && closedTagInit) {
						foundTagClose = true;
					}
					
					else if (" ".equalsIgnoreCase(sub)) {
						hasAttributes = true;
					}
						
					else if (!hasAttributes){
						tagName += sub;
					}

				closedTagInit = false;
				}else {
					if ("<".equalsIgnoreCase(sub)) {
						foundTagInit = true;
						closedTagInit = true;
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

	private Tag[] converterVetor() {
		Tag[] tagsVetor = new Tag[tags.obterComprimento()];
		int i = 0;
		for (Tag tag : tags) {
			tagsVetor[i] = tag;
			i++;
		}
		return tagsVetor;
	}
	
	public Tag[] getTags() {
        Tag[] tagsVetor = converterVetor();
        OrdenacaoQuickSort<Tag> quickSort = new OrdenacaoQuickSort<Tag>(); 
        quickSort.setInfo(tagsVetor);
        quickSort.ordernar();
        
        return quickSort.getInfo();
    }
	
}
