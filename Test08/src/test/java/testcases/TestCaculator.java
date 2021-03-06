package testcases;

import static org.junit.Assert.*;

//import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
//import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCaculator 
{
	private static WebDriver driver;
//	private static String nodeUrl;
	private static StringBuffer verificationErrors = new StringBuffer();
	private static String baseURL;

	public String winHandleBefore;

	@BeforeClass
	public static void setUp() throws Exception 
	{
//		nodeUrl = "http://172.16.20.161:4444/wd/hub";
//
//		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//		capabilities.setBrowserName("firefox");
//		capabilities.setPlatform(Platform.WINDOWS);
//
//		driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
//		
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		driver = new FirefoxDriver();
		baseURL = "https://www.commerzbank.de/portal/de/privatkunden/produkte/finanzieren-und-erwerben/baufinanzierung/baufinanzierung.html";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void StartApplication() throws Exception 
	{
		driver.get(baseURL);
		assertEquals(driver.getTitle(), "Baufinanzierung – das beste Darlehens-Angebot von 250 Banken - Commerzbank");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	@Test(timeout = 50000)
	public void testBaufinanzierung_Antrag() throws Exception 
	{
		driver.get(baseURL);
		Assert.assertEquals(driver.getTitle(),
				"Baufinanzierung – das beste Darlehens-Angebot von 250 Banken - Commerzbank");

		driver.findElement(By.cssSelector("li.flex-active-slide > a.center.top")).click();

		String parentWindow = driver.getWindowHandle();

		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) 
			{
				driver.switchTo().window(windowHandle);
				Formularausfuellen();
				driver.close();
				driver.switchTo().window(parentWindow);
			}
		}
	}

	public void Formularausfuellen() 
	{
		System.out.println("nach  for condt. winhandle");

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cse.venture.reason")));

		Assert.assertEquals("Commerzbank Finanzierungsanfrage", driver.getTitle());

		new Select(driver.findElement(By.id("cse.venture.reason")))
				.selectByVisibleText("Kauf eines Neubaus vom Bauträger");
		new Select(driver.findElement(By.id("cse.estate.combiType"))).selectByVisibleText("Eigentumswohnung");
		new Select(driver.findElement(By.id("cse.estate.utilization"))).selectByVisibleText("selbst bewohnen");
		if (!driver.findElement(By.id("radioButton_cse.estate.notfound_0")).isSelected()) {
			driver.findElement(By.id("radioButton_cse.estate.notfound_0")).click();
		}
		;
		new Select(driver.findElement(By.id("cse.estate.buyTimeline"))).selectByVisibleText("");
		driver.findElement(By.id("cse.estate.zip")).clear();
		driver.findElement(By.id("cse.estate.zip")).sendKeys("01097");
		driver.findElement(By.id("cse.venture.priceBuilding")).clear();
		driver.findElement(By.id("cse.venture.priceBuilding")).sendKeys("260000");
		driver.findElement(By.id("cse.venture.originalFunding.loans[1].amount")).clear();
		driver.findElement(By.id("cse.venture.originalFunding.loans[1].amount")).sendKeys("50000");
		new Select(driver.findElement(By.id("cse.venture.originalFunding.loans[1].maturity")))
				.selectByVisibleText("10 Jahre");
		if (!driver.findElement(By.name("amortType")).isSelected()) {
			driver.findElement(By.name("amortType")).click();
		}
		;

		driver.findElement(By.id("cse.venture.originalFunding.loans[1].amortisation")).clear();
		driver.findElement(By.id("cse.venture.originalFunding.loans[1].amortisation")).sendKeys("2");

		driver.findElement(By.id("togglelinkoptionalOther")).click();

		driver.findElement(By.id("cse.estate.city")).clear();
		driver.findElement(By.id("cse.estate.city")).sendKeys("Dresden");
		driver.findElement(By.id("cse.venture.originalFunding.equityCash")).clear();
		driver.findElement(By.id("cse.venture.originalFunding.equityCash")).sendKeys("0");
		driver.findElement(By.id("cse.venture.priceDue")).clear();
		driver.findElement(By.id("cse.venture.priceDue")).sendKeys("28.07.2017");

		driver.findElement(By.xpath("//tr[@id='b2cAppButtonLine']/td[2]/a/span")).click();

		// Page : Baufinanzierung Onlineantrag - Zinsen Check

		Assert.assertEquals(driver.findElement(By.cssSelector("td.section")).getText(), "Zinscheck");
		driver.findElement(By.xpath("//tr[@id='b2cAppButtonLine']/td[2]/a/span")).click();

		// Page : Baufinanzierung Onlineantrag - Persönlichen Daten

		Assert.assertEquals(driver.findElement(By.cssSelector("td.section")).getText(), "Persönliche Angaben");
		new Select(driver.findElement(By.id("cse.mainApplicant.sex"))).selectByVisibleText("Herr");
		driver.findElement(By.id("cse.mainApplicant.fname")).clear();
		driver.findElement(By.id("cse.mainApplicant.fname")).sendKeys("Roman");
		driver.findElement(By.id("cse.mainApplicant.lname")).clear();
		driver.findElement(By.id("cse.mainApplicant.lname")).sendKeys("Polanski");
		driver.findElement(By.id("cse.mainApplicant.birthdate")).clear();
		driver.findElement(By.id("cse.mainApplicant.birthdate")).sendKeys("02.02.1952");
		driver.findElement(By.id("cse.mainApplicant.zip")).clear();
		driver.findElement(By.id("cse.mainApplicant.zip")).sendKeys("01099");
		driver.findElement(By.id("cse.mainApplicant.city")).clear();
		driver.findElement(By.id("cse.mainApplicant.city")).sendKeys("Frankfurt am Main");
		driver.findElement(By.id("cse.mainApplicant.phoneEvening")).clear();
		driver.findElement(By.id("cse.mainApplicant.phoneEvening")).sendKeys("00497272727272");
		driver.findElement(By.id("cse.mainApplicant.email")).clear();
		driver.findElement(By.id("cse.mainApplicant.email")).sendKeys("rpolanski@frankfurt.de");
		new Select(driver.findElement(By.id("cse.mainApplicant.jobStatus"))).selectByVisibleText("Elternzeit");
		driver.findElement(By.id("cse.mainApplicant.netSalary")).clear();
		driver.findElement(By.id("cse.mainApplicant.netSalary")).sendKeys("45000");
		new Select(driver.findElement(By.cssSelector("select[name=\"cse.mainApplicant.netSalary\"]")))
				.selectByVisibleText("monatlich");
		driver.findElement(By.id("cse.venture.ventureDesc")).clear();
		driver.findElement(By.id("cse.venture.ventureDesc")).sendKeys("Einmal Test");
		if (!driver.findElement(By.id("radioButton_cse.venture.isCobaCustomer_0")).isSelected()) {
			driver.findElement(By.id("radioButton_cse.venture.isCobaCustomer_0")).click();
		}
		;

		driver.findElement(By.id("cse.mainApplicant.contactDate")).clear();
		driver.findElement(By.id("cse.mainApplicant.contactDate")).sendKeys("02.09.2017");
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString))
			Assert.fail(verificationErrorString);
	}
}
