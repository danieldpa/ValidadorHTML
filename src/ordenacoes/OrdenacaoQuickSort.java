package ordenacoes;

public class OrdenacaoQuickSort<E extends Comparable<E>> extends OrdenacaoAbstract<E> {

	@Override
	public void ordernar() {
		quickSort(0, info.length - 1);
	}
	
	private void quickSort(int inicio, int fim) {
		if (inicio < fim) {
			int idxPivo = particionar(inicio, fim);
			quickSort(inicio, idxPivo - 1);
			quickSort(idxPivo + 1, fim);
		}
	}
	
	private int particionar(int inicio, int fim) {
		int a = inicio;
		int b = fim + 1;
		E pivo = info[inicio];
		
		while (true) {
			do {
				a++;
			} while (a <= fim && info[a].compareTo(pivo) < 0);
			
			do {
				b--;
			} while (b >= inicio && info[b].compareTo(pivo) > 0);
			
			if (a >= b) {
				break;
			}
			trocar(a, b);
		}
		trocar(b, inicio);
		return b;
	}
}