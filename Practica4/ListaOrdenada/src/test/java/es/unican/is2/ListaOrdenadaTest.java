package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.adt.IListaOrdenada;

public class ListaOrdenadaTest {

	private IListaOrdenada<Integer> lista;

	@BeforeEach
	void setUp() {
		lista = new ListaOrdenada<>(); // Suponiendo una implementación concreta
	}

	//TEST METODO GET
	@Test
	public void testGetListaNoVaciaValido() {
		lista.add(3);
		lista.add(1);
		lista.add(2);

		assertEquals(1, lista.get(0));
		assertEquals(3, lista.get(2));
	}

	@Test
	public void testGetListaNoVaciaNoValido() {
		lista.add(3);
		lista.add(1);
		lista.add(2);

		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(12));
	}

	@Test
	public void testGetListaVacia() {

		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(2));
	}

	//TEST METODO ADD
	@Test
	public void testAddListaNoVaciaValido() {
		//LISTA INICIAL
		lista.add(3);
		lista.add(1);
		lista.add(2);
		//NUEVO VALOR ANHADIDO
		lista.add(6);

		assertEquals(6, lista.get(3));
		assertEquals(4, lista.size());
		
		lista.add(0);
		
		assertEquals(0, lista.get(0));
		assertEquals(5, lista.size());
		
		lista.add(5);
		
		assertEquals(5, lista.get(4));
		assertEquals(6, lista.size());
		

	}

	@Test
	public void testAddListaNoVaciaNoValido() {
		lista.add(3);
		lista.add(1);
		lista.add(2);

		assertThrows(NullPointerException.class, () -> lista.add(null));
	}
	
	//TEST METODO REMOVE
	@Test
	public void testRemoveListaNoVaciaValido() {
		lista.add(3);
		lista.add(1);
		lista.add(2);
		
		assertEquals(1, lista.remove(0));
		assertEquals(2, lista.size());
	}
	
	@Test
	public void testRemoveListaNoVaciaNoValido() {
		lista.add(3);
		lista.add(1);
		lista.add(2);
		
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(12));
		assertEquals(3, lista.size());
	}
	
	@Test
	public void testRemoveListaVaciaNoValido() {
		
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(2));
		assertEquals(0, lista.size());
	}
	
	//TEST METODO SIZE
	@Test
	public void testSizeListaNoVacia() {
		lista.add(3);
		lista.add(1);
		lista.add(2);
		
		assertEquals(3, lista.size());
	}
	
	@Test
	public void testSizeListaVacia() {
		assertEquals(0, lista.size());
	}
	
	//TEST METODO CLEAR
	@Test
	public void testClearListaNoVacia() {
		lista.add(3);
		lista.add(1);
		lista.add(2);
		
		lista.clear();
		assertEquals(0, lista.size());
	}
	
	@Test
	public void testClearListaVacia() {
		lista.clear();
		assertEquals(0, lista.size());
	}
	
	
	


}
