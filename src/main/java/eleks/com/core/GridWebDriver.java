package eleks.com.core;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridWebDriver {

	private static final String CHROME = "chrome";
	private static final String FIREFOX = "firefox";
	private static final String IE = "ie";

	private static RemoteWebDriver webDriver;
	
	static DesiredCapabilities cap = null;
	
	private GridWebDriver() {

	}
	public static WebDriver getInstance() {
		if (webDriver == null) {
			cap = DesiredCapabilities.firefox();
			cap.setBrowserName(DesiredCapabilities.firefox()
					.getBrowserName());
		}
		return webDriver;	
	}

	public static RemoteWebDriver getInstance(String browserName,
			String remouteURL) throws Exception {
		if (webDriver == null) {
			if (FIREFOX.equals(browserName)) {
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName(DesiredCapabilities.firefox()
						.getBrowserName());
			} else if (CHROME.equals(browserName)) {
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName(DesiredCapabilities.chrome()
						.getBrowserName());
			} else if (IE.equals(browserName)) {
				cap = DesiredCapabilities.internetExplorer();
			} else
				throw new Exception("invalid browser setup");
			webDriver = new RemoteWebDriver(new URL(remouteURL), cap);
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		return webDriver;
	}

	public static void killDriverInstance() {
		if (webDriver != null) {
			webDriver.close();
			webDriver.quit();
		}
	}

}
