package asesorEspacial;

import org.testng.Assert;
import org.testng.annotations.Test;

import Login.Login;
import demoTestim.flujoSitio;
import util.BaseTest;
import util.FunctionGeneric;

public class TC001_Seleccionar_Destino extends BaseTest {
	public TC001_Seleccionar_Destino() {
		super();
	}

	@Test (priority=1)
	public void TestCargarURLTestim() {
		try {
			login = new Login();
			FunctionGeneric.driver = login.openUrlAsesorEspacialChrome(
					FunctionGeneric.leerMatrizTxt("url", matriz));
			login = new Login(FunctionGeneric.driver);
			
			estado = login.validarURL("Space & Beyond | Testim.io demo");
			if (!FunctionGeneric.stateStep("Ingreso al sitio de Testim.io", estado, extent,
					FunctionGeneric.driver))
				
			{
				flagState = false;
			}
			
			else
				
			{
				flagState = true;
			}
			
			Assert.assertEquals(flagState, true);
			
		} catch (Exception e) {
		System.out.println("Error Test: " + e.getMessage());
		afterClass();
		}
	}
	
	@Test (priority=2)
	public void SeleccionarDestino() {
		try {
			estado = flujoSitio.SeleccionarDestino();
			if (!FunctionGeneric.stateStep("Seleccionar Destino", estado, extent, FunctionGeneric.driver)) 
				
			{
				flagState = false;
			}
			
			else
				
			{
				flagState = true;
			}
			
			Assert.assertEquals(flagState, true);
						
		} catch (Exception e) {
			System.out.println("Error Test: " + e.getMessage());
			afterClass();
		}
	}
}