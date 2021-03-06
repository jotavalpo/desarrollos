package Login;

import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import util.FunctionGeneric;
import util.PropertiesManager;

public class Login {

	static String curDir = System.getProperty("user.dir");
	WebDriver driver;
	static String url = "";
	Properties prop = new Properties();
	private String path;

	public Login() {

	}

	public Login(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver openUrlAsesorEspacialFirefox(String url) {
	DesiredCapabilities dc = null;
	PropertiesManager prop = new PropertiesManager();
	
	try {
//		FunctionGeneric.executeCMD("taskkill -f -im cmd.exe");
//		FunctionGeneric.executeCMD("taskkill -f -im firefox.exe");
		System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--start-maximized");
		String BrowserName = null, nodeUrl = null;
		BrowserName = prop.getFrameworkProperty("BrowserMF");
		System.out.println(BrowserName);
		nodeUrl = prop.getFrameworkProperty("nodeURlFirefoxLocal");
		if ("Firefox".equalsIgnoreCase(BrowserName)) {
			dc = DesiredCapabilities.firefox();
		} else if ("Chrome".equalsIgnoreCase(BrowserName)) {
			dc = DesiredCapabilities.chrome();
		} else if ("IExplorer".equalsIgnoreCase(BrowserName)) {
			dc = DesiredCapabilities.internetExplorer();
		} else {
			System.out.println("No ingreso el nombre del navegador");
		}
		dc.setPlatform(Platform.WINDOWS);
		dc.setCapability("requireWindowsFocus", true);
		dc.setCapability("ignoreZoomSetting", true);
		dc.setCapability("nativeEvents", false);
		dc.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);

		FunctionGeneric.driver = new RemoteWebDriver(new URL(nodeUrl), dc);

		System.out.println(url);

		if (url.equals("")) {
			FunctionGeneric.driver = null;

		} else {
			FunctionGeneric.driver.navigate().to(url);
		}

	} catch (Exception e) {
		System.out.println("Error al abrir p?gina de inicio del Asesor Espacial " + e.getMessage());
	}

	return FunctionGeneric.driver;
}
	
	public WebDriver openUrlAsesorEspacialChrome(String url) {
		DesiredCapabilities dc = null;
		PropertiesManager prop = new PropertiesManager();
		
		try {
//			FunctionGeneric.executeCMD("taskkill -f -im cmd.exe");
//			FunctionGeneric.executeCMD("taskkill -f -im chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			String BrowserName = null, nodeUrl = null;
			BrowserName = prop.getFrameworkProperty("BrowserCH");
			System.out.println(BrowserName);
			nodeUrl = prop.getFrameworkProperty("nodeURlChromeLocal");
			if ("Firefox".equalsIgnoreCase(BrowserName)) {
				dc = DesiredCapabilities.firefox();
			} else if ("Chrome".equalsIgnoreCase(BrowserName)) {
				dc = DesiredCapabilities.chrome();
			} else if ("IExplorer".equalsIgnoreCase(BrowserName)) {
				dc = DesiredCapabilities.internetExplorer();
			} else {
				System.out.println("No ingreso el nombre del navegador");
			}
			dc.setPlatform(Platform.WINDOWS);
			dc.setCapability("requireWindowsFocus", true);
			dc.setCapability("ignoreZoomSetting", true);
			dc.setCapability("nativeEvents", false);
			dc.setCapability(ChromeOptions.CAPABILITY, options);

			FunctionGeneric.driver = new RemoteWebDriver(new URL(nodeUrl), dc);

			System.out.println(url);

			if (url.equals("")) {
				FunctionGeneric.driver = null;

			} else {
				FunctionGeneric.driver.navigate().to(url);
			}

		} catch (Exception e) {
			System.out.println("Error al abrir p?gina de inicio del Asesor Espacial " + e.getMessage());
		}

		return FunctionGeneric.driver;
	}

