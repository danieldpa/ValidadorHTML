package arvore;

public class Arvore<E> {
	
	private NoArvore<E> raiz;
	
	public void setRaiz(NoArvore<E> raiz) {
		this.raiz = raiz;
	}
	
	public NoArvore<E> getRaiz() {
		return raiz;
	}
	
	public boolean estaVazia() {
		return raiz == null;
	}
	
	public boolean pertence(E info) {
		if (estaVazia()) {
			return false;
		}
		return pertence(raiz, info);
	}
	
	private boolean pertence(NoArvore<E> no, E info) {
		if (no.getInfo().equals(info)) {
			return true;
		}
		NoArvore<E> filho = no.getPrimeiro();
		while (filho != null) {
			if (pertence(filho, info)) {
				return true;
			}
			filho = filho.getProximo();
		}

		return false;
	}
	
	@Override
	public String toString() {
		if (estaVazia()) {
			return "";
		}
		return obterRepresentacaoTextual(raiz);
	}
	
	private String obterRepresentacaoTextual(NoArvore<E> no) {
		String arvore = "<" + no.getInfo();
		NoArvore<E> filho = no.getPrimeiro();
		while (filho != null) {
			arvore += obterRepresentacaoTextual(filho);
			filho = filho.getProximo();
		}
		arvore += '>';
		return arvore;
	}
	
	public int contarNos() {
		if (estaVazia()) {
			return 0;
		}
		return contarNos(raiz);
	}
	
	private int contarNos(NoArvore<E> no) {
		int q = 1;
		NoArvore<E> filho = no.getPrimeiro();
		while(filho != null) {
			q+= contarNos(filho);
			filho = filho.getProximo();
		}
		return q;
	}
	
}
