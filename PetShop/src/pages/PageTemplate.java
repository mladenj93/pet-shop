package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageTemplate {
	
	protected WebDriver driver;
	protected Properties config;
	protected WebDriverWait waiter;
	
	
	public PageTemplate(WebDriver driver, Properties config, WebDriverWait waiter) {
		this.driver = driver;
		this.config = config;
		this.waiter = waiter;
	}
	
	public WebElement findElementByXpath(String xpathKey) {
		return this.driver.findElement(By.xpath(this.config.getProperty(xpathKey)));
	}
	
	public WebElement presenceOfElementLocatedByXpath(String xpathKey) {
		return this.waiter.until(
				ExpectedConditions.presenceOfElementLocated(
						By.xpath(this.config.getProperty(xpathKey))));
		
	}
	
	public WebElement visibilityOfElementLocatedByXpath(String xpathKey) {
		return this.waiter.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.xpath(this.config.getProperty(xpathKey))));
		
	}
	public WebElement elementToBeClickableByXpath(String xpathKey) {
		return this.waiter.until(
				ExpectedConditions.elementToBeClickable(
						By.xpath(this.config.getProperty(xpathKey))));
		
	}
	
	public List<WebElement> findElementsByXpath(String xpathKey){
		return this.driver.findElements(By.xpath(this.config.getProperty(xpathKey)));
	}
	public List<WebElement> presenceOfElementsLocatedByXpath(String xpathKey) {
		return this.waiter.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(
						By.xpath(this.config.getProperty(xpathKey))));
		
	}
	public List<WebElement> visibilityOfElementsLocatedByXpath(String xpathKey) {
		return this.waiter.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath(this.config.getProperty(xpathKey))));
		
	}


}
