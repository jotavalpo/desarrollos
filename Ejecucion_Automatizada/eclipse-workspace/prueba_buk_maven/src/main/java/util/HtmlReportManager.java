package util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class HtmlReportManager {

	private static HtmlReportManager instance;
	private ExtentReports extentReports;

	public static HtmlReportManager getInstance() throws Exception {
		if (instance == null) {
			instance = new HtmlReportManager();

			Path folderPath = Paths.get(PropertiesManager.getInstance().getFrameworkProperty("report_base"),
					BaseTest.pathResultados + "HTML\\");

			Path reportPath = Paths.get(folderPath.toString(), BaseTest.nameClass + ".html");
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath.toString().replaceAll(" ", "_"));
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setChartVisibilityOnOpen(false);
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config()
					.setDocumentTitle(PropertiesManager.getInstance().getFrameworkProperty("report_document_title"));
			htmlReporter.config().setEncoding("utf-8");
			htmlReporter.config().setReportName(PropertiesManager.getInstance().getFrameworkProperty("report_name"));

			instance.extentReports = new ExtentReports();
			instance.setHierarchy();
			instance.extentReports.attachReporter(htmlReporter);

		}
		return instance;
	}

	public ExtentReports getHtmlReport() {
		return extentReports;
	}

	public ExtentTest createTest(String name, String description) {
		return extentReports.createTest(name, description);
	}

	public void debug(WebDriver driver, ExtentTest extentTest, String message, boolean capture) throws Exception {
		addMessage(driver, extentTest, Status.DEBUG, message, capture);
	}

	public void info(WebDriver driver, ExtentTest extentTest, String message, boolean capture) throws Exception {
		addMessage(driver, extentTest, Status.INFO, message, capture);
	}

	public void skip(WebDriver driver, ExtentTest extentTest, String message, boolean capture) throws Exception {
		addMessage(driver, extentTest, Status.SKIP, message, capture);
	}

	public void pass(WebDriver driver, ExtentTest extentTest, String message, boolean capture) throws Exception {
		addMessage(driver, extentTest, Status.PASS, message, capture);
	}

	public void fail(WebDriver driver, ExtentTest extentTest, String message, boolean capture) throws Exception {
		addMessage(driver, extentTest, Status.FAIL, message, capture);
	}

	public void warning(WebDriver driver, ExtentTest extentTest, String message, boolean capture) throws Exception {
		addMessage(driver, extentTest, Status.WARNING, message, capture);
	}

	public void error(WebDriver driver, ExtentTest extentTest, String message, boolean capture) throws Exception {
		addMessage(driver, extentTest, Status.ERROR, message, capture);
	}

	public void faltal(WebDriver driver, ExtentTest extentTest, String message, boolean capture) throws Exception {
		addMessage(driver, extentTest, Status.FATAL, message, capture);
	}

	public void addMessage(WebDriver driver, ExtentTest extentTest, Status status, String message, boolean capture)
			throws Exception {
		if (capture)
			extentTest.log(status, message, addScreenCapture(driver));
		else
			extentTest.log(status, message);
	}

	public void addCategory(ExtentTest extentTest, String category) {
		String[] categories = category.split(",");
		extentTest.assignCategory(categories);
	}

	public void addAuthor(ExtentTest extentTest, String author) {
		String[] authors = author.split(",");
		extentTest.assignAuthor(authors);
	}

	public void addSystemInfo(ExtentTest extentTest, String info) {
		String[] infos = info.split(",");
		extentTest.assignCategory(infos);
	}

	public void setSystemInfo(ExtentReports extentReports, String info, String value) {
		extentReports.setSystemInfo(info, value);
	}

	public void setInfoBrowser(ExtentReports extentReports, String navegador, String version) {
		extentReports.setSystemInfo("Navegador", navegador);
		extentReports.setSystemInfo("Version", version);
	}

	public void setInfoTest(ExtentReports extentReports, String ambiente, String url) {
		extentReports.setSystemInfo("Ambiente", ambiente);
		extentReports.setSystemInfo("URl", url);
	}

	private void setHierarchy() {
		List<Status> statusHierarchy = Arrays.asList(Status.FATAL, Status.FAIL, Status.ERROR, Status.SKIP, Status.PASS,
				Status.WARNING, Status.INFO, Status.DEBUG);
		this.extentReports.config().statusConfigurator().setStatusHierarchy(statusHierarchy);
	}

	private MediaEntityModelProvider addScreenCapture(WebDriver driver) throws Exception {
		Random random = new Random();
		String imageName = String.valueOf(random.nextInt(10000));
		Path imagePath = Paths.get("img", imageName + ".jpg");
		Path reportPath = Paths.get(PropertiesManager.getInstance().getFrameworkProperty("report_base"),
				BaseTest.pathResultados + "HTML\\", imagePath.toString());

		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(reportPath.toString().replaceAll(" ", "_")));
		return MediaEntityBuilder.createScreenCaptureFromPath(imagePath.toString()).build();
	}

}
