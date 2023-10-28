package Pilha;

import ListaEncadeada.ListaEncadeada;

public class PilhaLista<T> implements Pilha<T> {

	private ListaEncadeada<T> lista;

	public PilhaLista() {
		this.lista = new ListaEncadeada<>();
	}

	@Override
	public void push(T info) {
		lista.inserir(info);
	}

	@Override
	public T pop() {

		T valor;

		valor = peek();

		lista.retirar(valor);

		return valor;
	}

	@Override
	public T peek() {
		if (estaVazia()) {
			throw new PilhaVaziaException();
		}
		return lista.getPrimeiro().getInfo();
	}

	@Override
	public boolean estaVazia() {

		return lista.estaVazia();
	}

	@Override
	public void liberar() {
		lista = new ListaEncadeada<>();
	}

	public String toString() {
		return lista.toString();
	}

}
