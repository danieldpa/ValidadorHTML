package arvore;

public class NoArvore<E> {
	private E info;
	private NoArvore<E> primeiro;
	private NoArvore<E> proximo;

	public NoArvore(E info) {
		this.info = info;
	}
	
	public E getInfo() {
		return info;
	}
	
	public NoArvore<E> getPrimeiro() {
		return primeiro;
	}
	
	public NoArvore<E> getProximo() {
		return proximo;
	}
	
	public void inserirFilho(NoArvore<E> sa) {
		sa.proximo = primeiro;
		primeiro = sa;
	}
	
}
