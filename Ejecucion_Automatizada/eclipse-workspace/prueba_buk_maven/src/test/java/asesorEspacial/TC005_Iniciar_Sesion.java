package asesorEspacial;

import org.testng.Assert;
import org.testng.annotations.Test;

import Login.Login;
import demoTestim.flujoSitio;
import util.BaseTest;
import util.FunctionGeneric;

public class TC005_Iniciar_Sesion extends BaseTest {
	public TC005_Iniciar_Sesion() {
		super();
	}

	@Test (priority=15)
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
	
	@Test (priority=16)
	public void IniciarSesion() {
		try {
			estado = flujoSitio.IniciarSesion(
					FunctionGeneric.leerMatrizTxt("usuario", matriz),
					FunctionGeneric.leerMatrizTxt("password", matriz));
			if (!FunctionGeneric.stateStep("Iniciar sesi?n en el sitio", estado, extent, FunctionGeneric.driver)) 
				
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