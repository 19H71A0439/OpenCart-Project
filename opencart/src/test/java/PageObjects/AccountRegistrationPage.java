package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	public AccountRegistrationPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath="//input[@id='input-firstname']")
    WebElement txtFirstname;

    @FindBy(xpath="//input[@id='input-lastname']")
    WebElement txtLastname;

    @FindBy(xpath="//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath="//input[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(xpath="//input[@id='input-password']") 
    WebElement txtPassword;

    @FindBy(xpath="//input[@id='input-confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath="//input[@name='agree']")
    WebElement chkPolicy;

    @FindBy(xpath="//button[normalize-space()='Continue']")
    WebElement btnContinue;

    @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;
    By chkPrivacyPolicy = By.name("agree");
    public void setFirstName(String fname)
    {
        txtFirstname.sendKeys(fname);
    }

    public void setLastName(String lname)
    {
        txtLastname.sendKeys(lname);
    }

    public void setEmail(String email)
    {
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String tel)
    {
        txtTelephone.sendKeys(tel);
    }

    public void setPassword(String pwd)
    {
        txtPassword.sendKeys(pwd);
    }

    public void setConfirmPassword(String pwd)
    {
        txtConfirmPassword.sendKeys(pwd);
    }

    public void clickPolicy()
    {
        chkPolicy.click();
    }

	/*
	 * public void clickContinue() { btnContinue.click(); }
	 */
    public void clickContinue() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnContinue);
        
        // Wait a moment for scroll to complete
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        
        // Use JS click to bypass overlay/intercept issue
        js.executeScript("arguments[0].click();", btnContinue);
    }
    public void setPrivacyPolicy() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", chkPolicy);
    }
    public String getConfirmationMsg()
    {
        try
        {
            return msgConfirmation.getText();
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
    }
}

