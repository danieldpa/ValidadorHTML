package validador;

public class Tag  implements Comparable<Tag>{
	private String nome;
	private int count;
	
	public Tag(String name) {
		nome = name;
		count = 1;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String toString() {
		return "Tag: \"" + nome + "\" "+ 
				"Count: " + count; 
	}

	@Override
    public int compareTo(Tag o) {	
        int resultado = Integer.compare(o.count, this.count);
        if (resultado != 0) {
            return resultado;
        }
        return this.nome.compareTo(o.nome);
    }
}
