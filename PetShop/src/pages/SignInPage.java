package pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage extends PageTemplate {
	
	public SignInPage(WebDriver driver, Properties config, WebDriverWait waiter) {
		super(driver, config, waiter);
	}

	public WebElement getUsername() {
		return this.presenceOfElementLocatedByXpath("username_input");
	}
	public WebElement getPassword() {
		return this.presenceOfElementLocatedByXpath("password_input");
	}
	public void setUsername(String username) {
		this.getUsername().clear();
		this.getUsername().sendKeys(username);
	}
	public void setPassword(String password) {
		this.getPassword().clear();
		this.getPassword().sendKeys(password);
	}
	public WebElement getLoginButton() {
		return this.elementToBeClickableByXpath("login_button");
	}
	public void clickOnLoginButton() {
		this.getLoginButton().click();
	}
	public void login(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
		this.clickOnLoginButton();
		
	}
	
	public WebElement getRegiserLink() {
        return this.elementToBeClickableByXpath("register_link");
	}
	public void clickOnRegisterLink() {
		this.getRegiserLink().click();
	}
	public WebElement getSingOut() {
		return this.presenceOfElementLocatedByXpath("sing_out_button");
	}
	public void clickOnSingOut() {
		this.getSingOut().click();
	}

}
