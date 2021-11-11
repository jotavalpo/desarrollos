package util;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Login.Login;

public abstract class BaseTest {

	protected String[][] matriz;
	protected Evidencia evi;
	protected static String nameClass;
	protected String lab;
	protected String rutaAlm;
	protected String estado;
	protected static String pathResultados;
	protected boolean flagState = true;
	protected PropertiesManager url;
	protected WebDriver driver;
	protected Login login;
	protected ExtentTest extent;
	protected static ExtentReports extentReports;

	public static ArrayList<String> arrStep = new ArrayList<String>();

	@BeforeClass
	public void beforeClass() {
		try {

			evi = new Evidencia();
			url = new PropertiesManager();

			nameClass = this.getClass().getName().substring(this.getClass().getPackage().getName().length() + 1,
					this.getClass().getName().length());

			matriz = FunctionGeneric.getDataTxt(nameClass);
			
			pathResultados = FunctionGeneric.leerMatrizTxt("Proyecto", matriz) + "\\"
					+ FunctionGeneric.leerMatrizTxt("Ciclo", matriz) + "\\";

			extentReports = HtmlReportManager.getInstance().getHtmlReport();
			extent = HtmlReportManager.getInstance().createTest(nameClass, "Prueba Funcional");
			HtmlReportManager.getInstance().addAuthor(extent, System.getProperty("user.name"));

		} catch (Exception e) {
			System.out.println("Error BeforeClass: " + e.getMessage());
		}
	}

	@AfterClass
	public void afterClass() {

		try {

//			Robot r = new Robot();

//			r.keyPress(KeyEvent.VK_ALT);
//			r.keyPress(KeyEvent.VK_F4);
//			Thread.sleep(1000);
//			r.keyRelease(KeyEvent.VK_ALT);
//			r.keyRelease(KeyEvent.VK_F4);
			Thread.sleep(1000);
			FunctionGeneric.closeWindows(0);
			evi.createPDF(BaseTest.arrStep, nameClass, pathResultados, flagState);
			FunctionGeneric.updateStateTestCase(flagState, nameClass, BaseTest.arrStep);
//			FunctionGeneric.driver.quit();
			extentReports.flush();
//			System.exit(0);
		} catch (Exception e) {
			System.out.println("Error AfterClass: " + e.getMessage());
		}
	}
}