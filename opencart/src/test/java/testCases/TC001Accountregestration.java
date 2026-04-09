package testCases;



import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AccountRegistrationPage;
import PageObjects.HomePage;

public class TC001Accountregestration  extends Baseclass{

  

	@Test(groups= {"Regression","Master"})
	public void verify_account_reg() throws InterruptedException {

	    logger.info("Account registration started");

	    try {

	        HomePage hm = new HomePage(driver);

	        hm.clickMyAccount();
	        hm.clickRegister();

	        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

	        regpage.setFirstName("John");
	        regpage.setLastName("David");
	        regpage.setEmail("user" + System.currentTimeMillis() + "@test.com");
	        regpage.setPassword("xyz123456");

	        Thread.sleep(2000);

	        regpage.setPrivacyPolicy();
	        regpage.clickContinue();

	        String confmsg = regpage.getConfirmationMsg();

	        if(confmsg.equals("Your Account Has Been Created!")) {

	            Assert.assertTrue(true);
	            logger.info("Account created successfully");

	        } else {

	            logger.error("Account creation failed");
	            Assert.fail();
	        }

	    } catch(Exception e) {

	        logger.error("Test failed due to exception");
	        Assert.fail();

	    }
	}
}