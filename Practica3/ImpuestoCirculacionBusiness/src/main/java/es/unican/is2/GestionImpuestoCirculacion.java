package es.unican.is2;

import es.unican.is2.impuestoCirculacion.domain.*;
import es.unican.is2.impuestoCirculacion.exceptions.DataAccessException;
import es.unican.is2.impuestoCirculacion.exceptions.OperacionNoValidaException;
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
	
	/**
	 * Registra un nuevo contribuyente
	 * @param c Contribuyente que desea registrar
	 * @return El contribuyente registrado
	 * 		   null si no se registra porque ya existe un 
	 *              contribuyente con el mismo dni
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
		if (contribuyentesDAO.contribuyente(c.getDni()) != null) {
            throw new DataAccessException(); // Ya existe
            
        }
        contribuyentesDAO.creaContribuyente(c);
        return c;
	}
	
	/**
	 * Elimina el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente que se quiere eliminar
	 * @return El contribuyente eliminado
	 * 		   null si no se elimina porque no se encuentra 
	 * @throws OperacionNoValidaException si el contribuyente existe 
	 *         pero tiene vehiculos a su nombre
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Contribuyente bajaContribuyente(String dni) throws DataAccessException, OperacionNoValidaException {
		Contribuyente c = contribuyentesDAO.contribuyente(dni);
        if (c == null) {
            throw new DataAccessException(); // No existe o tiene vehículos asociados
        }
        if (!c.getVehiculos().isEmpty()) {
        	throw new OperacionNoValidaException("El contribuyente tiene vehiculos a su nombre");
        }
        contribuyentesDAO.eliminaContribuyente(dni);
        return c;
	}
	
	/**
	 * Registra un nuevo vehiculo y lo asocia al contribuyente con el dni indicado
	 * @param v Vehiculo que desea registrar
	 * @param dni DNI del contribuyente
	 * @return El vehiculo ya registrado
	 * 		   null si no se registra porque no se encuentra el contribuyente
	 * @throws OperacionNoValidaException si ya existe un vehiculo con la misma matricula
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws DataAccessException, OperacionNoValidaException {
		if (vehiculosDAO.vehiculoPorMatricula(v.getMatricula()) != null) {
			throw new OperacionNoValidaException("Vehiculo existente");
        }
        Contribuyente c = contribuyentesDAO.contribuyente(dni);
        if (c == null) {
        	throw new DataAccessException(); // Contribuyente no existe
        }
        c.getVehiculos().add(v);
        contribuyentesDAO.actualizaContribuyente(c);
        vehiculosDAO.creaVehiculo(v);
        return v;
	}
	
	/**
	 * Elimina el vehiculo cuya matricula se indica y 
	 * que pertenece al contribuyente cuyo dni se indica
	 * @param matricula Matricula del coche a eliminar
	 * @param dni DNI del propietario del vehiculo
 	 * @return El vehiculo eliminado
 	 *         null si el vehiculo o el propietario no existen
 	 * @throws OperacionNoValidaException si el vehiculo no pertenece al dni indicado
 	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo bajaVehiculo(String dni, String matricula) throws DataAccessException, OperacionNoValidaException {
		Vehiculo v = vehiculosDAO.vehiculoPorMatricula(matricula);
		Contribuyente c = contribuyentesDAO.contribuyente(dni);
        if (v == null || c == null) {
        	throw new DataAccessException(); // No existe el vehiculo o el contribuyente
        }
        if  (c.buscaVehiculo(matricula) == null) {
        	throw new OperacionNoValidaException("El vehiculo no pertenece al contribuyente");
        }
        c.getVehiculos().remove(v);
        contribuyentesDAO.actualizaContribuyente(c);
        vehiculosDAO.eliminaVehiculo(matricula);
        return v;
	}
	
	/**
	 * Cambia el propietario del vehiculo indicado
	 * @param matricula Matricula del vehiculo
	 * @param dniActual DNI del propietario actual del vehiculo
	 * @param dniNuevo DNI del nuevo propietario del vehiculo
	 * @return true si se realiza el cambio
	 *         false si no realiza el cambio porque el vehiculo o los contribuyentes no existen
	 * @throws OperacionNoValidaException si el vehiculo no pertenece al dni indicado
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo) 
			throws DataAccessException, OperacionNoValidaException {
		Vehiculo v = vehiculosDAO.vehiculoPorMatricula(matricula);
        Contribuyente actual = contribuyentesDAO.contribuyente(dniActual);
        Contribuyente nuevo = contribuyentesDAO.contribuyente(dniNuevo);
        
        if (v == null || actual == null || nuevo == null) {
        	throw new DataAccessException(); // No existe el vehiculo o alguno de sus contribuyentes
        }
        if  (actual.buscaVehiculo(matricula) == null) {
        	throw new OperacionNoValidaException("El vehiculo no pertenece al contribuyente actual");
        }
        actual.getVehiculos().remove(v);
        contribuyentesDAO.actualizaContribuyente(actual);
        nuevo.getVehiculos().add(v);
        contribuyentesDAO.actualizaContribuyente(nuevo);
        vehiculosDAO.actualizaVehiculo(v);
        //
        return true;
	}
	
	/**
	 * Retorna el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente buscado
	 * @return El contribuyente correspondiente al dni
	 * 		   null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Contribuyente contribuyente(String dni) throws DataAccessException {
		Contribuyente c = contribuyentesDAO.contribuyente(dni);
		if (c == null) {
			throw new DataAccessException(); // No existe el contribuyente
		}
		return c;
	}
	
	/**
	 * Retorna el vehiculo cuya matricula se indica
	 * @param matricula Matricula del vehiculo buscado
	 * @return El vehiculo correspondiente a la matricula
	 * 	       null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo vehiculo(String matricula) throws DataAccessException {
		Vehiculo v = vehiculosDAO.vehiculoPorMatricula(matricula);
		if (v == null) {
			throw new DataAccessException(); // No existe el vehiculo
		}
		return v;
	}


}
