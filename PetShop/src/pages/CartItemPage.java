package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartItemPage extends PageTemplate{
	
	private WebElement itemRow;

	protected CartItemPage(WebDriver driver, Properties config, WebDriverWait waiter,
			WebElement itemRow) {
		super(driver, config, waiter);
		this.itemRow = itemRow;
	}
	@Override
	public WebElement findElementByXpath(String xpathKey) {
		return this.itemRow.findElement(By.xpath(this.config.getProperty(xpathKey)));
	}
	public WebElement getRemove() {
		return this.findElementByXpath("remove_button");
	}
	
	public void clickOnRemuve() {
		this.getRemove().click();
	}
	public WebElement getQuantity() {
		return this.findElementByXpath("quantity_input");
	}
	public void setQuantity(int quantity) {
		this.getQuantity().clear();
		this.getQuantity().sendKeys(String.valueOf(quantity));
	}
	public double getQuantityValue() {
		return Double.parseDouble(this.getQuantity().getAttribute("value"));
	}
	public WebElement getId() {
		return this.findElementByXpath("item_id_link");
	}
	public String getIdText() {
		return this.getId().getText();
	}
	public WebElement getPriceList() {
		return this.findElementByXpath("list_price");
	}
	public double getPrice() {
		String price = this.getPriceList().getText();
		String priceNumber = price.substring(1);
		return Double.parseDouble(priceNumber);
	}
	public WebElement getTotalCost() {
		return this.findElementByXpath("total_price");
	}
	public double getTotalPrice() {
		String price = this.getTotalCost().getText();
		String priceNumber = price.substring(1);
		return Double.parseDouble(priceNumber);
	}
	
	
	

}
