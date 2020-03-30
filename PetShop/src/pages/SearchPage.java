package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends PageTemplate{

	public SearchPage(WebDriver driver, Properties config, WebDriverWait waiter) {
		super(driver, config, waiter);
	}
	
	public WebElement getSearch() {
		return this.presenceOfElementLocatedByXpath("search_input");
	}
	public void setTextInSearch(String text) {
		this.getSearch().clear();
		this.getSearch().sendKeys(text);
	}

	public WebElement getSearchButton() {
		return this.presenceOfElementLocatedByXpath("search_button");
	}
	public void clickOnSearchButton() {
		this.getSearchButton().click();
	}
	public void search(String text) {
		this.setTextInSearch(text);
		this.clickOnSearchButton();
	}
	private List<WebElement> getItemsRows(){
		List<WebElement> items = this.presenceOfElementsLocatedByXpath("search_items_rows");;
		items.remove(0);
		items.remove(items.size()-1);
		return items;
	}
	public List<SearchItemPage> getSearchItems(){
		List<SearchItemPage> listItems = new ArrayList<SearchItemPage>();
		for(int i = 0; i < this.getItemsRows().size(); i++) {
			listItems.add(new SearchItemPage( driver, config, waiter, this.getItemsRows().get(i)));
		}
		return listItems;
	}
	public boolean verifyItemExists(String id) {
		List <SearchItemPage> items = this.getSearchItems();
		
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getTextProductId().contains(id)) {
				return true;
			}
		}
		return false;
	}

}