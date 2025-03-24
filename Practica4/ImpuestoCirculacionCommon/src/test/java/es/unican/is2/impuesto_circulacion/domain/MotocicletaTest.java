package es.unican.is2.impuesto_circulacion.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import es.unican.is2.impuestoCirculacion.domain.Motocicleta;
import es.unican.is2.impuestoCirculacion.domain.TipoMotor;

public class MotocicletaTest {
	
	//Test constructor no valido
	@Test
	public void testMatriculaNoValida() {
		assertThrows(RuntimeException.class, () -> new Motocicleta(1, null, LocalDate.of(2020, 1, 1), TipoMotor.GASOLINA, 10));
	}
	
	//Test constructor valido
	@Test
	public void testConstructorMotocicletaValido() {
		 Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.of(2020, 1, 1), TipoMotor.GASOLINA, 10);
		 assertEquals(m.getId(), 1);
		 assertEquals(m.getMatricula(), "1234ABC");
	}
	
	//Test metodo valido
	@Test
	public void testPrecioImpustoValido1Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now().minusYears(25) , TipoMotor.GASOLINA, 1);
		assertEquals(m.precioImpuesto(), 0.0);
	}

	@Test
	public void testPrecioImpustoValido2Cilindrada() {
		Motocicleta m = new Motocicleta(2, "1234ABD", LocalDate.now().minusYears(30), TipoMotor.DIESEL, 70);
		assertEquals(m.precioImpuesto(), 0.0);
	}

	@Test
	public void testPrecioImpustoValido3Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now().minusYears(25).plusDays(1), TipoMotor.HIBRIDO, 125);
		assertEquals(m.precioImpuesto(), 8.0);
	}
	
	@Test
	public void testPrecioImpustoValido4Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now().minusYears(12), TipoMotor.ELECTRICO, 126);
		assertEquals(m.precioImpuesto(), 3.75);
	}
	
	@Test
	public void testPrecioImpustoValido5Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now().minusYears(4), TipoMotor.GAS, 200);
		assertEquals(m.precioImpuesto(), 15.0);
	}
	
	@Test
	public void testPrecioImpustoValido6Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now().minusYears(4).plusDays(1), TipoMotor.HIBRIDO, 250);
		assertEquals(m.precioImpuesto(), 7.5);
	}
	
	@Test
	public void testPrecioImpustoValido7Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now().minusYears(2), TipoMotor.GASOLINA, 251);
		assertEquals(m.precioImpuesto(), 30.0);
	}
	
	@Test
	public void testPrecioImpustoValido8Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now().minusYears(1), TipoMotor.GASOLINA, 400);
		assertEquals(m.precioImpuesto(), 30.0);
	}
	
	@Test
	public void testPrecioImpustoValido9Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now().minusYears(1).plusDays(1), TipoMotor.GASOLINA, 500);
		assertEquals(m.precioImpuesto(), 30.0);
	}
	
	@Test
	public void testPrecioImpustoValido10Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now().minusDays(100), TipoMotor.GASOLINA, 501);
		assertEquals(m.precioImpuesto(), 60.0);
	}
	
	@Test
	public void testPrecioImpustoValido11Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, 700);
		assertEquals(m.precioImpuesto(), 60.0);
	}
	
	@Test
	public void testPrecioImpustoValido12Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, 1000);
		assertEquals(m.precioImpuesto(), 60.0);
	}
	
	@Test
	public void testPrecioImpustoValido13Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, 1001);
		assertEquals(m.precioImpuesto(), 100.0);
	}
	
	@Test
	public void testPrecioImpustoValido14Cilindrada() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, 5000);
		assertEquals(m.precioImpuesto(), 100.0);
	}
	
	//Test metodo no validos
	@Test
	public void testCilindradaNoValida1() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, 0);
		assertThrows(RuntimeException.class, () -> m.precioImpuesto());
	}
	
	@Test
	public void testCilindradaNoValida2() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, -12);
		assertThrows(RuntimeException.class, () -> m.precioImpuesto());
	}
	
	@Test
	public void testFechaNoValida() {
		Motocicleta m = new Motocicleta(1, "1234ABC", LocalDate.now().plusYears(4), TipoMotor.GASOLINA, 8);
		assertThrows(RuntimeException.class, () -> m.precioImpuesto());
	}
}
