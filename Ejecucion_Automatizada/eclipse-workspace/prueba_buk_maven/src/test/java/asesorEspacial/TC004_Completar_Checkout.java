package asesorEspacial;

import org.testng.Assert;
import org.testng.annotations.Test;

import Login.Login;
import demoTestim.flujoSitio;
import util.BaseTest;
import util.FunctionGeneric;

public class TC004_Completar_Checkout extends BaseTest {
	public TC004_Completar_Checkout() {
		super();
	}

	@Test (priority=10)
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
		
	@Test (priority=11)
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
	
	@Test (priority=12)
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
	
	@Test (priority=13)
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
	
	@Test (priority=14)
	public void CompletarCheckout() {
		try {
			estado = flujoSitio.completarDatosCheckout(
					FunctionGeneric.leerMatrizTxt("nombre", matriz),
					FunctionGeneric.leerMatrizTxt("email", matriz),
					FunctionGeneric.leerMatrizTxt("seguroSocial", matriz),
					FunctionGeneric.leerMatrizTxt("telefono", matriz),
					FunctionGeneric.leerMatrizTxt("codPromocion", matriz));
			if (!FunctionGeneric.stateStep("Completar datos del checkout", estado, extent, FunctionGeneric.driver)) 
				
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