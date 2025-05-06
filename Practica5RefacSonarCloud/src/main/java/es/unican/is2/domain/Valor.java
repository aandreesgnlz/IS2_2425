package es.unican.is2.domain;

/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un n�mero de acciones 
 * de una de las entidades del IBEX 35
 */
public class Valor {
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	public Valor(String entidad, int numAcciones, double cotizacionActual) { //WMC= +1
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	}
	
	public int getNumValores() { //WMC= +1
		return numAcciones;
	}

	public void setNumValores(int numValores) { //WMC= +1
		this.numAcciones = numValores;
	}

	public double getCotizacion() { //WMC= +1
		return cotizacion;
	}
	
	public void setCotizacion(double cotizacion) { //WMC= +1
		this.cotizacion = cotizacion;
	}

	public String getEntidad() { //WMC= +1
		return entidad;
	}
	
	@Override
	public boolean equals(Object obj) { 
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;

	    Valor other = (Valor) obj;

	    if (numAcciones != other.numAcciones) return false;

	    if (entidad == null) {
	        return other.entidad == null;
	    } else {
	        return entidad.equals(other.entidad);
	    }
	}

}