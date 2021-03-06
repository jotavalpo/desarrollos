package asesorEspacial;

import org.testng.Assert;
import org.testng.annotations.Test;

import Login.Login;
import demoTestim.flujoSitio;
import util.BaseTest;
import util.FunctionGeneric;

public class TC003_Agregar_Destino_Carro extends BaseTest {
	public TC003_Agregar_Destino_Carro() {
		super();
	}

	@Test (priority=6)
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
		
	@Test (priority=7)
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
	
	@Test (priority=8)
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
	
	@Test (priority=9)
	public void AgregarDestino() {
		try {
			estado = flujoSitio.AgregarDestino();
			if (!FunctionGeneric.stateStep("Agregar Destino al carrito", estado, extent, FunctionGeneric.driver)) 
				
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