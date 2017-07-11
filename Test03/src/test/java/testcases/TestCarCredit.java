package testcases;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestCarCredit
{
	private static WebDriver driver;
	private static String nodeUrl;
	private static StringBuffer verificationErrors = new StringBuffer();
	private static String baseURL;
	
	public String winHandleBefore;
	
	@BeforeClass
	public static void setUp() throws Exception 
	{  	
		nodeUrl = "http://172.16.20.161:4444/wd/hub";
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setBrowserName("chrome");
		capabilities.setPlatform(Platform.WINDOWS);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
		baseURL="https://www.commerzbank.de/portal/de/privatkunden/produkte/finanzieren-und-erwerben/baufinanzierung/baufinanzierung.html";  
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);;
	}
	
	
	@Test
	public void start_Application() throws Exception 
	{
		  driver.get(baseURL);  
		  assertEquals(driver.getTitle(), "Autokredit – damit Sie schneller ans Ziel kommen - Commerzbank","Titel was verified" );
		  driver.findElement(By.linkText("Termin vereinbaren")).click();
	}

	  @Test
	  public void testBaufinanzierung_Antrag_Terminvereinbarung() throws Exception 
	  {
		  driver.findElement(By.id("elem666785")).clear(); 
		  driver.findElement(By.id("elem666785")).sendKeys("07.08.2017");
		  driver.findElement(By.id("elem666786")).clear(); 
		  driver.findElement(By.id("elem666786")).sendKeys("11:00"); 
		  driver.findElement(By.id("elem666787")).clear(); 
		  driver.findElement(By.id("elem666787")).sendKeys("12:00"); 
		  driver.findElement(By.id("elem666756")).clear(); 
		  driver.findElement(By.id("elem666756")).sendKeys("Dresden"); 
		  driver.findElement(By.id("elem666780")).clear(); 
		  driver.findElement(By.id("elem666780")).sendKeys("Autofinanzierung"); 
		  
		  WebElement webElement1 = driver.findElement(By.id("elem666780"));//You can use xpath, ID or name whatever you like
		  webElement1.sendKeys(Keys.TAB);
		  
		  WebElement webElement2 = driver.findElement(By.xpath("//div[@id='elem666784Wrapper']/div/fieldset/ul/li[2]/label[1]"));	
		  webElement2.sendKeys(Keys.ARROW_RIGHT);
		 
		 // driver.findElement(By.xpath("//div[@id='elem666784Wrapper']/div/fieldset/ul/li[2]/label[1]")).click();

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
	  
	  @Test
	  public void testBaufinanzierung_Antrag_Anfrage_Bestaetigung() throws Exception 
	  {
		  //Anfrage Bestätigung
		  
		    assertEquals(driver.findElement(By.id("elem666755_ueberschrift")).getText(), "Ihre Terminvereinbarung");
		    assertEquals(driver.findElement(By.xpath("//form[@id='panel']/div/div/div/div/span[8]")).getText(), "Dresden");
		    assertEquals(driver.findElement(By.xpath("//form[@id='panel']/div/div/div/div/span[10]")).getText(), "Autofinanzierung");
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
