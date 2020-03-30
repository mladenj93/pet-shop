package tests;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.CartItemPage;
import pages.HomePage;
import pages.MenuPage;

public class HomeTest extends TestTemplate{
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) throws Exception {
		this.init(browser);
	}
	
	@Test
	public void storeEnterTest() {
		
	    this.driver.navigate().to(this.config.getProperty("petshop_url"));
	    HomePage hp = new HomePage(driver, config, waiter);
	    hp.clickOnEnterStoreLink();
	    
	    MenuPage mp = new MenuPage(driver, config, waiter);
	    
	    boolean singInIsExists = true;
	    try {
	    	mp.getSingIn();
	    }catch(Exception e){
	    	singInIsExists = false;
	    }
	    
	    Assert.assertTrue(singInIsExists);
	}
	
	@AfterClass
	public void close() {
		this.driver.close();
	}

}
