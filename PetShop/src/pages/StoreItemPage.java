package pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreItemPage extends PageTemplate {

	public StoreItemPage(WebDriver driver, Properties config, WebDriverWait waiter) {
		super(driver, config, waiter);
	}

	public WebElement getAddToCart() {
		return this.presenceOfElementLocatedByXpath("add_to_cart_button");
	}

	public void clickOnAddToCart() {
		this.getAddToCart().click();
	}

	public void addToCart(String id) {
		String baseUrl = this.config.getProperty("petshop_item_base_url");
		this.driver.navigate().to(baseUrl + id);

		this.clickOnAddToCart();
	}

}
