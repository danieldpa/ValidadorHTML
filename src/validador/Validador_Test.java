package validador;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		
		String tag = "Tag: p Count: 1 Tag: H1 Count: 1 Tag: body Count: 1 Tag: title Count: 1 Tag: meta Count: 2 Tag: head Count: 1 Tag: html Count: 1 Tag: !DOCTYPE Count: 1";
		
		assertEquals(true, inter.isValido());
		assertEquals(tag, inter.getTags().toString());
	}

}
