package es.unican.is2;

import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.impuestoCirculacion.business.IInfoImpuestoCirculacion;
import es.unican.is2.impuestoCirculacion.dao.ContribuyentesDAO;
import es.unican.is2.impuestoCirculacion.dao.IContribuyentesDAO;
import es.unican.is2.impuestoCirculacion.dao.IVehiculosDAO;
import es.unican.is2.impuestoCirculacion.dao.VehiculosDAO;


public class ITVistaFuncionarioTest {

	private FrameFixture demo;
	
	@BeforeEach
	public void setUp() {	
		
	    IContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();	    
	    IVehiculosDAO vehiculosDAO = new VehiculosDAO(); 

	    // Crear la instancia de GestionImpuestoCirculacion
	    IInfoImpuestoCirculacion info = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);
		
		VistaFuncionario gui = new VistaFuncionario(info);
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
        try {
            Thread.sleep(500); // Espera 500 ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}

	@Test 
	public void testBuscarContribuyenteExistente() { 
		demo.textBox("txtDniContribuyente").setText("11111111A"); 
		demo.button("btnBuscar").click(); 
		demo.textBox("txtNombreContribuyente").requireText("Juan Perez Lopez"); 
		
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