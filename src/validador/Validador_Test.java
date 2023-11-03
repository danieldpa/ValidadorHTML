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
		} catch (IOException | HTMLSyntaxException | HTMLInvalidFile e) {
			e.printStackTrace();
		}
		
		Tag[] vetor = inter.getTags();
		for (Tag t : vetor) {
	        System.out.println(t.toString());
	    }
		
		assertEquals(true, inter.isValido());
	}
	
//	void testArquivoCorreto() {
//		Interpretador inter = null;
//		try{
//			inter = new Interpretador("texto.html");
//		} catch (IOException | HTMLSyntaxException e) {
//			e.printStackTrace();
//		}
//		
//		String tag = "Tag: p Count: 1 Tag: H1 Count: 1 Tag: body Count: 1 Tag: title Count: 1 Tag: meta Count: 2 Tag: head Count: 1 Tag: html Count: 1 Tag: !DOCTYPE Count: 1";
//		
//		assertEquals(true, inter.isValido());
//		assertEquals(tag, inter.getTags().toString());
//	}
	
//	@Test
//	void testComSingleton() {
//		Interpretador inter = null;
//		try {
//			inter = new Interpretador("teste01.html");
//		} catch (IOException | HTMLSyntaxException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println(inter.getTags().toString());
//		
//		String tag = "Tag: div Count: 1 Tag: button Count: 1 Tag: br Count: 1 Tag: input Count: 1 Tag: img Count: 1 Tag: p Count: 2 Tag: h1 Count: 1 Tag: body Count: 1 Tag: title Count: 1 Tag: link Count: 1 Tag: meta Count: 2 Tag: head Count: 1 Tag: html Count: 1 Tag: !DOCTYPE Count: 1";
//		
//		assertEquals(true, inter.isValido());
//		assertEquals(tag, inter.getTags().toString());
//	}
//	
//	@Test
//	void testSemFechamentoDiv() {
//		Interpretador inter = null;
//		try {
//			inter = new Interpretador("teste02.html");
//			fail();
//		} catch (IOException | HTMLSyntaxException e) {
//			System.out.println(e.getMessage());
//		}
//		
//	}
//	
//	@Test
//	void testSemAberturaP() {
//		Interpretador inter = null;
//		try {
//			inter = new Interpretador("teste03.html");
//			fail();
//		} catch (IOException | HTMLSyntaxException e) {
//			System.out.println(e.getMessage());
//		}
//		
//	}
//	
//	@Test
//	void testCaseInsensitive() {
//		Interpretador inter = null;
//		try {
//			inter = new Interpretador("teste4.html");
//		} catch (IOException | HTMLSyntaxException e) {
//			e.printStackTrace();
//		}
//		
//		String tag = "Tag: p Count: 1 Tag: H1 Count: 1 Tag: body Count: 1 Tag: title Count: 1 Tag: meta Count: 2 Tag: head Count: 1 Tag: html Count: 1 Tag: !DOCTYPE Count: 1";
//		
//		assertEquals(true, inter.isValido());
//		assertEquals(tag, inter.getTags().toString());
//	}
//	
//	@Test
//	void testSemTags() {
//		Interpretador inter = null;
//		try {
//			inter = new Interpretador("teste05.html");
//		} catch (IOException | HTMLSyntaxException e) {
//			System.out.println(e.getMessage());
//		}
//		String tag = "";
//		
//		assertEquals(true, inter.isValido());
//		assertEquals(tag, inter.getTags().toString());
//	}
//	
//	@Test
//	void testEspacamento() {
//		Interpretador inter = null;
//		try {
//			inter = new Interpretador("teste06.html");
//		} catch (IOException | HTMLSyntaxException e) {
//			e.printStackTrace();
//		}
//		
//		String tag = "Tag: br Count: 1 Tag: p Count: 1 Tag: h1 Count: 1 Tag: body Count: 1 Tag: title Count: 1 Tag: meta Count: 1 Tag: head Count: 1 Tag: html Count: 1 Tag: !DOCTYPE Count: 1";
//		
//		assertEquals(true, inter.isValido());
//		assertEquals(tag, inter.getTags().toString());
//	}
//	
//	@Test
//	void testTagsIncompletas() {
//		Interpretador inter = null;
//		try {
//			inter = new Interpretador("teste07.html");
//			fail();
//		} catch (IOException | HTMLSyntaxException e) {
//			System.out.println(e.getMessage());
//		}
//		
//	}

}
