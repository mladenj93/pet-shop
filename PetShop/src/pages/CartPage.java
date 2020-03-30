package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends PageTemplate {

	public CartPage(WebDriver driver, Properties config, WebDriverWait waiter) {
		super(driver, config, waiter);
	}

	public WebElement getCart() {
		return this.presenceOfElementLocatedByXpath("cart_button");
	}

	public void clickOnCart() {
		this.getCart().click();
	}

	public WebElement getUpdateCart() {
		return this.presenceOfElementLocatedByXpath("update_cart_button");
	}

	public void clickOnUpdateCart() {
		this.getUpdateCart().click();

	}

	public WebElement getProceedToCheckout() {
		return this.presenceOfElementLocatedByXpath("proceed_to_checkout_button");
	}

	public void clickProceedToCheckout() {
		this.getProceedToCheckout().click();
	}

	public WebElement getEmptyCartMessage() {
		return this.findElementByXpath("empty_cart_message");
	}

	public boolean isEmpty() {
		this.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		boolean isEmpty = true;
		try {
			this.getEmptyCartMessage();

		} catch (Exception e) {
			isEmpty = false;
		}
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return isEmpty;

	}

	private List<WebElement> getItemRows() {
		List<WebElement> itemRows = this.presenceOfElementsLocatedByXpath("cart_item_rows");
		itemRows.remove(0);// first row is header
		itemRows.remove(itemRows.size() - 1);// last row is for total price
		return itemRows;
	}

	public List<CartItemPage> getCartItems() {
		List<CartItemPage> items = new ArrayList<CartItemPage>();
		for (int i = 0; i < getItemRows().size(); i++) {
			items.add(new CartItemPage(driver, config, waiter, getItemRows().get(i)));
		}
		return items;
	}

	public CartItemPage findItemById(String itemId) {
		for (int i = 0; i < getCartItems().size(); i++) {
			if (this.getCartItems().get(i).getIdText().equals(itemId)) {
				return this.getCartItems().get(i);
			}
		}
		return null;
	}

	public void setQuantity(String itemId, int quantity) {
		this.findItemById(itemId).setQuantity(quantity);
	}

	public void removeAll() {
		while (!isEmpty()) {
			this.getCartItems().get(0).clickOnRemuve();
		}
	}

	public void removeItemById(String itemId) {
		this.findItemById(itemId).clickOnRemuve();
	}

	public void clearViaCookies() {
		this.driver.manage().deleteAllCookies();
		this.driver.navigate().refresh();
	}

	public WebElement getSubTotal() {
		return this.presenceOfElementLocatedByXpath("sub_total_price");
	}

	public double getSubTotalPrice() {
		String price = this.getSubTotal().getText();
		String priceNumber = price.substring(price.indexOf("$")+1);
		return Double.parseDouble(priceNumber);
	}

}
