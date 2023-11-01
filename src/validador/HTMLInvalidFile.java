package validador;

public class HTMLInvalidFile extends Exception{
	public HTMLInvalidFile(String fileName) {
		super("Arquivo não é um HTML válido: \"" + fileName + "\"");
	}
}
