package es.unican.is2.impuestoCirculacion.domain;

import java.time.LocalDate;

/**
 * Clase que representa un vehiculo de tipo turismo.
 */
public class Turismo extends Vehiculo {

	private double potencia;
	
	
	public Turismo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, double potencia) throws RuntimeException {
		super(id, matricula, fechaMatriculacion, motor);
		if (matricula == null) {
			throw new RuntimeException("Matricula no valida");
		}
		this.potencia = potencia;
	}

	/**
	 * Retorna la potencia en caballos fiscales del vehiculo.
	 */
	public double getPotencia() {
		return potencia;
	}

	@Override
	public double precioImpuesto() throws RuntimeException {
		double importe = 0;
		if (potencia <= 0) {
			throw new RuntimeException("Potencia no valida");
		}
		if (potencia < 8) {
			importe = 25.00;
        } else if (potencia < 12) {
        	importe = 67.00;
        } else if (potencia < 16) {
        	importe = 143.00;
        } else if (potencia < 20) {
        	importe = 178.00;
        } else {
        	importe = 223.00;
        }
		
		return aplicarDescuento(importe);
	}

}
