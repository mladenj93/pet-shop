package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.SearchItemPage;
import pages.SearchPage;
import utils.ExcelUtils;

public class SearchTest extends TestTemplate {

	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) throws Exception {
		this.init(browser);
	}

	@Test
	public void searchTest() {
		this.driver.navigate().to(this.config.getProperty("petshop_menu_url"));

		ExcelUtils.setExcell(this.config.getProperty("data_file"));
		ExcelUtils.setWorkSheetByString(this.config.getProperty("search_items_sheet"));

		SoftAssert sa = new SoftAssert();
		SearchPage sp = new SearchPage(driver, config, waiter);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {

			sp.search(ExcelUtils.getDataAt(i, 0));

			int actualyItemsCount = sp.getSearchItems().size();
			int expectedItemsCount = (int)ExcelUtils.getNumDataAt(i, 1);

			sa.assertEquals(actualyItemsCount, expectedItemsCount);
			int startIndex = 2; 
		
			for (int j = startIndex; j < expectedItemsCount + startIndex; j++) {
				String productId = ExcelUtils.getDataAt(i, j);
				sa.assertTrue(sp.verifyItemExists(productId));
			}

		}
		sa.assertAll();
	}
	@AfterClass
	public void close() {
		this.driver.close();
	}
}
