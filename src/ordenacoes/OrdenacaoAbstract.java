package ordenacoes;

public abstract class OrdenacaoAbstract<E extends Comparable<E>> {
	protected E[] info;
	
	public E[] getInfo() {
		return info;
	}
	public void setInfo(E[] info) {
		this.info = info;
	}
	public void trocar(int a, int b) {
		E temp = info[a];
		info[a] = info[b];
		info[b] = temp;
	}
	public abstract void ordernar();
}
