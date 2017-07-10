package testcases;

import org.testng.Assert;
import org.testng.AssertJUnit;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCalculator {

	  private WebDriver driver;
	  public  String baseURL;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  public String winHandleBefore;
	  static String nodeUrl;
	  
	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception 
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

	  @Test (timeOut = 5000,  priority=0)
	  public void StartApplication() throws Exception{
		  
			//Startseite is called and loaded in  Firefox
			driver.get(baseURL); 
			AssertJUnit.assertEquals(driver.getTitle(), "Baufinanzierung – das beste Darlehens-Angebot von 250 Banken - Commerzbank");  
		    driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	  }
	  

	  
	  @Test (timeOut = 5000,  priority=1) 
	  public void testBaufinanzierung_Antrag_Start() throws Exception {


		// Store the current window handle
		  winHandleBefore = driver.getWindowHandle();

		// Perform the click operation that opens new window
		driver.findElement(By.cssSelector("li.flex-active-slide > a.center.top")).click();
		

		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
		// Page : Baufinanzierung Onlineantrag -Start
		
		AssertJUnit.assertEquals("Commerzbank Finanzierungsanfrage", driver.getTitle());
	    new Select(driver.findElement(By.id("cse.venture.reason"))).selectByVisibleText("Kauf eines Neubaus vom Bauträger");
	    new Select(driver.findElement(By.id("cse.estate.combiType"))).selectByVisibleText("Eigentumswohnung");
	    new Select(driver.findElement(By.id("cse.estate.utilization"))).selectByVisibleText("selbst bewohnen");
	    if (!driver.findElement(By.id("radioButton_cse.estate.notfound_0")).isSelected()) {
	      driver.findElement(By.id("radioButton_cse.estate.notfound_0")).click();
	    };
	    new Select(driver.findElement(By.id("cse.estate.buyTimeline"))).selectByVisibleText("");
	    driver.findElement(By.id("cse.estate.zip")).clear();
	    driver.findElement(By.id("cse.estate.zip")).sendKeys("01097");
	    driver.findElement(By.id("cse.venture.priceBuilding")).clear();
	    driver.findElement(By.id("cse.venture.priceBuilding")).sendKeys("260000");
	    driver.findElement(By.id("cse.venture.originalFunding.loans[1].amount")).clear();
	    driver.findElement(By.id("cse.venture.originalFunding.loans[1].amount")).sendKeys("50000");
	    new Select(driver.findElement(By.id("cse.venture.originalFunding.loans[1].maturity"))).selectByVisibleText("10 Jahre");
	    if (!driver.findElement(By.name("amortType")).isSelected()) {
	      driver.findElement(By.name("amortType")).click();
	    };
	    
	 
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
	    
	  }
	  
	  @Test (timeOut = 5000,  priority=2)
	  public void testBaufinanzierung_Antrag_Zinsen_Check () throws Exception {

	  
	    //Page : Baufinanzierung Onlineantrag - Zinsen Check
	  
	    AssertJUnit.assertEquals(driver.findElement(By.cssSelector("td.section")).getText(), "Zinscheck");
	    driver.findElement(By.xpath("//tr[@id='b2cAppButtonLine']/td[2]/a/span")).click();
	 
	  }
	  
	  
	  @Test (timeOut = 5000,  priority=3)
	  public void testBaufinanzierung_Antrag_Persoenlichen_Daten () throws Exception {
	  
	   // Page : Baufinanzierung Onlineantrag  - Persönlichen Daten
	    
	    AssertJUnit.assertEquals(driver.findElement(By.cssSelector("td.section")).getText(), "Persönliche Angaben");
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
	    new Select(driver.findElement(By.cssSelector("select[name=\"cse.mainApplicant.netSalary\"]"))).selectByVisibleText("monatlich");
	    driver.findElement(By.id("cse.venture.ventureDesc")).clear();
	    driver.findElement(By.id("cse.venture.ventureDesc")).sendKeys("Einmal Test");
	    if (!driver.findElement(By.id("radioButton_cse.venture.isCobaCustomer_0")).isSelected()) {
	      driver.findElement(By.id("radioButton_cse.venture.isCobaCustomer_0")).click();
	    };
	    
	    
	    driver.findElement(By.id("cse.mainApplicant.contactDate")).clear();
	    driver.findElement(By.id("cse.mainApplicant.contactDate")).sendKeys("02.09.2017");
	    
	    
	    // Close the new window, if that window no more required
	    driver.close();

	    // Switch back to original browser (first window)
	    driver.switchTo().window(winHandleBefore);
	    
	  }

	  
	@AfterClass (alwaysRun = true, timeOut = 5000)
	  public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      Assert.fail(verificationErrorString);
	    }
	  }

	  
	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }


}
