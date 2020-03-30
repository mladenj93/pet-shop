package pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends PageTemplate {

	public RegistrationPage(WebDriver driver, Properties config, WebDriverWait waiter) {
		super(driver, config, waiter);

	}

	public WebElement getUserId() {
		return this.presenceOfElementLocatedByXpath("user_id_input");
	}

	public void setUserId(String id) {
		this.getUserId().clear();
		this.getUserId().sendKeys(id);
	}

	public WebElement getNewPassword() {
		return this.presenceOfElementLocatedByXpath("new_password_input");
	}

	public void setNewPassword(String password) {
		this.getNewPassword().clear();
		this.getNewPassword().sendKeys(password);
	}

	public WebElement getRepeatPassword() {
		return this.presenceOfElementLocatedByXpath("repeat_password_input");
	}

	public void setRepeatPassword(String password) {
		this.getRepeatPassword().clear();
		this.getRepeatPassword().sendKeys(password);
	}

	public WebElement getFirstName() {
		return this.presenceOfElementLocatedByXpath("first_name_input");
	}

	public void setFirstName(String firstName) {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
	}

	public WebElement getLastName() {
		return this.presenceOfElementLocatedByXpath("last_name_input");
	}

	public void setLastName(String lastName) {
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
	}

	public WebElement getEmail() {
		return this.presenceOfElementLocatedByXpath("email_input");
	}

	public void setEmail(String email) {
		this.getEmail().clear();
		this.getEmail().sendKeys(email);
	}

	public WebElement getPhone() {
		return this.presenceOfElementLocatedByXpath("phone_input");
	}

	public void setPhone(String phone) {
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
	}

	public WebElement getAddress1() {
		return this.presenceOfElementLocatedByXpath("address_1_input");
	}

	public void setAddress1(String address) {
		this.getAddress1().clear();
		this.getAddress1().sendKeys(address);
	}

	public WebElement getAddress2() {
		return this.presenceOfElementLocatedByXpath("address_2_input");
	}

	public void setAddress2(String address) {
		this.getAddress2().clear();
		this.getAddress2().sendKeys(address);
	}

	public WebElement getCity() {
		return this.presenceOfElementLocatedByXpath("city_input");
	}

	public void setCity(String city) {
		this.getCity().clear();
		this.getCity().sendKeys(city);
	}

	public WebElement getState() {
		return this.presenceOfElementLocatedByXpath("state_input");
	}

	public void setState(String state) {
		this.getState().clear();
		this.getState().sendKeys(state);
	}

	public WebElement getZip() {
		return this.presenceOfElementLocatedByXpath("zip_input");
	}

	public void setZip(String zip) {
		this.getZip().clear();
		this.getZip().sendKeys(zip);
	}

	public WebElement getCountry() {
		return this.presenceOfElementLocatedByXpath("country_input");
	}

	public void setCountry(String country) {
		this.getCountry().clear();
		this.getCountry().sendKeys(country);
	}

	public Select getLanguage() {
		WebElement language = this.presenceOfElementLocatedByXpath("select_language");
		return new Select(language);
	}

	public void setLanguageByIndex(int index) {
		this.getLanguage().selectByIndex(index);
	}

	public void setLanguageByText(String text) {
		this.getLanguage().selectByVisibleText(text);
	}

	public void setLanguageByValue(String value) {
		this.getLanguage().selectByValue(value);
	}

	public Select getFavouriteCategory() {
		WebElement favouriteCategory = this.presenceOfElementLocatedByXpath("select_favourite_category");
		return new Select(favouriteCategory);
	}

	public void setFavouriteCategoryByIndex(int index) {
		this.getFavouriteCategory().selectByIndex(index);
	}

	public void setFavouriteCategoryByValue(String value) {
		this.getFavouriteCategory().selectByValue(value);
	}

	public void setFavouriteCategoryByText(String Text) {
		this.getFavouriteCategory().selectByVisibleText(Text);
	}

	public WebElement getEnableMyList() {
		return this.elementToBeClickableByXpath("enable_my_list_checkbox");
	}

	public void setEnableMyList(boolean checked) {
		if (checked != this.getEnableMyList().isSelected()) {
			this.getEnableMyList().click();
		}
	}

	public WebElement getEnableMyBanner() {
		return this.elementToBeClickableByXpath("enable_my_banner_checkbox");
	}

	public void setEnableMyBanner(boolean checked) {
		if (checked != this.getEnableMyBanner().isSelected()) {
			this.getEnableMyBanner().click();
		}
	}

	public WebElement getSave() {
		return this.elementToBeClickableByXpath("save_account_information_button");
	}

	public void clickOnSave() {
		this.getSave().click();
	}
	
	public void submitForm(String id, String password, String password1, String firstName, String lastName, String email, 
			String phone, String address1, String address2, String city, String state, 
			String zip, String country, String language, String category, 
			boolean myListChecked, boolean myBannerChecked) {
		this.setUserId(id);
		this.setNewPassword(password);
	    this.setRepeatPassword(password1);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setAddress1(address1);
		this.setAddress2(address2);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setCountry(country);
		this.setLanguageByText(language);
		this.setFavouriteCategoryByText(category);
		this.setEnableMyList(myListChecked);
		this.setEnableMyBanner(myBannerChecked);
		this.clickOnSave();
		
	}
}
