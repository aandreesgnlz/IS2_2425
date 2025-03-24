package es.unican.is2.impuesto_circulacion.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import es.unican.is2.impuestoCirculacion.domain.TipoMotor;
import es.unican.is2.impuestoCirculacion.domain.Turismo;

public class TurismoTest {
	
	//Tests constructor no validos
	@Test
	public void testMatriculaNoValida() {
		assertThrows(RuntimeException.class, () -> new Turismo(1, null, LocalDate.of(2020, 1, 1), TipoMotor.GASOLINA, 10));
	}
	
	//Tests constructor validos
	@Test
	public void testConstructorTurismoValido() {
		 Turismo t = new Turismo(1, "1234ABC", LocalDate.of(2020, 1, 1), TipoMotor.GASOLINA, 10);
		 assertEquals(t.getId(), 1);
		 assertEquals(t.getMatricula(), "1234ABC");
	}
	
	//Tests metodo validos
	@Test
	public void testPrecioImpustoValido1Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now().minusYears(25) , TipoMotor.GASOLINA, 0.001);
		assertEquals(t.precioImpuesto(), 0.0);
	}

	@Test
	public void testPrecioImpustoValido2Potencia() {
		Turismo t = new Turismo(2, "1234ABD", LocalDate.now().minusYears(30), TipoMotor.DIESEL, 4);
		assertEquals(t.precioImpuesto(), 0.0);
	}

	@Test
	public void testPrecioImpustoValido3Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now().minusYears(25).plusDays(1), TipoMotor.HIBRIDO, 7);
		assertEquals(t.precioImpuesto(), 25.0);
	}
	
	@Test
	public void testPrecioImpustoValido4Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now().minusYears(12), TipoMotor.ELECTRICO, 8);
		assertEquals(t.precioImpuesto(), 16.75);
	}
	
	@Test
	public void testPrecioImpustoValido5Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now().minusYears(4), TipoMotor.GAS, 10);
		assertEquals(t.precioImpuesto(), 67.0);
	}
	
	@Test
	public void testPrecioImpustoValido6Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now().minusYears(4).plusDays(1), TipoMotor.HIBRIDO, 11.999);
		assertEquals(t.precioImpuesto(), 16.75);
	}
	
	@Test
	public void testPrecioImpustoValido7Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now().minusYears(2), TipoMotor.GASOLINA, 12);
		assertEquals(t.precioImpuesto(), 143.0);
	}
	
	@Test
	public void testPrecioImpustoValido8Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now().minusYears(1), TipoMotor.GASOLINA, 14);
		assertEquals(t.precioImpuesto(), 143.0);
	}
	
	@Test
	public void testPrecioImpustoValido9Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now().minusYears(1).plusDays(1), TipoMotor.GASOLINA, 15.999);
		assertEquals(t.precioImpuesto(), 143.0);
	}
	
	@Test
	public void testPrecioImpustoValido10Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now().minusDays(100), TipoMotor.GASOLINA, 16);
		assertEquals(t.precioImpuesto(), 178.0);
	}
	
	@Test
	public void testPrecioImpustoValido11Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, 18);
		assertEquals(t.precioImpuesto(), 178.0);
	}
	
	@Test
	public void testPrecioImpustoValido12Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, 19.999);
		assertEquals(t.precioImpuesto(), 178.0);
	}
	
	@Test
	public void testPrecioImpustoValido13Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, 20);
		assertEquals(t.precioImpuesto(), 223.0);
	}
	
	@Test
	public void testPrecioImpustoValido14Potencia() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, 35);
		assertEquals(t.precioImpuesto(), 223.0);
	}
	
	//Tests metodo no validos
	@Test
	public void testPotenciaNoValida1() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, 0);
		assertThrows(RuntimeException.class, () -> t.precioImpuesto());
	}
	
	@Test
	public void testPotenciaNoValida2() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, -4);
		assertThrows(RuntimeException.class, () -> t.precioImpuesto());
	}
	
	@Test
	public void testFechaNoValida() {
		Turismo t = new Turismo(1, "1234ABC", LocalDate.now().plusYears(4), TipoMotor.GASOLINA, 8);
		assertThrows(RuntimeException.class, () -> t.precioImpuesto());
	}
	
	
}
