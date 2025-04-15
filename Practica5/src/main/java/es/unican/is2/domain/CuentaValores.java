package es.unican.is2.domain;

import java.util.LinkedList;
import java.util.List;

public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta) { //WMC= +1
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	public List<Valor> getValores() { //WMC= +1
		return valores;
	}
	
	public boolean anhadeValor(Valor valor) { //WMC= +1
		for (Valor v:valores) { //WMC= +1 CCog= +1
			if (v.getEntidad().equals(valor.getEntidad())) //WMC= +1 CCog= +2
				return false;
		}
		valores.add(valor);
		return true;
	}
	
}
