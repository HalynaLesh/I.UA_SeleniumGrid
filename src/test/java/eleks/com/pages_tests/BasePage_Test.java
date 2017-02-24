package eleks.com.pages_tests;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import eleks.com.EmailBuilder.Email;
import eleks.com.EmailBuilder.EmailBuilder;
import eleks.com.core.ConfigProperties;
import eleks.com.core.ConnectMySQL;
import eleks.com.core.GridWebDriver;
import eleks.com.pages.CreateMailPage;
import eleks.com.pages.MailBoxPage;
import eleks.com.pages.MainPage;
import eleks.com.pages.SentEmailsPage;
import eleks.com.pages.SuccessSendMailPage;

public class BasePage_Test {
    
    final String filePath = "Extent.html";

	protected GridWebDriver gridDriver;
	protected RemoteWebDriver remoteWebDriver;
	protected ThreadLocal<RemoteWebDriver> threadDriver = new ThreadLocal<RemoteWebDriver>();

	Email email = new EmailBuilder().buildTo("to@i.ua")
			.buildSubject("subject_tra_ta_ta").buildBody("body_tra_ta_ta")
			.build();

	ConnectMySQL mySQL = new ConnectMySQL();

	SoftAssert softAssert = new SoftAssert();

	MainPage mainPage;
	MailBoxPage mailBoxPage;
	CreateMailPage createMailPage;
	SuccessSendMailPage successSendMailPage;
	SentEmailsPage sentEmailsPage;
	

	@BeforeMethod
	@Parameters({ "platform", "browserName", "remouteURL" })
	public void setUp(String platform, String browserName, String remouteURL)
			throws Exception {
		remoteWebDriver = GridWebDriver.getInstance(browserName, remouteURL);
		threadDriver.set(remoteWebDriver);
		remoteWebDriver.get(ConfigProperties.getProperties("url"));
		mainPage = new MainPage(GridWebDriver.getInstance());
	}

	public WebDriver getDriver() {
		return threadDriver.get();
	}
	

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		GridWebDriver.killDriverInstance();
	}

	public String getUserNameSQL() throws ClassNotFoundException, SQLException {
		return mySQL.getUserNameSQL();
	}

	public String getUserPassSQL() throws ClassNotFoundException, SQLException {
		return mySQL.getUserPassSQL();
	}

}
