package es.unican.is2;

import es.unican.is2.impuestoCirculacion.domain.*;
import es.unican.is2.impuestoCirculacion.business.IGestionContribuyentes;
import es.unican.is2.impuestoCirculacion.business.IGestionVehiculos;
import es.unican.is2.impuestoCirculacion.business.IInfoImpuestoCirculacion;
import es.unican.is2.impuestoCirculacion.dao.*;

public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {

	private IContribuyentesDAO contribuyentesDAO;
	private IVehiculosDAO vehiculosDAO;

	// Constructor
	public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentesDAO, IVehiculosDAO vehiculosDAO) {
		this.contribuyentesDAO = contribuyentesDAO;
		this.vehiculosDAO = vehiculosDAO;
	}

	public Contribuyente altaContribuyente(Contribuyente c) {
		return null;
	}

	public Contribuyente bajaContribuyente(String dni) {
		return null;
	}

	public Vehiculo altaVehiculo(Vehiculo v, String dni) {
		return null;
	}

	public Vehiculo bajaVehiculo(String dni, String matricula) {
		return null;
	}

	public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo) {
		return false;
	}

	public Contribuyente contribuyente(String dni) {
		return null;
	}

	public Vehiculo vehiculo(String matricula) {
		return null;
	}


}
