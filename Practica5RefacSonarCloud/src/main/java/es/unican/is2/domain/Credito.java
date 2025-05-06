package es.unican.is2.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import es.unican.is2.exceptions.datoErroneoException;
import es.unican.is2.exceptions.saldoInsuficienteException;


public class Credito extends Tarjeta {
	
	private double creditoEnTarjeta;
	private List<Movimiento> MovimientosMensuales;
	private List<Movimiento> historicoMovimientos;

	public Credito(String numero, String titular, String cvc, //WMC= +1
			CuentaAhorro cuentaAsociada, double creditoEnTarjeta) {
		super(numero, titular, cvc, cuentaAsociada);
		this.creditoEnTarjeta = creditoEnTarjeta;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirarEnCajero(double x) throws saldoInsuficienteException, datoErroneoException { //WMC= +1 ///
		if (x<0)	//WMC= +1 CCog= +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		////
		x += x * 0.05; // Comision por operacion con tarjetas credito
		Movimiento m = new Movimiento("Retirada en cajero", LocalDateTime.now(), -x);
		
		if (getGastosAcumulados()+x > creditoEnTarjeta)	//WMC= +1 CCog= +1
			throw new saldoInsuficienteException("Credito insuficiente");
		else { //CCog= +1
			MovimientosMensuales.add(m);
		}
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		//WMC= +1
		if (x<0)	//WMC= +1 CCog= +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > creditoEnTarjeta) //WMC= +1 CCog= +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		////
		Movimiento m = new Movimiento("Compra a credito en: " + datos, LocalDateTime.now(), -x);
		MovimientosMensuales.add(m);
	}
	
    private double getGastosAcumulados() { //WMC= +1
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) { //WMC= +1 CCog= +1
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		return r;
	}
	
	
	public LocalDate getCaducidadCredito() { //WMC= +1
		return this.cuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	 */
	public void liquidar() { //WMC= +1			/////
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) { //WMC= +1 CCog= +1
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		////
		Movimiento liq = new Movimiento("Liquidacion de operaciones tarjeta credito", LocalDateTime.now(), -r);
	
		if (r != 0) //WMC= +1 CCog= +1
			cuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(MovimientosMensuales);
		MovimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosMensuales() { //WMC= +1
		return MovimientosMensuales;
	}
	
	public CuentaAhorro getCuentaAsociada() { //WMC= +1
		return cuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() { //WMC= +1
		return historicoMovimientos;
	}

}