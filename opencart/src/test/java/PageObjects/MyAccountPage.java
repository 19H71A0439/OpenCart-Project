package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath="//h1[normalize-space()='My Account']")
    WebElement msgHeading;

    @FindBy(xpath="//h1[normalize-space()='My Account']")
    WebElement txtMyAccount;

    // Logout locator
    @FindBy(linkText="Logout")
    WebElement lnkLogout;

    public boolean VerifyMyAccount() {
        try {
            return txtMyAccount.isDisplayed();
        }
        catch(Exception e) {
            return false;
        }
    }

    public boolean isMyAccountPageExists()
    {
    	 try
    	    {
    	        return msgHeading.isDisplayed();
    	    }
    	    catch(Exception e)
    	    {
    	        return false;
    	    }
    }

    // Logout method
    public void clickLogout()
    {
        lnkLogout.click();
    }
}