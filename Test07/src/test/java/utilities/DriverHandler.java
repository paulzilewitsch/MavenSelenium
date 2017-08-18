package utilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.*;

public class DriverHandler {
	public static DesiredCapabilities capabilities;
	public static String browserName;
	public static String userName;
	public static WebDriver driver;
	private static String hubUrl;

	public static WebDriver getDriver(WebDriver mydriver, String browserName, String baseURL) throws Exception {
		// INFO:
		// https://stackoverflow.com/questions/7883542/getting-the-computer-name-in-java
		userName = System.getProperty("user.name");

		if (userName == "Node") {

			// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			// capabilities.setBrowserName("chrome");
			// capabilities.setPlatform(Platform.WINDOWS);
			//
			// ChromeOptions options = new ChromeOptions();
			// options.addArguments("disable-infobars");
			// capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			capabilities.setPlatform(Platform.WINDOWS);

			hubUrl = "http://172.16.20.161:4444/wd/hub";
			mydriver = new RemoteWebDriver(new URL(hubUrl), capabilities);
			mydriver.manage().window().maximize();
			mydriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		} 
		else {
			// local

			if (browserName == "IE") {
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				capabilities.setJavascriptEnabled(true);

				System.setProperty("webdriver.ie.driver", "C:\\Users\\" + userName
						+ "\\Testautomatisierung\\Libraries\\IEDriverServer_x64_3.4.0\\IEDriverServer.exe");

				mydriver = new InternetExplorerDriver(capabilities);
				mydriver.manage().window().maximize();
				mydriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			} else if (browserName == "Firefox") {
				System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
				mydriver = new FirefoxDriver();
			} else if (browserName == "Chrome") {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\" + userName
						+ "\\Testautomatisierung\\Libraries\\chromedriver_win32\\chromedriver.exe");
			}
			mydriver.get(baseURL);
		}
		driver = mydriver;
		System.out.println("browser: " + browserName + " | user: " + userName + "\n");
		return driver;

	}
}