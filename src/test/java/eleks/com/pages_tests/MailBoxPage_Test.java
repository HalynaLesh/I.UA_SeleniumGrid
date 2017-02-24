package eleks.com.pages_tests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MailBoxPage_Test extends BasePage_Test {

	@Test
	public void goToCreateEmailPage_test() throws SQLException, ClassNotFoundException {
		mainPage.openMainPage();
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		Assert.assertTrue(createMailPage.isCreateMailPageOpened());
		mailBoxPage = createMailPage.navigateToMailBoxPage();
		mailBoxPage.logout();
	}

	@Test
	public void logout_test() throws SQLException, ClassNotFoundException {
		mainPage.openMainPage();
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		mainPage = mailBoxPage.logout();
		Assert.assertTrue(mainPage.isMainPageOpened());
	}

}
