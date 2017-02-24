package eleks.com.pages_tests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPage_Test extends BasePage_Test {

	@Test
	public void isMainPageOpened_test() {
		mainPage.openMainPage();
		Assert.assertTrue(mainPage.isMainPageOpened());
	}

	@Test
	public void positive_loginAs_test() throws ClassNotFoundException,
			SQLException {
		mainPage.openMainPage();
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		Assert.assertTrue(mailBoxPage.isMailBoxPageOpened());
		mailBoxPage.logout();
	}

/*	@Test
	public void negative_loginAs_test() throws ClassNotFoundException,
			SQLException {
		mainPage.openMainPage();
		mailBoxPage = mainPage.loginAs("gdgdg", "dgdfgd");
		Assert.assertTrue(mailBoxPage.isMailBoxPageOpened());
		mainPage.returnToMainPage();
	}*/

}
