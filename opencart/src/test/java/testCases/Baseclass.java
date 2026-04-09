package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;      //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
@Test
public class Baseclass {
	 public  WebDriver driver;
     public Logger logger;
     public Properties p;
     @BeforeClass(groups= {"sanity","Regression"})
     @Parameters({"os","browser"})
     public void setup(String os,String browser) throws IOException {
      FileReader file = new FileReader("./src//test//resources//config.properties");
      p= new Properties();
      p.load(file);
         switch(browser.toLowerCase()) {

             case "chrome":
                 driver = new ChromeDriver();
                 break;

             case "edge":
                 driver = new EdgeDriver();
                 break;

             case "firefox":
                 driver = new FirefoxDriver();
                 break;

             default:
                 System.out.println("invalid browser");
                 return;
         }

         logger = LogManager.getLogger(this.getClass());

         driver.manage().deleteAllCookies();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         driver.get(p.getProperty("appURL1"));
         driver.manage().window().maximize();
     }
     @AfterClass(groups= {"sanity","Regression"}) public void tearDown() { driver.quit(); }
     public String captureScreen(String tname) throws IOException {

    	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

    	    TakesScreenshot ts = (TakesScreenshot) driver;
    	    File sourceFile = ts.getScreenshotAs(OutputType.FILE);

    	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

    	    File targetFile = new File(targetFilePath);
    	    sourceFile.renameTo(targetFile);

    	    return targetFilePath;
    	}
}
