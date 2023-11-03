package validador;

public class HTMLInvalidFile extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HTMLInvalidFile(String fileName) {
		super("Arquivo não é um HTML válido: \"" + fileName + "\"");
	}
}
