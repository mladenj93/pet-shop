package tests;

import java.util.Random;

import javax.crypto.Cipher;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartItemPage;
import pages.CartPage;
import pages.SignInPage;
import pages.StoreItemPage;
import utils.ExcelUtils;

public class CartTest extends TestTemplate {

	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) throws Exception {
		this.init(browser);
	}

	@Test
	public void addToCartTest() {

		ExcelUtils.setExcell(this.config.getProperty("data_file"));
		ExcelUtils.setWorkSheetByString(this.config.getProperty("cart_items_sheet"));
		SoftAssert sa = new SoftAssert();
		StoreItemPage sip = new StoreItemPage(driver, config, waiter);
		CartPage cp = new CartPage(driver, config, waiter);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String itemId = ExcelUtils.getDataAt(i, 0);

			sip.addToCart(itemId);

			CartItemPage item = cp.findItemById(itemId);

			sa.assertTrue(item != null);
		}

		sa.assertAll();
		ExcelUtils.closeExcell();
	}

	@Test
	public void clearCart() {
		ExcelUtils.setExcell(this.config.getProperty("data_file"));
		ExcelUtils.setWorkSheetByString(this.config.getProperty("cart_items_sheet"));

		StoreItemPage sip = new StoreItemPage(driver, config, waiter);
		CartPage cp = new CartPage(driver, config, waiter);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String itemId = ExcelUtils.getDataAt(i, 0);

			sip.addToCart(itemId);

		}
		cp.removeAll();
		Assert.assertTrue(cp.isEmpty());

	}

	@Test
	public void clearCartByCookies() {
		ExcelUtils.setExcell(this.config.getProperty("data_file"));
		ExcelUtils.setWorkSheetByString(this.config.getProperty("cart_items_sheet"));

		StoreItemPage sip = new StoreItemPage(driver, config, waiter);
		CartPage cp = new CartPage(driver, config, waiter);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String itemId = ExcelUtils.getDataAt(i, 0);

			sip.addToCart(itemId);

		}
		cp.clearViaCookies();
		Assert.assertTrue(cp.isEmpty());
	}

	@Test
	public void totalCostTest() {

		ExcelUtils.setExcell(this.config.getProperty("data_file"));
		ExcelUtils.setWorkSheetByString(this.config.getProperty("cart_items_sheet"));

		StoreItemPage sip = new StoreItemPage(driver, config, waiter);
		CartPage cp = new CartPage(driver, config, waiter);
		SoftAssert sa = new SoftAssert();

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String itemId = ExcelUtils.getDataAt(i, 0);

			sip.addToCart(itemId);

		}
		Random rand = new Random();

		for (int i = 0; i < cp.getCartItems().size(); i++) {

			CartItemPage item = cp.getCartItems().get(i);

			int quantity = rand.nextInt(10);
			item.setQuantity(quantity);

		}
		cp.clickOnUpdateCart();

		for (int i = 0; i < cp.getCartItems().size(); i++) {

			CartItemPage item = cp.getCartItems().get(i);

			boolean totalCostIsOk = true;
			if (item.getPrice() * item.getQuantityValue() != item.getTotalPrice()) {
				totalCostIsOk = false;
			}
			sa.assertTrue(totalCostIsOk);
		}
		sa.assertAll();
	}

	@Test
	public void subTotalPriceTest() {

		ExcelUtils.setExcell(this.config.getProperty("data_file"));
		ExcelUtils.setWorkSheetByString(this.config.getProperty("cart_items_sheet"));

		StoreItemPage sip = new StoreItemPage(driver, config, waiter);
		CartPage cp = new CartPage(driver, config, waiter);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String itemId = ExcelUtils.getDataAt(i, 0);

			sip.addToCart(itemId);

		}
		Random rand = new Random();

		for (int i = 0; i < cp.getCartItems().size(); i++) {

			CartItemPage item = cp.getCartItems().get(i);

			int quantity = rand.nextInt(10);
			item.setQuantity(quantity);

		}
		cp.clickOnUpdateCart();
		double sum = 0;

		for (int i = 0; i < cp.getCartItems().size(); i++) {

			CartItemPage item = cp.getCartItems().get(i);

			sum = sum + item.getTotalPrice();
		}

		Assert.assertTrue(cp.getSubTotalPrice() == sum);

	}

	@AfterTest
	public void close() {
		this.driver.close();
	}

}
