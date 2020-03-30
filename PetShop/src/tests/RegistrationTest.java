package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.MenuPage;
import pages.RegistrationPage;
import utils.ExcelUtils;

public class RegistrationTest extends TestTemplate {
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) throws Exception {
		this.init(browser);
	}
	@BeforeMethod
	public void setupForTest() throws IOException {

		ExcelUtils.setExcell(this.config.getProperty("data_file")); 
		ExcelUtils.setWorkSheetByString(this.config.getProperty("users_sheet"));
		
		for(int j = 1; j < ExcelUtils.getRowNumber(); j++) {
		    ExcelUtils.setCellAt(j, 0, this.generateUniqueId());
		}
		ExcelUtils.write();
		ExcelUtils.closeExcell();
		
	}
	
	@Test
	public void registrationOnPetShop() throws IOException {
		ExcelUtils.setExcell(this.config.getProperty("data_file")); 
		ExcelUtils.setWorkSheetByString(this.config.getProperty("users_sheet"));
		SoftAssert sa = new SoftAssert();
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			
			String userId = ExcelUtils.getDataAt(i, 0); 
			String password = ExcelUtils.getDataAt(i, 1);
			String firstName = ExcelUtils.getDataAt(i, 2);
			String lastName = ExcelUtils.getDataAt(i, 3);
			String email = ExcelUtils.getDataAt(i, 4);
			String phone = ExcelUtils.getDataAt(i, 5);
			String address1 = ExcelUtils.getDataAt(i, 6);
			String address2 = ExcelUtils.getDataAt(i, 7);
			String city = ExcelUtils.getDataAt(i, 8);
			String state = ExcelUtils.getDataAt(i, 9);
			String zip = ExcelUtils.getDataAt(i, 10);
			String country = ExcelUtils.getDataAt(i, 11);
			
			this.driver.navigate().to(this.config.getProperty("petshop_register_url"));
			
			RegistrationPage rp = new RegistrationPage(driver, config, waiter);
			rp.submitForm(userId, password, password, firstName, lastName,
					email, phone, address1, address2, city, state, zip, 
					country, "english", "FISH", false, false);
			
			MenuPage mp = new MenuPage(driver, config, waiter);
			boolean registrationIsOk = true;
			try {
				mp.getSingIn();
			}catch(Exception e) {
				registrationIsOk = false;
			}
			sa.assertTrue(registrationIsOk);
		}
		sa.assertAll();
		ExcelUtils.closeExcell();
	}
	@AfterClass
	public void close() {
		this.driver.close();
	}
	private String generateUniqueId() {
		  int leftLimit = 48; 
		    int rightLimit = 122; 
		    int targetStringLength = 10;
		    Random random = new Random();
		 
		    String generatedString = random.ints(leftLimit, rightLimit + 1)
		      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();
		    return generatedString;
	}

}
