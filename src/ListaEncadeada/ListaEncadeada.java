package ListaEncadeada;

public class ListaEncadeada<T> {

	private NoLista<T> primeiro;

	public ListaEncadeada() {
		this.primeiro = null;
	}

	public NoLista<T> getPrimeiro() {
		return primeiro;
	}

	public void inserir(T info) {
		NoLista<T> no = new NoLista<>();
		no.setInfo(info);
		no.setProximo(getPrimeiro());
		this.primeiro = no;
	}

	public boolean estaVazia() {

		return this.primeiro == null;
	}

	public NoLista<T> buscar(T info) {

		NoLista<T> p = this.primeiro;

		while (p != null) {

			if (info.equals(p.getInfo())) {
				return p;
			}
			p = p.getProximo();
		}

		return null;
	}

	public void retirar(T info) {

		NoLista<T> anterior = null;
		NoLista<T> p = getPrimeiro();

		while ((p != null) && (!p.getInfo().equals(info))) {
			anterior = p;
			p = p.getProximo();
		}

		if (p != null) {
			if (p == primeiro) {
				this.primeiro = p.getProximo();
			} else {
				anterior.setProximo(p.getProximo());
			}
		}
	}

	public int obterComprimento() {
		int comprimento = 0;
		NoLista<T> p = this.primeiro;

		while (p != null) {
			comprimento++;
			p = p.getProximo();
		}

		return comprimento;
	}

	public NoLista<T> obterNo(int posicao) {
		if (posicao < 0) {
			throw new IndexOutOfBoundsException();
		}

		NoLista<T> p = getPrimeiro();

		while ((p != null) && (posicao > 0)) {
			posicao--;
			p = p.getProximo();
		}

		if (p == null) {
			throw new IndexOutOfBoundsException();
		}
		return p;
	}

	@Override
	public String toString() {
		String resultado = "";
		NoLista<T> p = getPrimeiro();

		while (p != null) {
			resultado += p.getInfo().toString();
			if (p.getProximo() != null) {
				resultado += " "; 
			}
			p = p.getProximo();
		}

		return resultado;
	}

}
