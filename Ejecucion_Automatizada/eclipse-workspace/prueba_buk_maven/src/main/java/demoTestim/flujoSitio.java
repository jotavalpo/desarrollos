package demoTestim;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import util.FunctionGeneric;

public class flujoSitio {

	WebDriver driver;

	public void siteTestim(WebDriver driver) {
		this.driver = driver;
	}

	public static String SeleccionarDestino () {

		String msg = "OK";

		try {
			
			msg = FunctionGeneric.clickObjectByXpath("Click en Seleccionar Departing", "//*[@id=\'app\']/div/section[1]/div[3]/div/div[1]/div/div/input", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.clickObjectByXpath("Seleccionar fecha de calendario", "/html/body/div[2]/div/div[2]/section/div/div/div/span/div/div[2]/div[15]/span", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.clickObjectByXpath("Click en OK de calendario", "/html/body/div[2]/div/div[2]/nav/button[2]", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.clickObjectByXpath("Click en Seleccionar Returning", "//*[@id=\'app\']/div/section[1]/div[3]/div/div[2]/div/div/input", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.clickObjectByXpath("Seleccionar fecha de calendario", "/html/body/div[2]/div/div[2]/section/div/div/div/span/div/div[2]/div[28]/span", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.clickObjectByXpath("Click en OK de calendario", "/html/body/div[2]/div/div[2]/nav/button[2]", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
						
			msg = FunctionGeneric.clickObjectByXpath("Click en selector de Adults (18+)", "//*[@id=\'app\']/div/section[1]/div[3]/div/div[3]", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.clickObjectByXpath("Click en opci?n 2 adultos", "//*[@id=\'app\']/div/section[1]/div[3]/div/div[3]/ul/li[3]", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.clickObjectByXpath("Click en selector de Children (0-7)", "//*[@id=\'app\']/div/section[1]/div[3]/div/div[4]", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.clickObjectByXpath("Click en opci?n 2 ni?os", "//*[@id=\'app\']/div/section[1]/div[3]/div/div[4]/ul/li[3]", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
		} catch (Exception e) {
			msg = "Error al buscar destino";
		}

		return msg;
	}
	
	public static String filtrarResultados (String valorMaximo) {

		String msg = "OK";

		try {
			
			msg = FunctionGeneric.clickObjectByXpath("Seleccionar lugar de lanzamiento (Launch)", "//*[@id=\'app\']/div/section[2]/div[4]/div/div/div[1]/div/input", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			FunctionGeneric.driver.findElement(By.xpath("//*[@id=\'app\']/div/section[2]/div[4]/div/div/div[1]/ul/li[2]")).click();
			Thread.sleep(1000);
			if (!msg.equals("OK"))
			return msg;
			
			msg = FunctionGeneric.clickObjectByXpath("Seleccionar color del planeta (Planet color)", "//*[@id=\'app\']/div/section[2]/div[4]/div/div/div[2]/div/input", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			FunctionGeneric.driver.findElement(By.xpath("//*[@id=\'app\']/div/section[2]/div[4]/div/div/div[2]/ul/li[3]")).click();
			Thread.sleep(1000);
			if (!msg.equals("OK"))
			return msg;
			
			msg = FunctionGeneric.setTextObjectByXpath("Definir monto m?ximo", "//*[@id=\'app\']/div/section[2]/div[4]/div/div/div[3]/div[2]/div[2]/input", valorMaximo, "set", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			FunctionGeneric.driver.findElement(By.xpath("//*[@id=\'app\']/div/section[2]/div[4]/div/div/div[3]")).click();
			Thread.sleep(1000);
			if (!msg.equals("OK"))
			return msg;
			
					
		} catch (Exception e) {
			msg = "Error al filtrar los resultados";
		}

		return msg;
	}
	
	public static String AgregarDestino () {

		String msg = "OK";

		try {
			
			//Subir scroll mouse ya que ventana al buscar y filtrar se sube, dejando los resultados fuera de la vista en navegador
            JavascriptExecutor js = (JavascriptExecutor) FunctionGeneric.driver;
            js.executeScript("window.scrollBy(0,+300)", "");
			
            Thread.sleep(3000);
            msg = FunctionGeneric.clickObjectByXpath("Click en Tongli", "//*[@id=\'app\']/div/section[2]/div[5]/div/div/div[1]/div[4]/button", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
					
		} catch (Exception e) {
			msg = "Error al agregar destino al carrito";
		}

		return msg;
	}
	
	public static String completarDatosCheckout (String nombre, String email, String seguroSocial, String telefono, String codPromocion) {

		String msg = "OK";

		try {
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar nombre (Name)", "//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[1]/form/div[1]/input", nombre, "clean", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar nombre (Name)", "//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[1]/form/div[1]/input", nombre, "set", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar correo (Email Address)", "//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[1]/form/div[2]/input", email, "clean", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar correo (Email Address)", "//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[1]/form/div[2]/input", email, "set", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar n?mero seguro social (Social Security Number)", "//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[1]/form/div[3]/input", seguroSocial, "clean", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar n?mero seguro social (Social Security Number)", "//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[1]/form/div[3]/input", seguroSocial, "set", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar tel?fono (Phone Number)", "//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[1]/form/div[4]/input", telefono, "clean", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar tel?fono (Phone Number)", "//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[1]/form/div[4]/input", telefono, "set", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			//Subir scroll mouse ya que ventana al buscar y filtrar se sube, dejando los resultados fuera de la vista en navegador
            JavascriptExecutor js = (JavascriptExecutor) FunctionGeneric.driver;
            js.executeScript("window.scrollBy(0, +500)", "");
			
			// Adjuntar documento "Poliza_de_seguro_de_vida.pdf" que se encuentra en la ruta C:\desarrollos\Ejecucion_Automatizada			
			
			FunctionGeneric.driver.findElement(By.xpath("//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[1]/div/div")).click();
			Thread.sleep(3000);
			Runtime.getRuntime (). exec ("C:\\desarrollos\\Ejecucion_Automatizada\\FileUpload.exe");
			Thread.sleep(3000);
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar c?digo descuento (I have a promo code)", "//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[2]/div[4]/div[1]/div/input", codPromocion, "clean", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar c?digo descuento (I have a promo code)", "//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[2]/div[4]/div[1]/div/input", codPromocion, "set", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.clickObjectByXpath("Click en bot?n APPLY", "//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[2]/div[4]/div[2]/button", "click", FunctionGeneric.driver);
			Thread.sleep(2000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.clickObjectByXpath("Click en Acepto t?rminos y condiciones (I agree to the terms and conditions)", "//*[@id=\'app\']/div/div[2]/section[1]/div[3]/div[2]/div[5]/div/label/div", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.clickObjectByXpath("Click en bot?n PAY NOW", "//*[@id=\"app\"]/div/div[2]/section[1]/div[3]/div[2]/div[7]/div/button", "click", FunctionGeneric.driver);
			Thread.sleep(2000);
			if (!msg.equals("OK"))
				return msg;
					
		} catch (Exception e) {
			msg = "Error al completar los datos requeridos en el checkout";
		}

		return msg;
	}

	public static String IniciarSesion (String usuario, String password) {

		String msg = "OK";

		try {
			
			msg = FunctionGeneric.clickObjectByXpath("Click en LOG IN", "//*[@id=\'app\']/div/header/div/div[2]/ul/li/button", "click", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;		
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar usuario (Username)", "//*[@id=\'login\']/div[1]/input", usuario, "clean", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar usuario (Username)", "//*[@id=\'login\']/div[1]/input", usuario, "set", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar contrase?a (Password)", "//*[@id=\'login\']/div[2]/input", password, "clean", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObjectByXpath("Ingresar contrase?a (Password)", "//*[@id=\'login\']/div[2]/input", password, "set", FunctionGeneric.driver);
			Thread.sleep(1000);
			if (!msg.equals("OK"))
				return msg;
						
			msg = FunctionGeneric.clickObjectByXpath("Click en Iniciar Sesi?n (LOG IN)", "button", "type", "submit", "click", FunctionGeneric.driver);
			Thread.sleep(2000);
			if (!msg.equals("OK"))
				return msg;
			
		} catch (Exception e) {
			msg = "Error al iniciar sesi?n en el sitio";
		}

		return msg;
	}
}