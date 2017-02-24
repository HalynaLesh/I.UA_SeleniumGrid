package eleks.com.pages_tests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import eleks.com.core.GridWebDriver;
import eleks.com.pages.MainPage;

public class SentEmailsPage_Test extends BasePage_Test {

	SoftAssert softAssert = new SoftAssert();

	@BeforeMethod
	public void init() {
		mainPage = new MainPage(GridWebDriver.getInstance());
	}

	@Test
	public void positive_verifySubject_test() throws ClassNotFoundException,
			SQLException {
		mainPage.openMainPage();
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		successSendMailPage = createMailPage.createEmail(email.getTo(),
				email.getSubject(), email.getBody());
		sentEmailsPage = successSendMailPage.navigateToSentEmailsPage();
		Assert.assertTrue(sentEmailsPage.isCorrectSubject(email.getSubject()));
		mailBoxPage = sentEmailsPage.goToMaiBoxPage();
		mailBoxPage.logout();
	}

	@Test
	public void negative_verifySubject_test() throws ClassNotFoundException,
			SQLException {
		mainPage.openMainPage();
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		successSendMailPage = createMailPage.createEmail(email.getTo(),
				email.getSubject(), email.getBody());
		sentEmailsPage = successSendMailPage.navigateToSentEmailsPage();
		Assert.assertFalse(sentEmailsPage.isCorrectSubject("dgdfg"));
		mailBoxPage = sentEmailsPage.goToMaiBoxPage();
		mailBoxPage.logout();
	}

	@Test
	public void positive_verifyEmailContent_test()
			throws ClassNotFoundException, SQLException {
		mainPage.openMainPage();
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		successSendMailPage = createMailPage.createEmail(email.getTo(),
				email.getSubject(), email.getBody());
		sentEmailsPage = successSendMailPage.navigateToSentEmailsPage();
		sentEmailsPage.openSentEmail(email.getSubject());
		Assert.assertTrue(sentEmailsPage.isCorrectToOnEmail(email.getTo()));
		Assert.assertTrue(sentEmailsPage.isCorrectSubjectOnEmail(email
				.getSubject()));
		Assert.assertTrue(sentEmailsPage.isCorrectBodyOnEmail(email.getBody()));
		softAssert.assertAll();
		mailBoxPage = sentEmailsPage.goToMaiBoxPage();
		mailBoxPage.logout();
	}

	@Test
	public void negative_verifyEmailContent_test()
			throws ClassNotFoundException, SQLException {
		mainPage.openMainPage();
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		successSendMailPage = createMailPage.createEmail(email.getTo(),
				email.getSubject(), email.getBody());
		sentEmailsPage = successSendMailPage.navigateToSentEmailsPage();
		sentEmailsPage.openSentEmail(email.getSubject());
		Assert.assertFalse(sentEmailsPage.isCorrectToOnEmail("xfgxdfg"));
		Assert.assertFalse(sentEmailsPage.isCorrectSubjectOnEmail("hfhf"));
		Assert.assertFalse(sentEmailsPage.isCorrectBodyOnEmail("fhfghf"));
		softAssert.assertAll();
		mailBoxPage = sentEmailsPage.goToMaiBoxPage();
		mailBoxPage.logout();
	}

}
