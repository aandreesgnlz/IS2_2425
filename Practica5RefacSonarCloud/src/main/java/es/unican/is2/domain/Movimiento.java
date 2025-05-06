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
	public boolean equals(Object obj) { 
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;

	    Movimiento other = (Movimiento) obj;

	    if (Double.compare(importe, other.importe) != 0) return false;
	    if (concepto == null) {
	        if (other.concepto != null) return false;
	    } else if (!concepto.equals(other.concepto)) return false;

	    if (fecha == null) {
	        return other.fecha == null;
	    } else {
	        return fecha.equals(other.fecha);
	    }
	}
}