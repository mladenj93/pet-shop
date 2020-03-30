package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.SignInPage;
import utils.ExcelUtils;

public class LogInTest extends TestTemplate {
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) throws Exception {
		this.init(browser);
	}
	
	@Test
	public void logInTest() {
		
		ExcelUtils.setExcell(this.config.getProperty("data_file")); 
		ExcelUtils.setWorkSheetByString(this.config.getProperty("users_sheet"));
		SoftAssert sa = new SoftAssert();
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String userId = ExcelUtils.getDataAt(i, 0); 
			String password = ExcelUtils.getDataAt(i, 1);
			this.driver.navigate().to(this.config.getProperty("petshop_signin_url"));
			
			SignInPage sip = new SignInPage(driver, config, waiter);
			
			sip.login(userId, password);
			
			boolean logInIsOk = true;
			try {
				sip.getSingOut();
			}catch(Exception e){
			    logInIsOk = false;
			}
			sa.assertTrue(logInIsOk);
			sip.clickOnSingOut();
			
		}
		sa.assertAll();
		ExcelUtils.closeExcell();
	}
	@AfterClass
	public void clase() {
		this.driver.close();
	}

}
