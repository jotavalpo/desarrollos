package util;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class FunctionGeneric {
	public static ArrayList<String> arrEvidencia = new ArrayList<String>();
	public static String spoofUserAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36";
	// protected static final String CUR_DIR = System.getProperty("user.dir");
	public static WebDriver driver;
	public void deleteFile(String nameFile, String path) throws UnknownHostException {
		String computerName = InetAddress.getLocalHost().getHostName();
		System.out.println(computerName);
		
		if (computerName.contains("server")) {
			File fileServer = new File("F:\\apps\\desarrollos\\Ejecucion_Automatizada\\" + path + "HTML\\" + nameFile + ".html");
			if (fileServer.exists()) {
				fileServer.delete();
			}
		}else {
			File file = new File("C:\\desarrollos\\Ejecucion_Automatizada\\" + path + "HTML\\" + nameFile + ".html");
			if (file.exists()) {
				file.delete();
			}
		}
		}
		
	

//	public static void closeWindows(int winEsperadas) {
//		int contador = 0;
//		try {
//
//			closeAlert();
//			Set<String> winSet = driver.getWindowHandles();
//			List<String> winList = new ArrayList<String>(winSet);
//
//			while (winList.size() != winEsperadas) {
//				driver.switchTo().window(winList.get(winList.size() - 1));
//				driver.close();
//				winSet = driver.getWindowHandles();
//				winList = new ArrayList<String>(winSet);
//				contador++;
//				if (contador < 20)
//					closeWindows(0);
//			}
//
//		} catch (Exception e) {
//
//		}
//	}

	public static void closeWindows(int winEsperadas) {
		try {

			closeAlert();
			Set<String> winSet = FunctionGeneric.driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			
			for (int i = 0; i < winList.size(); i++) {
				FunctionGeneric.driver.switchTo().window(winList.get(winList.size() - 1));
				FunctionGeneric.driver.close();
			}
//			FunctionGeneric.executeCMD("taskkill -f -im cmd.exe");
//			FunctionGeneric.executeCMD("taskkill -f -im chromedriver.exe");
//			FunctionGeneric.executeCMD("taskkill -f -im iexplore.exe");
			FunctionGeneric.driver.quit();
//			while (winList.size() != winEsperadas) {
//				driver.switchTo().window(winList.get(winList.size() - 1));
//				driver.close();
//				winSet = driver.getWindowHandles();
//				winList = new ArrayList<String>(winSet);
//				contador++;
//				if (contador < 10) {
//					FunctionGeneric.executeCMD("taskkill -f -im iexplore.exe");
//					//closeWindows(0);
//				}
//			}

		} catch (Exception e) {

		}
	}
	
	public static void closeAlert() {
		try {
			if (validaAlert()) {
				FunctionGeneric.driver.switchTo().alert().accept();
			}
		} catch (Exception e) {
			System.out.println("Close Alert: " + e.getMessage());
		}
	}

	public static void dismissAlert() {
		try {
			if (validaAlert()) {
				FunctionGeneric.driver.switchTo().alert().dismiss();
			}
		} catch (Exception e) {
			System.out.println("Close Alert: " + e.getMessage());
		}
	}

	public static boolean validaAlert() {
		try {
			FunctionGeneric.driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public static void setupDriver(String browserType, String appURL) {
		executeCMD("taskkill -f -im cmd.exe");
		if (browserType.equals("firefox")) {
			FunctionGeneric.driver = initFirefoxDriver(appURL);
		} else if (browserType.equals("chrome")) {
			FunctionGeneric.driver = initChromeDriver(appURL);
		} else if (browserType.equals("ie")) {
			FunctionGeneric.driver = initIExplorerDriver(appURL);
		} else {
			throw new RuntimeException("Browser type unsupported : " + browserType);
		}
	}

	public static void setupDriver(String browserType) {
		FunctionGeneric.executeCMD("taskkill -f -im cmd.exe");
		try {
			if (browserType.equalsIgnoreCase("chrome")) {
				executeCMD("taskkill -f -im chromedriver.exe");
				// System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				options.addArguments("--disable-popup-blocking");
				driver = new ChromeDriver(options);
			} else if (browserType.equalsIgnoreCase("phantom")) {
				
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setJavascriptEnabled(true);
				caps.setCapability(
						PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX
								+ "userAgent", spoofUserAgent);

				caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
						new String[] { "--web-security=false",
								"--ssl-protocol=any", "--ignore-ssl-errors=true",
								"--webdriver-loglevel=INFO" });

				PhantomJSDriverService service = new PhantomJSDriverService.Builder()
				        .usingPort(8080)
				        .usingPhantomJSExecutable(new File("/usr/local/bin/phantomjs"))
				        .build();
				
				// executeCMD("taskkill -f -im chromedriver.exe");
				System.setProperty("phantomjs.binary.path", "./driver/phantomjs.exe");
				driver = new PhantomJSDriver();
			} else if (browserType.equalsIgnoreCase("ie")) {

				// executeCMD("taskkill -f -im IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability("requireWindowFocus", true);
				capabilities.setCapability("ignoreZoomSetting", true);
				driver = new InternetExplorerDriver(capabilities);

			} else {
				throw new Exception("Opcion no valida");
			}
		} catch (Exception e) {
			throw new RuntimeException("Browser type unsupported : " + browserType);
		}
	}

	public static WebDriver initFirefoxDriver(String appURL) {
		executeCMD("taskkill -f -im firefox.exe");
		// String path = CUR_DIR + "\\Drivers\\geckodriver.exe";
		// System.setProperty("webdriver.gecko.driver", path);
		System.out.println("Launching Firefox browser..");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	public static WebDriver initChromeDriver(String appURL) {
		executeCMD("taskkill -f -im chrome.exe");
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		// driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	public static WebDriver initIExplorerDriver(String appURL) {
		try {
			executeCMD("taskkill -f -im iexplore.exe");
			executeCMD("taskkill -f -im IEDriverServer.exe");
			// String path = CUR_DIR + "\\Drivers\\IEDriverServer.exe";
			// String path = "c:\\desarrollos\\Drivers\\IEDriverServer.exe";
			// System.setProperty("webdriver.ie.driver", path);
			// System.setProperty("webdriver.ie.driver",
			// "C:\\desarrollos\\Drivers\\IEDriverServer.exe");
			System.out.println("Launching IExplorer browser..");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("nativeEvents", false);

			System.out.println("Antes de crear el obj driver");

			WebDriver driver = new InternetExplorerDriver(capabilities);
			Thread.sleep(60000);
			System.out.println("despues de crear el objdriver");
			driver.manage().window().maximize();
			driver.navigate().to(appURL);
			System.out.println("PASA AAAAA");
		} catch (Exception e) {
			System.out.println("Error abrir url " + e.getMessage());
		}
		// driver.get(appURL);
		return driver;
	}

	public static Boolean validatePopUp() {

		Boolean exists = false;
		try {
			WebDriverWait wait = new WebDriverWait(FunctionGeneric.driver, 5 /* timeout in seconds */);
			if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
				System.out.println("alert was present");
				exists = true;
			}
		} catch (Exception e) {

			System.out.println("Error: " + e.toString());
		}
		return exists;
	}

	public static void executeCMD(String cmd) {
		try {

			Runtime.getRuntime().exec(cmd);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	public static void waitTOC(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	public static boolean waitWindows(int window, List<String> winList) throws InterruptedException {

		Set<String> winSet = FunctionGeneric.driver.getWindowHandles();
		int contador = 0;
		boolean flag = true;

		while (winList.size() != window) {
			Thread.sleep(2000);
			winSet = FunctionGeneric.driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			contador++;
			if (contador == 20) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public void table() {
		WebElement table = FunctionGeneric.driver.findElement(By.id("BarraMenuBarraMenuAdmision"));
		List<WebElement> childTable = table.findElements(By.tagName("td"));
		for (WebElement trElement : childTable) {
			if (trElement.getText().equals("Evaluador")) {
				trElement.click();
			} else if (trElement.getText().equals("Evaluador de Evrie.Solicitudes_Evrie")) {
				trElement.click();
			}
		}
	}

	public static WebDriver waitWindows(int windowsWait) {

		try {

			Set<String> winSet = FunctionGeneric.driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);

			if (windowsWait > 0) {
				if (!waitWindows(windowsWait, winList))
					return FunctionGeneric.driver;

				winSet = FunctionGeneric.driver.getWindowHandles();
				winList = new ArrayList<String>(winSet);
			}

			FunctionGeneric.driver.switchTo().window(winList.get(winList.size() - 1));

		} catch (Exception e) {
			System.out.println("Las ventanas esperadas no se han desplegado correctamente");
		}

		return FunctionGeneric.driver;
	}

	public static String Screenshot() throws UnknownHostException {
		String ruta = ""; 
		String computerName = InetAddress.getLocalHost().getHostName();;
		
		try {
			if (computerName.contains("server")) {
				ruta = "F:\\apps\\desarrollos\\Ejecucion_Automatizada\\Print\\";
				File folderServer = new File(ruta);
				if (!folderServer.exists()) {
					folderServer.mkdirs();
				}
			}else {
				ruta = "C:\\desarrollos\\Ejecucion_Automatizada\\Print\\";
				File folder = new File(ruta);
				if (!folder.exists()) {
					folder.mkdirs();
				}
			}
	
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String strDate = sdf.format(cal.getTime());
			Thread.sleep(1500);
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			strDate = strDate.replaceAll("/", "");
			strDate = strDate.replaceAll(":", "");
				
			ruta = ruta + strDate + ".png";
			ImageIO.write(image, "png", new File(ruta));


		} catch (Exception e) {
			System.out.println("Error al crear Screenshot: " + e.getMessage());
		}
	
		return ruta;
	}

	public static String validaMensajeAlert(String mensaje) {

		String flag = "OK";

		try {

			WebDriverWait wait = new WebDriverWait(FunctionGeneric.driver, 30);
			Alert myAlert = wait.until(ExpectedConditions.alertIsPresent());

			if (!myAlert.getText().contains(mensaje)) {
				flag = "Mensaje recibido : " + myAlert.getText();
				return flag;
			}
			myAlert.accept();

		} catch (Exception e) {
			flag = "La ventana para validar mensaje no se encuentra visible";
			System.out.println("Error Validar Mensaje Alert");
		}

		return flag;
	}
	
	public static String validaMensajeAlert2(String mensaje) {

		String flag = "OK";

		try {
		
		WebDriverWait wait = new WebDriverWait(FunctionGeneric.driver, 30);
		Alert myAlert = wait.until(ExpectedConditions.alertIsPresent());

		if (myAlert.getText().contains(mensaje)) {
		Thread.sleep(6000);
		myAlert.accept();
		// FunctionGeneric.driver.switchTo().alert().accept();
		}
		else
		{
		flag=myAlert.getText();
		Thread.sleep(6000);
		return flag;
		// FunctionGeneric.driver.close();
		} 
		 
		} catch (Exception e) {
		flag = "La ventana para r mensaje no se encuentra visible";
		System.out.println("Error Validar Mensaje Alert");
		}
		return flag;
	}

	public static int returnPositionDataCBO(Select combobox, String value) {
		int position = 0;
		try {
			List<WebElement> options = ((WebDriver) combobox).findElements(By.tagName("option"));

			for (WebElement option : options) {

				if (value.equalsIgnoreCase(option.getText().trim())) {
					System.out.println(option.getText().trim());
					position++;
					break;
				} else {
					position++;
				}

			}
		} catch (Exception e) {
			System.out.println("Error Combobox indice " + e.toString());
		}
		return position - 1;
	}

	public static int returnPositionDataCBOEquals(WebElement combobox, String value) {

		int position = 0;
		boolean flag = false;

		try {

			List<WebElement> options = combobox.findElements(By.tagName("option"));

			for (int i = 0; i < options.size(); i++) {
				// System.out.println(options.get(i).getText().trim());
				if (options.get(i).getText().trim().equals(value.trim())) {
					position = i;
					flag = true;
					break;
				}
			}

			if (flag == false)
				position = -1;

		} catch (Exception e) {
			System.out.println("Error Combobox indice " + e.toString());
		}
		return position;
	}

	public static int returnPositionDataCBO(WebElement combobox, String value) {

		int position = 0;
		boolean flag = false;

		try {

			List<WebElement> options = combobox.findElements(By.tagName("option"));

			for (int i = 0; i < options.size(); i++) {
				// System.out.println(options.get(i).getText());
				if (options.get(i).getText().trim().contains(value.trim())) {
					position = i;
					flag = true;
					break;
				}
			}

			if (flag == false)
				position = -1;

		} catch (Exception e) {
			System.out.println("Error Combobox indice " + e.toString());
		}
		return position;
	}

	public static String returnItemActual(WebElement combobox) {

		String valor = "";

		try {
			Select option = new Select(combobox);
			WebElement optionElment = option.getFirstSelectedOption();
			valor = optionElment.getText();
		} catch (Exception e) {
			System.out.println("Error Item Acutal Combobox " + e.toString());
		}
		return valor;
	}
	
	public static String validarTexto(String valor, String resultado) {

		String msg = "OK";

		try {

			if (!FunctionGeneric.driver.getPageSource().contains(valor))
				msg = "Error en " + resultado;

		} catch (Exception ex) {
			msg = "Error en " + resultado;
		}
		return msg;
	}

//	public static String validarTexto(String valor) {
//		
//		String msg = "OK";
//
//		if (!FunctionGeneric.driver.getPageSource().contains(valor))
//			msg = "No se ha encontrado el texto ";
//
//		return msg;
//	}

	public static void cerraALTF4() {
		try {

			new KeyboardClass();
			Robot robot = new Robot();

			KeyboardClass.KeyPressTecl("ALT");
			KeyboardClass.KeyPressTecl("F4");

			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_F4);
			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println("Error Cerrar ALT F4: " + e.getMessage());
		}
	}

	public static String clickObject(String nombreObj, String buscarPor, String value, String accion,
			WebDriver driver) {

		String msg = "OK";
		WebElement obj = null;

		try {

			switch (buscarPor) {
			case "name":
				obj = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
				break;

			case "id":
				obj = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
				break;

			case "value":
				obj = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.tagName(value)));
				break;

			case "link":
				obj = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.linkText(value)));
				break;

			case "css":
				obj = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(value)));
			
			case "class":
				obj = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.className(value)));
				
			default:
				break;
			}

			if (obj.isDisplayed()) {
				if (accion.equals("click")) {
					obj.click();
				} else if (accion.equals("dbclick")) {
					//
					Actions ac = new Actions(driver);
					ac.doubleClick(obj).perform();
				} else if (accion.equals("enter")) {
					obj.sendKeys(Keys.ENTER);
				}
			} else {
				msg = "El objeto '" + nombreObj + "' no se ha encontrado";
				return msg;
			}

		} catch (Exception e) {
			msg = "El objeto '" + nombreObj + "' no se ha encontrado";
		}

		return msg;
	}

	public static String clickObjectByXpath(String nombreObj, String tipoObj, String propiedad, String value,
			String accion, WebDriver driver) {

		String msg = "OK";
		WebElement objByXpath;

		try {

			if (propiedad.equals("text")) {
				objByXpath = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//" + tipoObj + "[" + propiedad + "() = '" + value + "']")));
			} else {
				objByXpath = (new WebDriverWait(driver, 30)).until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//" + tipoObj + "[@" + propiedad + "='" + value + "']")));
			}

			if (objByXpath.isDisplayed()) {
				if (accion.equals("click")) {
					objByXpath.click();
				} else if (accion.equals("click")) {
					//

					Actions ac = new Actions(driver);
					ac.doubleClick(objByXpath).perform();
					
				} else if (accion.equals("clickAndHold")) {
					Actions ac = new Actions(driver);
					ac.clickAndHold(objByXpath).build().perform();
					
				} else if (accion.equals("moveToElement")) {
					Actions ac = new Actions(driver);
					ac.moveToElement(objByXpath).build().perform();
										
					
				} else if (accion.equals("enter")) {
					objByXpath.sendKeys(Keys.ENTER);
				}
			} else {
				msg = "El objeto '" + nombreObj + "' no se ha encontrado";
				return msg;
			}

		} catch (Exception e) {
			msg = "El objeto '" + nombreObj + "' no se ha encontrado";
		}

		return msg;
	}

	public static String clickObjectByXpath(String nombreObj, String xpath, String accion,
			WebDriver driver) {

		String msg = "OK";

		try {

			WebElement objByXpath = (new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

			if (objByXpath.isDisplayed()) {
				if (accion.equals("click")) {
					objByXpath.click();
				} else if (accion.equals("click")) {
					//

					Actions ac = new Actions(driver);
					ac.doubleClick(objByXpath).perform();
					
				} else if (accion.equals("clickAndHold")) {
					Actions ac = new Actions(driver);
					ac.clickAndHold(objByXpath).build().perform();
					
				} else if (accion.equals("moveToElement")) {
					Actions ac = new Actions(driver);
					ac.moveToElement(objByXpath).build().perform();
										
					
				} else if (accion.equals("enter")) {
					objByXpath.sendKeys(Keys.ENTER);
				}
			} else {
				msg = "El objeto '" + nombreObj + "' no se ha encontrado";
				return msg;
			}

		} catch (Exception e) {
			msg = "El objeto '" + nombreObj + "' no se ha encontrado";
		}

		return msg;
	}
	
	public static String clickObjectByCssSelector(String nombreObj, String css, String accion,
			WebDriver driver) {

		String msg = "OK";

		try {

			WebElement objByCss = (new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));

			if (objByCss.isDisplayed()) {
				if (accion.equals("click")) {
					objByCss.click();
				} else if (accion.equals("click")) {
					//

					Actions ac = new Actions(driver);
					ac.doubleClick(objByCss).perform();
					
				} else if (accion.equals("clickAndHold")) {
					Actions ac = new Actions(driver);
					ac.clickAndHold(objByCss).build().perform();
					
				} else if (accion.equals("moveToElement")) {
					Actions ac = new Actions(driver);
					ac.moveToElement(objByCss).build().perform();
										
					
				} else if (accion.equals("enter")) {
					objByCss.sendKeys(Keys.ENTER);
				}
			} else {
				msg = "El objeto '" + nombreObj + "' no se ha encontrado";
				return msg;
			}

		} catch (Exception e) {
			msg = "El objeto '" + nombreObj + "' no se ha encontrado";
		}

		return msg;
	}
	
	public static String setTextObject(String nombreObj, String buscarPor, String value, String valorSet, String accion,
			boolean vacio, WebDriver driver) {
		String msg = "OK";
		WebElement obj = null;

		try {

			switch (buscarPor) {
			case "name":
				obj = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
				break;

			case "id":
				obj = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
			default:
				break;
			}

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", obj);

			if (obj.isDisplayed()) {
				if (accion.equals("click")) {
					obj.click();
				} else if (accion.equals("set")) {
					if (vacio) {
						if (obj.getAttribute("value").equals(""))
							obj.sendKeys(valorSet);
					} else {
						obj.sendKeys(valorSet);
					}
				} else if (accion.equals("enter")) {
					obj.sendKeys(Keys.ENTER);
				} else if (accion.equals("clean")) {
					obj.clear();
					// deleteAllText(obj);
				}
			} else {
				msg = "El objeto '" + nombreObj + "' no se ha encontrado";
				return msg;
			}

		} catch (Exception e) {
			msg = "El objeto '" + nombreObj + "' no se ha encontrado";
		}

		return msg;
	}

	public static String setTextObjectByXpath(String nombreObj, String tipoObj, String propiedad, String value,
			String valorSet, String accion, WebDriver driver) {

		String msg = "OK";

		try {

			WebElement objByXpath = (new WebDriverWait(driver, 30)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//" + tipoObj + "[@" + propiedad + "='" + value + "'")));

			if (objByXpath.isDisplayed()) {
				if (accion.equals("click")) {
					objByXpath.click();
				} else if (accion.equals("set")) {
					objByXpath.sendKeys(valorSet);
				} else if (accion.equals("enter")) {
					objByXpath.sendKeys(Keys.ENTER);
				} else if (accion.equals("clean")) {
					objByXpath.clear();
				}
			} else {
				msg = "El objeto '" + nombreObj + "' no se ha encontrado";
				return msg;
			}

		} catch (Exception e) {
			msg = "El objeto '" + nombreObj + "' no se ha encontrado";
		}

		return msg;
	}

	public static String setTextObjectByXpath(String nombreObj, String xpath, String valorSet, String accion,
			WebDriver driver) {

		String msg = "OK";

		try {

			WebElement objByXpath = (new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

			if (objByXpath.isDisplayed()) {
				if (accion.equals("click")) {
					objByXpath.click();
				} else if (accion.equals("set")) {
					objByXpath.sendKeys(valorSet);
				} else if (accion.equals("clean")) {
					objByXpath.clear();
				}
			} else {
				msg = "El objeto '" + nombreObj + "' no se ha encontrado";
				return msg;
			}

		} catch (Exception e) {
			msg = "El objeto '" + nombreObj + "' no se ha encontrado";
		}

		return msg;
	}

	public static String setTextObjectByCssSelector(String nombreObj, String css, String valorSet, String accion,
			WebDriver driver) {

		String msg = "OK";

		try {

			WebElement objByCss = (new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));

			if (objByCss.isDisplayed()) {
				if (accion.equals("click")) {
					objByCss.click();
				} else if (accion.equals("set")) {
					objByCss.sendKeys(valorSet);
				} else if (accion.equals("clean")) {
					objByCss.clear();
				}
			} else {
				msg = "El objeto '" + nombreObj + "' no se ha encontrado";
				return msg;
			}

		} catch (Exception e) {
			msg = "El objeto '" + nombreObj + "' no se ha encontrado";
		}

		return msg;
	}
	
	public static boolean findObject(String buscarPor, String value, WebDriver driver) {

		boolean msg = true;
		WebElement obj = null;

		try {

			switch (buscarPor) {
			case "name":
				obj = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
				break;

			case "id":
				obj = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
				break;

			case "tagname":
				obj = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.tagName(value)));
				break;

			case "link":
				obj = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.linkText(value)));
				break;
			case "class":
				obj = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.className(value)));
				break;
			default:
				break;
			}

			if (!obj.isDisplayed()) {
				msg = false;
				return msg;
			}

		} catch (Exception e) {
			System.out.println("El objeto no se ha encontrado");
			msg = false;
		}

		return msg;
	}

	public static void addScreenEvi(String paso, String estado) {
		try {
			BaseTest.arrStep.add(paso + "-" + Screenshot() + "-" + "Pass");
		} catch (UnknownHostException e) {
			System.out.println("Error en addScreenEvi: "+e.getMessage());
		}
	}

	public static String selectCBOEquals(String nombreObj, String buscarPor, String value, String dato, boolean vacio,
			WebDriver driver) {

		String msg = "OK";
		WebElement cbo = null;

		try {

			switch (buscarPor) {
			case "name":
				cbo = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
				break;
			case "id":
				cbo = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
				break;

			default:
				break;
			}

			if (cbo.isDisplayed()) {
				Select cboSelect = new Select(cbo);
				if (vacio) {
					if (returnItemActual(cbo).equals("")) {
						int index = FunctionGeneric.returnPositionDataCBOEquals(cbo, dato);
						cboSelect.selectByIndex(index);
					}
				} else {
					int index = FunctionGeneric.returnPositionDataCBOEquals(cbo, dato);
					cboSelect.selectByIndex(index);
				}

			} else {
				msg = "No se ha logrado seleccionar el dato '" + dato + "' en el combobox " + nombreObj;
			}

		} catch (Exception e) {
			msg = "No se ha logrado seleccionar el dato '" + dato + "' en el combobox " + nombreObj;
		}

		return msg;
	}

	public static String selecCBO(String nombreObj, String buscarPor, String value, String dato, boolean vacio,
			WebDriver driver) {

		String msg = "OK";
		WebElement cbo = null;

		try {

			switch (buscarPor) {
			case "name":
				cbo = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
				break;
			case "id":
				cbo = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
				break;
			default:
				break;
			}

			if (cbo.isDisplayed()) {
				Select cboSelect = new Select(cbo);
				if (vacio) {
					if (returnItemActual(cbo).equals("")) {
						int index = FunctionGeneric.returnPositionDataCBO(cbo, dato);
						cboSelect.selectByIndex(index);
					}
				} else {
					int index = FunctionGeneric.returnPositionDataCBO(cbo, dato);
					cboSelect.selectByIndex(index);
				}

			} else {
				msg = "No se ha logrado seleccionar el dato '" + dato + "' en el combobox " + nombreObj;
			}

		} catch (Exception e) {
			msg = "No se ha logrado seleccionar el dato '" + dato + "' en el combobox " + nombreObj;
		}

		return msg;
	}

	public static String selecCBOByVisibleText(String nombreObj, String buscarPor, String value, String dato,
			boolean vacio, WebDriver driver) {
		String msg = "OK";
		WebElement cbo = null;
		try {
			switch (buscarPor) {
			case "name":
				cbo = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
				break;
			case "id":
				cbo = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
				break;

			case "xpath":
				cbo = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));
				break;
			default:
				break;
			}

			if (cbo.isDisplayed()) {
				Select cboSelect = new Select(cbo);
				// if (vacio) {
				// if (returnItemActual(cbo).equals("")) {
				// int index = FunctionGeneric.returnPositionDataCBO(cbo, dato);
				// cboSelect.selectByIndex(index);
				// }
				// } else {
				// int index = FunctionGeneric.returnPositionDataCBO(cbo, dato);
				// cboSelect.selectByIndex(index);
				// }

				cboSelect.selectByVisibleText(dato);

			} else {
				msg = "No se ha logrado seleccionar el dato '" + dato + "' en el combobox " + nombreObj;
			}
		} catch (Exception e) {

		}
		return msg;
	}

	public static String selecCBOByIndex(String nombreObj, String buscarPor, String value, int index, boolean vacio,
			WebDriver driver) {
		String msg = "OK";
		WebElement cbo = null;
		try {
			switch (buscarPor) {
			case "name":
				cbo = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
				break;
			case "id":
				cbo = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
				break;

			case "xpath":
				cbo = (new WebDriverWait(driver, 30))
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));
				break;
			default:
				break;
			}

			if (cbo.isDisplayed()) {
				Select cboSelect = new Select(cbo);
				// if (vacio) {
				// if (returnItemActual(cbo).equals("")) {
				index = FunctionGeneric.returnPositionDataCBO(cbo, value);
				// cboSelect.selectByIndex(index);
				// }
				// } else {
				// int index = FunctionGeneric.returnPositionDataCBO(cbo, dato);
				// cboSelect.selectByIndex(index);
				// }

				cboSelect.selectByIndex(index);

			} else {
				msg = "No se ha logrado seleccionar el dato '" + index + "' en el combobox " + nombreObj;
			}
		} catch (Exception e) {

		}
		return msg;
	}

	public static String selecCBOXpath(String nombreObj, String tipoObj, String propiedad, String value, String dato,
			boolean vacio, WebDriver driver) {

		String msg = "OK";
		WebElement cbo = null;

		try {

			cbo = (new WebDriverWait(driver, 30)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//" + tipoObj + "[@" + propiedad + " ='" + value + "']")));

			if (cbo.isDisplayed()) {
				Select cboSelect = new Select(cbo);
				if (vacio) {
					if (returnItemActual(cbo).equals("")) {
						int index = FunctionGeneric.returnPositionDataCBO(cbo, dato);
						cboSelect.selectByIndex(index);
					}
				} else {
					int index = FunctionGeneric.returnPositionDataCBO(cbo, dato);
					cboSelect.selectByIndex(index);
				}

			} else {
				msg = "No se ha logrado seleccionar el dato '" + dato + "' en el combobox " + nombreObj;
			}

		} catch (Exception e) {
			msg = "No se ha logrado seleccionar el dato '" + dato + "' en el combobox " + nombreObj;
		}

		return msg;
	}

	public static void moveFileXLSX(String path, String nameFile) throws UnknownHostException {
		try {
		String pathFinal ="";
		String computerName = InetAddress.getLocalHost().getHostName();
		if (computerName.contains("server")) {
			pathFinal = "F:\\apps\\desarrollos\\Ejecucion_Automatizada\\" + path + "DATOS\\" + nameFile + ".xlsx";
			File folderServer = new File(pathFinal);
			if (!folderServer.exists()) {
				folderServer.mkdirs();
			}
		}else {
			pathFinal = "C:\\desarrollos\\Ejecucion_Automatizada\\" + path + "DATOS\\" + nameFile + ".xlsx";
			File folder = new File(pathFinal);
			if (!folder.exists()) {
				folder.mkdirs();
			}
		}
		
		if (computerName.contains("server")) {
			File rutaOriginalFichero = new File(
					"F:\\apps\\desarrollos\\Ejecucion_Automatizada\\Datos_Temporales\\" + nameFile + ".xlsx");
			File rutaDestinoFichero = new File(pathFinal.replace(" ", "_"));

			if (rutaDestinoFichero.exists())
				rutaDestinoFichero.delete();
			boolean estatus = rutaOriginalFichero.renameTo(rutaDestinoFichero);	
		}else {
			File rutaOriginalFichero = new File(
					"C:\\desarrollos\\Ejecucion_Automatizada\\Datos_Temporales\\" + nameFile + ".xlsx");
			File rutaDestinoFichero = new File(pathFinal.replace(" ", "_"));

			if (rutaDestinoFichero.exists())
				rutaDestinoFichero.delete();
			boolean estatus = rutaOriginalFichero.renameTo(rutaDestinoFichero);
		}
			
//			if (!estatus)
//				System.out.println("Error no se ha podido mover el  archivo del directorio");

		} catch (Exception e) {
			System.out.println("Error al mover el archivo");
		}
	}

	public static void deleteAllText(WebElement element) {
		try {
			Robot robot = new Robot();
			// element.click();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			robot.keyRelease(KeyEvent.VK_BACK_SPACE);
		} catch (Exception e) {
			System.out.println("ERROR AL ELIMINAR TODO EL TEXTO DEL CAMPO  " + e.getMessage());
		}
	}

	public static boolean findObjectXpath(String tipoObj, String propiedad, String value, WebDriver driver) {

		boolean flag = true;
		try {

			if (propiedad.equals("text")) {
				(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//" + tipoObj + "[" + propiedad + "() = '" + value + "']")));
			} else {
				(new WebDriverWait(driver, 30)).until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//" + tipoObj + "[@" + propiedad + " ='" + value + "']")));
			}

		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	public static boolean stateStep(String paso, String estado, ExtentTest extent, WebDriver driver) {

		boolean flag = true;

		try {

			if (estado.equals("OK")) {
				HtmlReportManager.getInstance().info(driver, extent, paso, false);
				HtmlReportManager.getInstance().pass(driver, extent, "", true);
				LogManager.getInstance().info("Paso " + paso + " exitoso");
				BaseTest.arrStep.add(paso + "-" + Screenshot() + "-" + "Pass");
			} else {
				HtmlReportManager.getInstance().info(driver, extent, paso, false);
				HtmlReportManager.getInstance().fail(driver, extent, estado, true);
				LogManager.getInstance().error(estado);
				BaseTest.arrStep.add(paso + "-" + Screenshot() + "-" + "Fail" + "-" + estado);
				flag = false;
			}

		} catch (Exception e) {
			System.out.println("Error en el estado del paso");
		}
		return flag;

	}

	public static void updateStateTestCase(boolean estado, String caso, ArrayList<String> arrStep) throws UnknownHostException {
		String computerName = InetAddress.getLocalHost().getHostName();
		String path = "";
		String pasos = "";
		try {
		if (computerName.contains("server")) {
			path = "F:\\apps\\desarrollos\\Ejecucion_Automatizada\\Datos_Temporales\\" + caso + "_R.txt";
			pasos = "";
		}else {
			path = "C:\\desarrollos\\Ejecucion_Automatizada\\Datos_Temporales\\" + caso + "_R.txt";
			pasos = "";
		}
				
		PrintWriter pw = new PrintWriter(path, "UTF-8");
		// PrintWriter pw1 = new PrintWriter("C:/new/hello.txt");
		for (int i = 0; i < arrStep.size(); i++) {
			pasos = pasos + "&" + arrStep.get(i);
		}
		pw.println("PASOS=" + pasos);
		pw.println("ESTADO=" + estado);
		pw.close();

		} catch (Exception e) {
		}
	}

	public static void irAlFondoDeLaPagina() {
		((JavascriptExecutor) FunctionGeneric.driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void irArribaDeLaPagina() {
		((JavascriptExecutor) FunctionGeneric.driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	public static Actions reconocerObjetosDinamicos(String nombreObj, String buscarPor, String value,
			WebDriver driver) {
		Actions actions = null;
		WebElement obj = null;
		try {
			switch (buscarPor) {
			case "name":
				obj = (new WebDriverWait(driver, 15))
						.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
				break;

			case "id":
				obj = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
				break;

			case "value":
				obj = (new WebDriverWait(driver, 15))
						.until(ExpectedConditions.presenceOfElementLocated(By.tagName(value)));
				break;

			case "link":
				obj = (new WebDriverWait(driver, 15))
						.until(ExpectedConditions.presenceOfElementLocated(By.linkText(value)));
				break;
			case "xpath":
				obj = (new WebDriverWait(driver, 15))
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));
				break;
			default:
				break;
			}

			if (obj.isDisplayed()) {
				actions = new Actions(driver);
				actions.moveToElement(obj).perform();
			}
		} catch (Exception e) {
			System.out.println("ERROR no se reconoce el objeto " + nombreObj);
		}
		return actions;
	}

	public static boolean validarFecha(String fecha) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
			formatoFecha.setLenient(false);
			formatoFecha.parse(fecha);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static boolean validarGeneracionDePDF(String buscarPor, String value) {
		boolean flag = true;
		try {
			Thread.sleep(2000);
			flag = FunctionGeneric.findObject(buscarPor, value, FunctionGeneric.driver);
		} catch (Exception e) {
			return false;
		}
		return flag;
	}

	/**
	 * @descripcion este metodo lee si un frame que despliega un pdf contiene una
	 *              ruta (http://), en caso de que si lo haga el resultado es
	 *              correcto, sino envia un mensaje de fallido
	 * @param numeroFrame
	 * @return
	 */
	public static String validarGeneracionDePDF(int numeroFrame) {
		String msg = "OK";
		try {
			
//			FunctionGeneric.driver.switchTo().frame((new WebDriverWait(FunctionGeneric.driver, 30)).until(
//					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']"))));
//			
			List<WebElement> iframe = driver.findElements(By.tagName("iframe"));
			
			System.out.println(iframe.size());
			
			String src = iframe.get(numeroFrame).getAttribute("src");
			
			Thread.sleep(2000);
			
			if (src.contains("http") || !src.contains("html")) {
				msg = "OK";
			}
			else
			{
				msg = "No se encuentra desplegado el PDF en pantalla";
			}
		} catch (Exception e) {
			msg = "Error no se encuentra ningun elemento de tipo frame";
		}
		return msg;
	}
	
	public static String validarPDF2() {

		String msg = "OK";
		try {
		
		for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
		}
		
		String getURL = FunctionGeneric.driver.getCurrentUrl();
		
		System.out.println("URL:" +getURL);
		
		
		if (getURL.contains("pdf"))
			return msg;
		
		} catch (Exception e) {
			msg = " PDF no encontrado";
		}
		return msg;

	}

	public static String buscarResultadoPorRegEx(String texto, String regEx, int nroGrupo) {
		String resultado = "";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(texto);

		while (matcher.find()) {
			resultado = matcher.group(nroGrupo);
		}
		return resultado;
	}

	public static int extraerMontoDisponible(By by) {
		int monto = 0;
		try {
			WebElement elemento = (new WebDriverWait(FunctionGeneric.driver, 15))
					.until(ExpectedConditions.presenceOfElementLocated(by));
			String msg = elemento.getText();
			msg = msg.replaceAll("[.$]", "").trim();
			monto = Integer.parseInt(msg);
		} catch (Exception e) {
			return 0;
		}
		return monto;
	}

	public static int calcularNuevoMontoDisponible(int montoAntes, int montoSolicitado) {
		int monto;
		try {
			monto = montoAntes - montoSolicitado;
		} catch (Exception e) {
			return 0;
		}
		return monto;
	}

	public static String compararMontoDisponibleActualizado(int nuevoMontoDiponible, int montoActualizado) {
		String msg = "OK";
		try {
			Thread.sleep(3000);
			if (nuevoMontoDiponible != montoActualizado) {
				msg = "el Monto disponible no ha sido actualizado | nuevo monto = " + nuevoMontoDiponible
						+ " | monto actualizado = " + montoActualizado + "";
			}
		} catch (Exception e) {
			msg = "Error no coinciden la cantidad de los montos";
		}
		return msg;
	}
	
	public static String compararValorPropiedad(String valorPropiedad) {
		String msg = "OK";
		try {
			Thread.sleep(3000);
			int valorPropiedadInt = Integer.parseInt(valorPropiedad);
			
			if (valorPropiedadInt < 750) {
				msg = "El precio de venta no puede ser inferior a 750 UF";
				
			}
		} catch (Exception e) {
			msg = "Error en el c?lculo precio venta";
		}
		return msg;
	}
	
	public static String validaPrecioVentaVSMontoPie(String valorPropiedad, String montoPie) {
		String msg = "OK";
		try {
			Thread.sleep(3000);
			int valorPropiedadInt = Integer.parseInt(valorPropiedad);
			int montoPieInt = Integer.parseInt(montoPie);
			
			if (valorPropiedadInt < montoPieInt) {
				msg = "Pie no debe ser mayor al precio de venta";
				
			}
		} catch (Exception e) {
			msg = "Error al comparar precio venta vs monto de pie";
		}
		return msg;
	}
	
	public static String validaPrecioVentaVSMontoPie2(String valorPropiedad) {
		String msg = "OK";
		try {
			Thread.sleep(3000);
			String montoPieString=driver.findElement(By.id("mf-pie")).getAttribute("Value");
			int valorPropiedadInt = Integer.parseInt(valorPropiedad);
			int montoPieInt = Integer.parseInt(montoPieString);
			
			if (valorPropiedadInt < montoPieInt) {
				msg = "Pie no debe ser mayor al precio de venta";
				
			}
		} catch (Exception e) {
			msg = "Error al comparar precio venta vs monto de pie";
		}
		return msg;
	}
	
	public static String compararMontoPie(String valorPropiedad, String montoPie) {
		String msg = "OK";
		try {
			Thread.sleep(3000);
			int valorPropiedadInt = Integer.parseInt(valorPropiedad);
			int montoPieInt = Integer.parseInt(montoPie);
			
			int diferenciaMontos = valorPropiedadInt - montoPieInt;
			
			if (diferenciaMontos <= 8610000) {
				msg = "Monto a financiar debe ser igual o mayor a 300 UF";
				
			}
		} catch (Exception e) {
			msg = "Error en el c?lculo de diferencia entre el precio venta vs monto pie";
		}
		return msg;
	}
	
	public static String compararMontoPie2(String valorPropiedad) {
		String msg = "OK";
		try {
			Thread.sleep(3000);
			String montoPieString=driver.findElement(By.id("mf-pie")).getAttribute("Value");
			int valorPropiedadInt = Integer.parseInt(valorPropiedad);
			int montoPieInt = Integer.parseInt(montoPieString);
			
			int diferenciaMontos = valorPropiedadInt - montoPieInt;
			
			if (diferenciaMontos <= 350) {
				msg = "Monto a financiar debe ser igual o mayor a 350 UF";
				
			}
		} catch (Exception e) {
			msg = "Error en el c?lculo de monto m?nimo de pie";
		}
		return msg;
	}
	
	public static void mergePDF(String pdf1, String pdf2, String rutaFinal) {
		try {
			PDFMergerUtility ut = new PDFMergerUtility();
			ut.addSource(pdf1);
			ut.addSource(pdf2);
			ut.setDestinationFileName(rutaFinal);
			ut.mergeDocuments();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String[][] getDataTxt(String caso) throws UnknownHostException {
		String computerName = InetAddress.getLocalHost().getHostName();
		String[][] matriz = null;
		String path = "";
		try {
		if (computerName.contains("server")) {
			path = "F:\\apps\\desarrollos\\Ejecucion_Automatizada\\Datos_Temporales\\" + caso + ".txt";
		}else {
			path = "C:\\desarrollos\\Ejecucion_Automatizada\\Datos_Temporales\\" + caso + ".txt";
		}		
		
		String line = null;
		String[] arrLine;
		int col = 2;
		
		int filas = cuentaFilasFile(path);
		BufferedReader in = new BufferedReader(new FileReader(path));
		matriz = new String[filas][col];

		for (int i = 0; i < filas; i++) {
			line = in.readLine();
			System.out.println(line);
			arrLine = line.split("=");
			matriz[i][0] = arrLine[0];
			if (arrLine.length > 1) {
				matriz[i][1] = arrLine[1];
			}

		}
			in.close();
		} catch (Exception e) {
			System.out.println("Error al leer datos de prueba: " + e.getMessage());
		}
		return matriz;
	}

	public static String leerMatrizTxt(String tag, String[][] matriz) {
		String value = null;
		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i][0].toUpperCase().equals(tag.toUpperCase())) {
				value = matriz[i][1];
				break;
			}
		}
		return value;
	}

	public static int cuentaFilasFile(String path) {
		int i = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			while ((in.readLine()) != null) {
				i++;
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Error al leer el archivo");
		}
		return i;
	}

	public static void setStat(boolean estado, String caso, ArrayList<String> arrStep) throws UnknownHostException {
		String computerName = InetAddress.getLocalHost().getHostName();
		String path = "";
		String pasos = "";
		try {
			if (computerName.contains("server")) {
				path = "F:\\apps\\desarrollos\\Ejecucion_Automatizada\\Datos_Temporales\\" + caso + "_R.txt";
			}else {
				path = "C:\\desarrollos\\Ejecucion_Automatizada\\Datos_Temporales\\" + caso + "_R.txt";
			}
			
		PrintWriter pw = new PrintWriter(path, "UTF-8");
		// PrintWriter pw1 = new PrintWriter("C:/new/hello.txt");
		for (int i = 0; i < arrStep.size(); i++) {
		pasos = pasos + "&" + arrStep.get(i);
		}
		pw.println("PASOS=" + pasos);
		pw.println("ESTADO=" + estado);
		pw.close();

		} catch (Exception e) {
		}
	}
	
	public static boolean findObjectTime(String buscarPor, String value, int tiempo, WebDriver driver) {

		boolean msg = true;
		WebElement obj = null;
		WebDriverWait wait = new WebDriverWait(driver, tiempo);
		try {

			switch (buscarPor) {
			case "name":
				obj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
				break;

			case "id":
				obj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
				break;

			case "tagname":
				obj = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(value)));
				break;

			case "link":
				obj = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(value)));
				break;

			default:
				break;
			}

			if (!obj.isDisplayed()) {
				msg = false;
				return msg;
			}

		} catch (Exception e) {
			System.out.println("El objeto no se ha encontrado");
			msg = false;
		}

		return msg;
	}
}