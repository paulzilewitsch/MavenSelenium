package testcases;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTest 
{
	 static WebDriver driver;
	 static String nodeUrl;
	 static String driverPath; //test
	 
	 @BeforeTest
	 public void setup() throws MalformedURLException 
	 {		 
//		 nodeUrl = "http://172.16.20.161:4444/wd/hub";
//		 DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//		 capabilities.setBrowserName("chrome");
//		 capabilities.setPlatform(Platform.WIN8);
//		 
//		 ChromeOptions options = new ChromeOptions();
//		 options.addArguments("disable-infobars");
//         capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//		 
//		 driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
		 
		 nodeUrl = "http://172.16.20.161:4444/wd/hub";
		 DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		 capabilities.setBrowserName("firefox");
		 capabilities.setPlatform(Platform.WIN8);
		 
		 driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
	 }
	 
	 @Test
	 public void simpleTest() 
	 {
		 System.out.println("Test ist gestartet");
		 driver.get("https://www.edureka.co/");
		 Assert.assertEquals("Instructor Led Online Courses with 24x7 On-Demand Support | Edureka", driver.getTitle());
	 }
	 
	 @AfterTest
	 public void afterTest() 
	 {
		 System.out.println("Test ist fertig");
		 driver.quit();
	 }
}
