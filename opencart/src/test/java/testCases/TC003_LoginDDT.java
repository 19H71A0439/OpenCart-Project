package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.MyAccountPage;
import PageObjects.login;
import Utilities.DataProviders;

public class TC003_LoginDDT extends Baseclass {


@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class , groups= {"Regression"})
public void verify_loginDDT(String email, String pwd, String exp) {

    logger.info("************** TC003_LoginDDT started *********");

    try {

        // Home Page
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickLogin();

        // Login Page
        login lp = new login(driver);
        lp.setEmail(email);
        lp.setPassword(pwd);
        lp.clicklogin();

        // My Account Page
        MyAccountPage macc = new MyAccountPage(driver);
        boolean targetPage = macc.isMyAccountPageExists();

        // Validation
        if (exp.equalsIgnoreCase("Valid")) {

            if (targetPage == true) {
                macc.clickLogout();
                Assert.assertTrue(true);
            } 
            else {
                Assert.assertTrue(false);
            }
            
        }

        else if (exp.equalsIgnoreCase("Invalid")) {

            if (targetPage == true) {
                macc.clickLogout();
                Assert.assertTrue(false);
            } 
            else {
                Assert.assertTrue(true);
            }
        }

    } 
    catch (Exception e) {
        Assert.fail();
    }

    logger.info("************** TC003_LoginDDT finished *********");
}


}
