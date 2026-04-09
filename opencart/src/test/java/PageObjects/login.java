package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class login extends BasePage {
	  public login(WebDriver driver)
	    {
	        super(driver);
	    }

	    @FindBy(xpath="//input[@id='input-email']")
	    WebElement txtemailaddress;

	    @FindBy(xpath="//input[@id='input-password']")
	    WebElement txtpassword;
	    @FindBy(xpath="//button[normalize-space()='Login']")
	    WebElement btnlogin;

	    public void setEmail(String email)
	    {
	    	txtemailaddress.sendKeys(email);
	    }
	    public void setPassword(String password) {
	    	txtpassword.sendKeys(password);
	    }
	    public void clicklogin() {
	    	btnlogin.click();
	    }

	 
}