	public WebDriver openUrlAsesorEspacialIE(String url) {
	
	DesiredCapabilities dc = null;
	PropertiesManager prop = new PropertiesManager();
	
	try {
//		FunctionGeneric.executeCMD("taskkill -f -im cmd.exe");
//		FunctionGeneric.executeCMD("taskkill -f -im chromedriver.exe");

		System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
		
		String BrowserName = null, nodeUrl = null;
		
		BrowserName = prop.getFrameworkProperty("BrowserIE");
		System.out.println(BrowserName);
		nodeUrl = prop.getFrameworkProperty("nodeURlIExplorerLocal");
		if ("Firefox".equalsIgnoreCase(BrowserName)) {
			dc = DesiredCapabilities.firefox();
		} else if ("Chrome".equalsIgnoreCase(BrowserName)) {
			dc = DesiredCapabilities.chrome();
		} else if ("IExplorer".equalsIgnoreCase(BrowserName)) {
			dc = DesiredCapabilities.internetExplorer();
		} else {
			System.out.println("No ingreso el nombre del navegador");
		}
		dc.setPlatform(Platform.WINDOWS);
		dc.setVersion("11");
		dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		dc.setCapability("requireWindowsFocus", true);
		dc.setCapability("ignoreZoomSetting", true);
		dc.setCapability("nativeEvents", false);
		dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		dc.setCapability("acceptInsecureCerts", true);
		dc.setCapability("acceptSslCerts",true);
		dc.setAcceptInsecureCerts(false);
		dc.acceptInsecureCerts();

		FunctionGeneric.driver = new RemoteWebDriver(new URL(nodeUrl), dc);

		System.out.println(url);

		if (url.equals("")) {
			FunctionGeneric.driver = null;

		} else {
			FunctionGeneric.driver.navigate().to(url);
		}

	} catch (Exception e) {
		System.out.println("Error al abrir p?gina de inicio del Asesor Espacial " + e.getMessage());
	}

	return FunctionGeneric.driver;
}
	
//	public String cerrarVentanasEmergentes() {
//
//		String msg = "OK";
//
//		try {
//
//			Thread.sleep(5000);
//			msg = FunctionGeneric.clickObjectByXpath("Cerrar ventana newsletter", "/html/body/w-div[3]/w-div/w-div/span", "click", FunctionGeneric.driver);
//			Thread.sleep(1000);
//			if (!msg.equals("OK"))
//				return msg;
//			
//			msg = FunctionGeneric.clickObjectByXpath("Cerrar ventana descuentos", "/html/body/w-div[1]/span", "click", FunctionGeneric.driver);
//			Thread.sleep(1000);
//			if (!msg.equals("OK"))
//				return msg;
//			
//			Thread.sleep(5000);
//			msg = FunctionGeneric.clickObjectByXpath("Cerrar ventana recibir ofertas", "button", "id", "onesignal-popover-allow-button", "click", FunctionGeneric.driver);
//			Thread.sleep(1000);
//			if (!msg.equals("OK"))
//				return msg;
//			
//		} catch (Exception e) {
//			msg = "Error al cerrar las ventanas emergentes";
//		}
//		return msg;
//	}
		
	public WebDriver returnDriver(String navegador) {
		DesiredCapabilities dc = null;
		FunctionGeneric.driver = null;
		try {
			FunctionGeneric.executeCMD("taskkill -f -im cmd.exe");
			String BrowserName = null, nodeUrl = null;
			switch (navegador) {

			case "ie":

				FunctionGeneric.executeCMD("taskkill -f -im IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
				BrowserName = prop.getProperty("BrowserIE");
				System.out.println(BrowserName);
				nodeUrl = prop.getProperty("nodeURlIExplorerLocal");
				if ("Firefox".equalsIgnoreCase(BrowserName)) {
					dc = DesiredCapabilities.firefox();
				} else if ("Chrome".equalsIgnoreCase(BrowserName)) {
					dc = DesiredCapabilities.chrome();
				} else if ("IExplorer".equalsIgnoreCase(BrowserName)) {
					dc = DesiredCapabilities.internetExplorer();
				} else {
					System.out.println("No ingreso el nombre del navegador");
				}
				dc.setPlatform(Platform.WINDOWS);
				dc.setVersion("11");
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				dc.setCapability("requireWindowsFocus", true);
				dc.setCapability("ignoreZoomSetting", true);
				dc.setCapability("nativeEvents", false);

				FunctionGeneric.driver = new RemoteWebDriver(new URL(nodeUrl), dc);

				break;

			case "chrome":

				FunctionGeneric.executeCMD("taskkill -f -im chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				BrowserName = prop.getProperty("BrowserCH");
				System.out.println(BrowserName);
				nodeUrl = prop.getProperty("nodeURlChromeLocal");
				if ("Firefox".equalsIgnoreCase(BrowserName)) {
					dc = DesiredCapabilities.firefox();
				} else if ("Chrome".equalsIgnoreCase(BrowserName)) {
					dc = DesiredCapabilities.chrome();
				} else if ("IExplorer".equalsIgnoreCase(BrowserName)) {
					dc = DesiredCapabilities.internetExplorer();
				} else {
					System.out.println("No ingreso el nombre del navegador");
				}
				dc.setPlatform(Platform.WINDOWS);
				dc.setCapability("requireWindowsFocus", true);
				dc.setCapability("ignoreZoomSetting", true);
				dc.setCapability("nativeEvents", false);
				dc.setCapability(ChromeOptions.CAPABILITY, options);

				FunctionGeneric.driver = new RemoteWebDriver(new URL(nodeUrl), dc);
				break;

			case "firefox":
				FunctionGeneric.executeCMD("taskkill -f -im geckodriver.exe");
				System.setProperty("webdriver.firefox.marionette", "./driver/geckodriver.exe");
				FunctionGeneric.driver = new FirefoxDriver();
				break;

			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return FunctionGeneric.driver;
	}

	public String ingresoLoginPHPTravels(String usuario, String password) {

		String msg = "OK";

		try {

			Thread.sleep(5000);
			msg = FunctionGeneric.setTextObject("Correo", "name", "email", usuario, "set", false, FunctionGeneric.driver);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObject("Correo", "name", "email", usuario, "set", true, FunctionGeneric.driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.setTextObject("Contrase?a", "name", "password", password, "set", false, FunctionGeneric.driver);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObject("Contrase?a", "name", "password", password, "set", true, FunctionGeneric.driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Boton Ingresar", "button", "type", "submit", "click", this.driver);
			if (!msg.equals("OK"))
				return msg;

			Thread.sleep(5000);
			
		} catch (Exception e) {
			msg = "Error al realizar login en PHP Travels";
		}
		return msg;
	}
	
	public String validarURL(String title) {

		String msg = "OK";
		String titulo = "";
		titulo = FunctionGeneric.driver.getTitle();
		if (!title.equals(titulo))
			msg = "No se ha logrado acceder a la URL " + title;

		return msg;
	}
}
