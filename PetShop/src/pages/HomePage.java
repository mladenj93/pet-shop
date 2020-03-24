package pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends PageTemplate {
		
	public HomePage(WebDriver driver, Properties config, WebDriverWait waiter) {
		super(driver, config, waiter);
	}
	
	public WebElement getEnterStoreLink() {
		return this.presenceOfElementLocatedByXpath("enter_store_lnk");
	}
	
	public void clickOnEnterStoreLink() {
		this.getEnterStoreLink().click();
	}

}
