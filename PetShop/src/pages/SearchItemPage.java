package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchItemPage extends PageTemplate{
	
	private WebElement searchItem;

	protected SearchItemPage(WebDriver driver, Properties config, WebDriverWait waiter,
			WebElement searchItem) {
		super(driver, config, waiter);
		this.searchItem = searchItem;
	}
	
	@Override
	public WebElement findElementByXpath(String xpathKey) {
		return this.searchItem.findElement(By.xpath(this.config.getProperty(xpathKey)));
	}
	public WebElement getItemLink() {
		return this.findElementByXpath("item_link");
	}
	
	public WebElement getProductId() {
		return this.findElementByXpath("product_id");
	}
	public String getTextProductId() {
		return this.getProductId().getText();
	}
	public WebElement getProductName() {
		return this.findElementByXpath("product_name");
	}
	
}
