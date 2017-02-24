package eleks.com.pages_tests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SuccessSendMailPage_Test extends BasePage_Test {

	@Test
	public void goToMailBoxPage_test() throws ClassNotFoundException,
			SQLException {
		mainPage.openMainPage();
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		successSendMailPage = createMailPage.createEmail(email.getTo(),
				email.getSubject(), email.getBody());
		mailBoxPage = successSendMailPage.navigateToMaiBoxPage();
		Assert.assertTrue(mailBoxPage.isMailBoxPageOpened());
		mailBoxPage.logout();
	}

}
