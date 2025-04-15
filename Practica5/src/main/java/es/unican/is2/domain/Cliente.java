package es.unican.is2.domain;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();
    
    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>();

 	public Cliente(String titular, String calle, String zip, String localidad,  //WMC= +1
 			String telefono, String dni) {  
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String zip, String localidad) {	//WMC= +1
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public void anhadeCuenta(Cuenta c) {										//WMC= +1
		Cuentas.add(c);
	}
	
	public void anhadeTarjeta(Tarjeta t) {										//WMC= +1 
		tarjetas.add(t);
		if (t instanceof Debito) {												//WMC= +1 CCog= +1
			Debito td = (Debito)t;
			td.getCuentaAsociada().setCaducidadDebito(td.getCaducidadDebito());
		} else {																//CCog= +1
			Credito tc = (Credito) t;
			tc.getCuentaAsociada().setCaducidadCredito(tc.getCaducidadCredito());
		}
	}
	
	public double getSaldoTotal() {												//WMC= +1
		double total = 0.0;
		for (Cuenta c: Cuentas) {  												//WMC= +1 CCog= +1
			if (c instanceof CuentaAhorro) {									//WMC= +1 CCog= +2
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  {							//WMC= +1 CCog= +1
				for (Valor v: ((CuentaValores) c).getValores()) {				//WMC= +1 CCog= +3
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	public String getNombre() {													//WMC= +1
		return nombre;
	}

	public String getCalle() {													//WMC= +1
		return calle;
	}

	public String getZip() {													//WMC= +1
		return zip;
	}

	public String getLocalidad() {												//WMC= +1
		return localidad;
	}

	public String getTelefono() {												//WMC= +1
		return telefono;
	}

	public String getDni() {													//WMC= +1
		return dni;
	}
	
	
	
}