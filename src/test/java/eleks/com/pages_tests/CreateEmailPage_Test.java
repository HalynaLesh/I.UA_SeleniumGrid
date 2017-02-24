package eleks.com.pages_tests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateEmailPage_Test extends BasePage_Test {

	@Test
	public void createEmail_test() throws ClassNotFoundException, SQLException {
		mainPage.openMainPage();
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		Assert.assertTrue(createMailPage.isCreateMailPageOpened());
		successSendMailPage = createMailPage.createEmail(email.getTo(),
				email.getSubject(), email.getBody());
		Assert.assertTrue(successSendMailPage.isSuccessSendMailPageOpened());
		mailBoxPage = successSendMailPage.navigateToMaiBoxPage();
		mailBoxPage.logout();
	}

}
