package com.hackaton.backend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClassTest {

	@Test
	public void testSuma() {
		int resultado = ClassAProbar.sumar(2, 3);
		int esperado = 5; // 2 + 3 = 5
		
		//Muchos tipos de assert, valida si lo que se trajo era lo esperado:
		assertEquals(esperado, resultado);
		
		//Generar fail a posta:
		//fail("Not yet implemented");
	}
	
	@Test
	public void testResta() {
		int resultado = ClassAProbar.restar(3, 1);
		int esperado = 2; // 3 - 1 = 2
		
		//Muchos tipos de assert, valida si lo que se trajo era lo esperado:
		assertEquals(esperado, resultado);
	}

}
