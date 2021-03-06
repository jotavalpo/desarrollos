package asesorEspacial;

import org.testng.Assert;
import org.testng.annotations.Test;

import Login.Login;
import demoTestim.flujoSitio;
import util.BaseTest;
import util.FunctionGeneric;

public class TC002_Filtrar_Resultados extends BaseTest {
	public TC002_Filtrar_Resultados() {
		super();
	}

	@Test (priority=3)
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
		
	@Test (priority=4)
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
	
	@Test (priority=5)
	public void FiltrarResultados() {
		try {
			estado = flujoSitio.filtrarResultados(FunctionGeneric.leerMatrizTxt("valorMaximo", matriz));
			if (!FunctionGeneric.stateStep("Filtrar resultados", estado, extent, FunctionGeneric.driver)) 
				
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