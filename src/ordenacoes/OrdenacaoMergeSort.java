package ordenacoes;

public class OrdenacaoMergeSort<E extends Comparable<E>> extends OrdenacaoAbstract<E> {

	@Override
	public void ordernar() {
		mergeSort(0, info.length - 1);
	}
	
	private void mergeSort(int inicio, int fim) {
		if (inicio < fim) {
			int meio = (inicio + fim) / 2;
			mergeSort(inicio, meio);
			mergeSort(meio + 1, fim);
			merge(inicio, fim, meio);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void merge(int inicio, int fim, int meio) {
		int tamEsquerda = meio - inicio + 1;
		E[] esquerda = (E[]) new Comparable[tamEsquerda];
		for (int i = 0; i < tamEsquerda; i++) {
			esquerda[i] = info[inicio + i];
		}
		int tamDireita = fim - meio;
		E[] direita = (E[]) new Comparable[tamDireita];
		for (int i = 0; i < tamDireita; i++) {
			direita[i] = info[meio + 1 + i];
		}
		int cEsq = 0;
		int cDir = 0;
		int i = 0;
		for (i = inicio; i <= fim; i++) {
			if (cEsq < tamEsquerda && cDir < tamDireita) {
				if (esquerda[cEsq].compareTo(direita[cDir]) < 0) {
					info[i] = esquerda[cEsq];
					cEsq++;
				} else {
					info[i] = direita[cDir];
					cDir++;
				}
			} else {
				break;
			}

		}
		
		for (; cEsq < tamEsquerda; i++) {
			info[i] = esquerda[cEsq];
			cEsq++;
		}
		
		for (; cEsq < tamEsquerda; i++) {
			info[i] = direita[cDir];
			cDir++;
		}
	}
}
