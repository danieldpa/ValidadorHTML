package ordenacoes;

public class OrdenacaoBolha<E extends Comparable<E>> extends OrdenacaoAbstract<E> {

	@Override
	public void ordernar() {
		for (int i = info.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (info[j].compareTo(info[j + 1]) > 0) {
					trocar(j, j + 1);
				}
			}
		}
	}
}
