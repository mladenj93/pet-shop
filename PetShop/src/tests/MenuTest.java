package tests;





import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.MenuPage;
import pages.SignInPage;

public class MenuTest extends TestTemplate {
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) throws Exception {
		this.init(browser);
	}
	
	@Test
	public void sidebarLinksTest() {
		
		this.driver.navigate().to(this.config.getProperty("petshop_menu_url"));
		
		MenuPage mp = new MenuPage(driver, config, waiter);
		mp.sidebarLinks();
		
		List<String> sideberList = new ArrayList<String>();
		sideberList.add("FISH");
		sideberList.add("DOGS");
		sideberList.add("CATS");
		sideberList.add("REPTILES");
		sideberList.add("BIRDS");
		
		boolean sideberLinkIsOK = true;
		
	    for(int i = 0; i < mp.sidebarLinks().size(); i++) {
	    	if(!mp.sidebarLinks().get(i).getAttribute("href").contains(sideberList.get(i))) {
	    		sideberLinkIsOK = false;
	    	}
	    }
	    Assert.assertTrue(sideberLinkIsOK);
		
	}
	
	@Test
	public void quickLinksTest() {
		
		this.driver.navigate().to(this.config.getProperty("petshop_menu_url"));
		
		MenuPage mp = new MenuPage(driver, config, waiter);
		mp.quickLinks();
		
		List<String> quickList = new ArrayList<String>();
		
		quickList.add("FISH");
		quickList.add("DOGS");
		quickList.add("REPTILES");
		quickList.add("CATS");
		quickList.add("BIRDS");
		
		boolean quickLinkIsOK = true;
		
	    for(int i = 0; i < mp.quickLinks().size(); i++) {
	    	if(!mp.quickLinks().get(i).getAttribute("href").contains(quickList.get(i))) {
	    		quickLinkIsOK = false;
	    	}
	    }
	    Assert.assertTrue(quickLinkIsOK);
		
	}
	
	@Test
	public void storeMapLinksTest() {
		
		this.driver.navigate().to(this.config.getProperty("petshop_menu_url"));
		
		MenuPage mp = new MenuPage(driver, config, waiter);
		mp.storeMapLinks();
		
		List<String> storeMapList = new ArrayList<String>();
		
		storeMapList.add("BIRDS");
		storeMapList.add("FISH");
		storeMapList.add("DOGS");
		storeMapList.add("REPTILES");
		storeMapList.add("CATS");
		storeMapList.add("BIRDS");
		
		boolean storeMapLinkIsOK = true;
		
	    for(int i = 0; i < mp.storeMapLinks().size(); i++) {
	    	if(!mp.storeMapLinks().get(i).getAttribute("href").contains(storeMapList.get(i))) {
	    		storeMapLinkIsOK = false;
	    	}
	    }
	    Assert.assertTrue(storeMapLinkIsOK);
		
	}
	@Test
	public void singInTest() {
		this.driver.navigate().to(this.config.getProperty("petshop_menu_url"));
		
		MenuPage mp = new MenuPage(driver, config, waiter);
		
		mp.clickOnSingIn();
		
		SignInPage sip = new SignInPage(driver, config, waiter);
		
		boolean singInIsOk = true;
		try {
			sip.getUsername();
			sip.getPassword();
		}catch(Exception e){
			singInIsOk = false;
		}
		Assert.assertTrue(singInIsOk);
	}
	@Test
	public void allLinksTest() {
		this.driver.navigate().to(this.config.getProperty("petshop_menu_url"));
		
		SoftAssert sa = new SoftAssert();
		
		MenuPage mp = new MenuPage(driver, config, waiter);
		
	    List<WebElement> allLinksOnMenuPage = new ArrayList<WebElement>();
		
	    allLinksOnMenuPage.addAll(mp.sidebarLinks());
	    allLinksOnMenuPage.addAll(mp.quickLinks());
	    allLinksOnMenuPage.addAll(mp.storeMapLinks());
	    allLinksOnMenuPage.add(mp.getSingIn());
	    
	    for(int i = 0; i < allLinksOnMenuPage.size(); i++) {
	    	String url = allLinksOnMenuPage.get(i).getAttribute("href");
			int status = verifyURLStatus(url);
			sa.assertTrue(status < 400, url);
	    }

		
		sa.assertAll();
		
		
	}
	public int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	
	@AfterClass
	public void clase() {
		this.driver.close();
	}

}
