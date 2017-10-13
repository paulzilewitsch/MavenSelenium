package cucumberSteps;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class facebook_login {
	
	private static WebDriver driver;
	private static StringBuffer verificationErrors = new StringBuffer();
	private static String baseURL;
	private static String nodeUrl;

	public String winHandleBefore;
	
	@Given ("^user navigates to Facebook$")
	public void Open_firefox_and_start_application() throws Throwable 
	{
//		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		nodeUrl = "http://172.16.20.161:4444/wd/hub";

		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setBrowserName("firefox");
		capabilities.setPlatform(Platform.WINDOWS);

		driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
		
		baseURL = "https://www.facebook.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get(baseURL);
		assertEquals(driver.getTitle(), "Facebook – Anmelden oder Registrieren");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}
		
		@When ("^user logs in using Username as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
		
		public void I_enter_Username_as_and_Password_as(String arg1, String arg2) {
		   driver.findElement(By.id("email")).sendKeys(arg1);
		   driver.findElement(By.id("pass")).sendKeys(arg2);
		   driver.findElement(By.id("u_0_v")).click(); 
		} 
		
		@Then("^login should be unsuccessful$")
		public void validateRelogin() { 
			   if(driver.getCurrentUrl().equalsIgnoreCase(
			      "https://www.facebook.com/login.php?login_attempt=1&lwv=110")){ 
			         System.out.println("Test Pass");
			   } else { 
			      System.out.println("Test Failed"); 
			   } 
			   driver.close(); 
			}
}
