package es.unican.is2.domain;

import java.time.LocalDateTime;

public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;
	
	public Movimiento(String concepto, LocalDateTime fecha, double importe) { //WMC= +1S ///
		this.concepto = concepto;
		this.fecha = fecha;
		this.importe = importe;
	}

	public double getI() { //WMC= +1
		return importe;
	}

	public void setI(double newMImporte) { //WMC= +1
		importe = newMImporte;
	}
	
	public String getC() { //WMC= +1
		return concepto;
	}

	public void setC(String newMConcepto) { //WMC= +1
		concepto = newMConcepto;
	}

	public LocalDateTime getF() { //WMC= +1
		return fecha;
	}

	public void setF(LocalDateTime newMFecha) { //WMC= +1
		fecha = newMFecha;
	}

	
	@Override
	public boolean equals(Object obj) { //WMC= +1 CCog= +1
		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto) && fecha.equals(other.fecha)&& importe==other.importe);
	}
	
}