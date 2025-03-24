package es.unican.is2;

import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.impuestoCirculacion.business.IInfoImpuestoCirculacion;
import es.unican.is2.impuestoCirculacion.domain.Contribuyente;
import es.unican.is2.impuestoCirculacion.domain.Vehiculo;
import es.unican.is2.impuestoCirculacion.exceptions.DataAccessException;

public class ITVistaFuncionarioTest {

	private FrameFixture demo;

	IInfoImpuestoCirculacion infoStub = new IInfoImpuestoCirculacion() {
		@Override
		public Contribuyente contribuyente(String dni) throws DataAccessException {
			// TODO Auto-generated method stub
			return new Contribuyente("Juan", "Gomez", "Perez", "12345678A");
		}
		@Override
		public Vehiculo vehiculo(String matricula) throws DataAccessException {
			// TODO Auto-generated method stub
			return null;
		}
	};

	@BeforeEach
	public void setUp() {
		VistaFuncionario gui = new VistaFuncionario(infoStub);
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}

	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}

	@Test 
	public void testBuscarContribuyenteExistente() { 
		demo.textBox("txtDniContribuyente").setText("12345678A"); 
		demo.button("btnBuscar").click(); 
		demo.textBox("txtNombreContribuyente").requireText("Juan Gomez Perez"); 
		//demo.textBox("txtTotalContribuyente").requireText("300.50"); 
		//demo.list("listMatriculasVehiculos").requireMa("1234-ABC", "5678-DEF"); 

		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}