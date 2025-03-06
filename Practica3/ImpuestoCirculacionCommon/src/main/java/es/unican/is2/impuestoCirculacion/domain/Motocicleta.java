package es.unican.is2.impuestoCirculacion.domain;

import java.time.LocalDate;

/**
 * Clase que representa un vehiculo de tipo motocicleta
 */
public class Motocicleta extends Vehiculo {

	private int cilindrada;

	public Motocicleta(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, int cilindrada) {
		super(id, matricula, fechaMatriculacion, motor);
		this.cilindrada = cilindrada;
	}

	/**
	 * Retorna la cilindrada en CC de la motocicleta.
	 */
	public int getCilindrada() {
		return cilindrada;
	}

	@Override
	public double precioImpuesto() {
		double importe = 0;
		if (cilindrada < 125) {
			importe = 8.00;
        } else if (cilindrada < 250) {
        	importe = 15.00;
        } else if (cilindrada < 500) {
        	importe = 30.00;
        } else if (cilindrada < 1000) {
        	importe = 60.00;
        } else {
        	importe = 100.00;
        }
		
		return aplicarDescuento(importe);
			
	}
}
