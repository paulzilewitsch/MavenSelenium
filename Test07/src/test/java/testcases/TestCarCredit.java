package testcases;

import static org.junit.Assert.assertEquals;
import utilities.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCarCredit 
{
	private static WebDriver driver;
	public static String baseURL;
	private static StringBuffer verificationErrors = new StringBuffer();
	public static String browserName;
	WebDriverWait wait  = new WebDriverWait(driver,30);

	@BeforeClass
	public static void setUp() throws Exception 
	{		
		baseURL = "https://www.commerzbank.de/portal/de/privatkunden/produkte/finanzieren-und-erwerben/autokredit/autokredit.html";
		browserName= "Firefox";	
		
		driver = DriverHandler.getDriver(driver, browserName, baseURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	}

	@Test(timeout = 50000)
	public void step_1_start_Application() throws Exception 
	{
		driver.get(baseURL);
		System.out.println("setup ");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Termin vereinbaren")));
		driver.findElement(By.linkText("Termin vereinbaren")).click();
	}

	@Test(timeout = 50000)
	public void step_2_testBaufinanzierung_Antrag_Terminvereinbarung() throws Exception 
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("elem666785")));
		driver.findElement(By.id("elem666785")).clear();
		driver.findElement(By.id("elem666785")).sendKeys("09.09.2017");
		driver.findElement(By.id("elem666786")).clear();
		driver.findElement(By.id("elem666786")).sendKeys("11:00");
		driver.findElement(By.id("elem666787")).clear();
		driver.findElement(By.id("elem666787")).sendKeys("12:00");
		driver.findElement(By.id("elem666756")).clear();
		driver.findElement(By.id("elem666756")).sendKeys("Dresden");
		driver.findElement(By.id("elem666780")).clear();
		driver.findElement(By.id("elem666780")).sendKeys("Autofinanzierung");
		// Radiobutton 'Frau'
		driver.findElement(By.xpath("//label[@for='elem666750']")).click();
		driver.findElement(By.id("elem666766")).clear();
		driver.findElement(By.id("elem666766")).sendKeys("Jop");
		driver.findElement(By.id("elem666744")).clear();
		driver.findElement(By.id("elem666744")).sendKeys("Sieben");
		driver.findElement(By.id("elem666753")).clear();
		driver.findElement(By.id("elem666753")).sendKeys("Mainstrasse");
		driver.findElement(By.id("elem666761")).clear();
		driver.findElement(By.id("elem666761")).sendKeys("772");
		driver.findElement(By.id("elem666762")).clear();
		driver.findElement(By.id("elem666762")).sendKeys("727272");
		driver.findElement(By.id("elem666777")).clear();
		driver.findElement(By.id("elem666777")).sendKeys("Frankfurt");
		driver.findElement(By.id("elem666772")).clear();
		driver.findElement(By.id("elem666772")).sendKeys("Deutschland");
		driver.findElement(By.id("elem666775")).clear();
		driver.findElement(By.id("elem666775")).sendKeys("00339272627272727272");
		driver.findElement(By.id("elem666782")).clear();
		driver.findElement(By.id("elem666782")).sendKeys("gilberto@frankfurttest.de");
		driver.findElement(By.xpath("//div[@id='panelActions']/div/span/input")).click();
	}

	@Test(timeout = 50000)
	public void step_3_testBaufinanzierung_Antrag_Anfrage_Bestaetigung() throws Exception 
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@id='panel']/div/div/div/div/span[8]")));
		assertEquals(driver.findElement(By.id("elem666755_ueberschrift")).getText(), "Ihre Terminvereinbarung");
		assertEquals(driver.findElement(By.xpath("//form[@id='panel']/div/div/div/div/span[8]")).getText(), "Dresden");
		assertEquals(driver.findElement(By.xpath("//form[@id='panel']/div/div/div/div/span[10]")).getText(),"Autofinanzierung");
	}

	@AfterClass
	public static void tearDown() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.quit();
		String verificationErrorString = verificationErrors.toString();

		if (!"".equals(verificationErrorString))
			Assert.fail(verificationErrorString);
	}

}
