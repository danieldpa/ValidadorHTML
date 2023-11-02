package ordenacoes;

public class OrdenacaoBolhaOtimizada<E extends Comparable<E>> extends OrdenacaoAbstract<E> {

	@Override
	public void ordernar() {
		boolean trocou = false;
		for (int i = info.length - 1; i > 0; i--) {
			trocou = false;
			for (int j = 0; j < i; j++) {
				if (info[j].compareTo(info[j + 1]) > 0) {
					trocar(j, j + 1);
					trocou = true;
				}
			}
			if (!trocou) {
				return;
			}
		}
	}

}
