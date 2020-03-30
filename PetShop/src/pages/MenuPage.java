package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage extends PageTemplate {
	
	public MenuPage(WebDriver driver, Properties config, WebDriverWait waiter) {
		super(driver, config, waiter);
	}
	
	public List<WebElement> sidebarLinks(){
		return this.visibilityOfElementsLocatedByXpath("sidebar_links");
	}
	public List<WebElement> quickLinks(){
		return this.visibilityOfElementsLocatedByXpath("quick_links");
	}
	public List<WebElement> storeMapLinks(){
		return this.visibilityOfElementsLocatedByXpath("store_map_links");
	}
	
	public WebElement getSingIn() {
		return this.elementToBeClickableByXpath("sing_in_button");
	}
	public void clickOnSingIn() {
		getSingIn().click();
	}

	

}
