package eleks.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SentEmailsPage extends BasePage {

	public SentEmailsPage(WebDriver webDriver) {
		super(webDriver);
	}

	@FindBy(xpath = ".//*[@id='newMail']/a")
	public WebElement goToMailBox_icon;

	public MailBoxPage goToMaiBoxPage() {
		goToMailBox_icon.click();
		return PageFactory.initElements(webDriver, MailBoxPage.class);
	}

	public void openSentEmail(String subject) {
		final String subject_find = "//descendant::div[@class='row'][1]//span[text()='subject_tra_ta_ta']";
		webDriver.findElement(By.xpath(subject_find)).click();
	}

	public boolean isCorrectSubject(String subject) {
		final String subject_find = "//descendant::span[text()='%s']";
		if (isElementPresent(webDriver.findElement(By.xpath(String.format(
				subject_find, subject))))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isCorrectSubjectOnEmail(String subject) {
		final String body_find = "//div[@class='message_header clear']/h3[text()='%s']";
		if (isElementPresent(webDriver.findElement(By.xpath(String.format(
				body_find, subject))))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isCorrectBodyOnEmail(String body) {
		final String body_find = "//div[@class='message_body']/pre[text()='%s']";
		if (isElementPresent(webDriver.findElement(By.xpath(String.format(
				body_find, body))))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isCorrectToOnEmail(String to) {
		final String body_find = "//div[@class='field_value'][text()='%s']";
		if (isElementPresent(webDriver.findElement(By.xpath(String.format(
				body_find, to))))) {
			return true;
		} else {
			return false;
		}
	}

}
