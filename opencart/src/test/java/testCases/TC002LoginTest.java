package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.MyAccountPage;
import PageObjects.login;

public class TC002LoginTest extends Baseclass {
    @Test(groups= {"sanity"})
	public void VerifyLogin() {
		
		logger.info("*****login test started*******");
		try {
		HomePage hm = new HomePage(driver);
		hm.clickMyAccount();
		hm.clickLogin();
		login lm = new login(driver);
		lm.setEmail(p.getProperty("email"));
		lm.setPassword(p.getProperty("password"));
		lm.clicklogin(); 
		MyAccountPage ma = new MyAccountPage(driver);
		
		boolean target = ma.VerifyMyAccount();
		Assert.assertEquals(target, true,"login failed");
		}
		catch(Exception ex) {
			Assert.fail();
		}
		
		logger.info("*****login test finished*******");
	}
}
