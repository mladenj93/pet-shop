package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class TestTemplate {
	protected WebDriver driver;
	protected Properties config;
	protected WebDriverWait waiter;
	
	public void setupDriverAndWaiter() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		this.waiter = new WebDriverWait(driver,30);
	}
	
	public void loadConfig() throws Exception {
		this.config =  new Properties();
		config.load(new FileInputStream("config/config.properties"));
	}
	
	public void init() throws Exception {
		this.setupDriverAndWaiter();
		this.loadConfig();
	}

}
