package validador;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Validador_Test {

	@Test
	void test() {
		Interpretador inter = null;
		try {
			inter = new Interpretador("texto.html");
		} catch (IOException | HTMLSyntaxException e) {
			e.printStackTrace();
		}

		System.out.println(inter.getTags().toString());
	}

}
