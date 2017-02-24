package eleks.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eleks.com.core.ConfigProperties;


public class MainPage extends BasePage {

	public MainPage(WebDriver webDriver) {
		super(webDriver);
	}

	@FindBy(xpath = "//p[2]/input")
	public WebElement login_username;

	@FindBy(xpath = "//input[@name='pass']")
	public WebElement login_password;

	@FindBy(xpath = "//form/p/input")
	public WebElement submitLogin;
	
	@FindBy(xpath = "//a[@class='mbox_16']/descendant::i")
	public WebElement navigateMailBox;
	
	@FindBy(xpath = "//img[@title='I.UA']")
	public WebElement iua_icon;

	public void openMainPage() {
		webDriver.get(ConfigProperties.getProperties("url"));
	}

	public boolean isMainPageOpened() {
		if (isElementPresent(login_username)
				&& isElementPresent(login_password)
				&& isElementPresent(submitLogin)) {
			return true;
		} else {
			return false;
		}
	}

	public MailBoxPage loginAs(String username, String pass) {
		login_username.click();
		type(login_username, username);
		type(login_password, pass);
		submitLogin.click();
		if (webDriver instanceof InternetExplorerDriver){
			navigateMailBox.click();
		}
		return PageFactory.initElements(webDriver, MailBoxPage.class);
	}
	
	public MainPage returnToMainPage(){
		iua_icon.click();
		return this;
		
	}

}
