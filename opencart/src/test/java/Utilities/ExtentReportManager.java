package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.Baseclass;

public class ExtentReportManager  extends Baseclass implements ITestListener{
	public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;
	public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter("./reports/" + repName);
        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("OS", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> groups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!groups.isEmpty()) {
            extent.setSystemInfo("Groups", groups.toString());
        }
    }

    // ✅ PASS
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " PASSED");
    }

    // ❌ FAIL + Screenshot
    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName() + " FAILED");

        if(result.getThrowable() != null) {
            test.log(Status.INFO, result.getThrowable().getMessage());
        }

        try {
            String imgPath = captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.SKIP, result.getName() + " SKIPPED");

        if(result.getThrowable() != null) {
            test.log(Status.INFO, result.getThrowable().getMessage());
        }
    }

    // 🔚 END REPORT
    public void onFinish(ITestContext testContext) {
        extent.flush();
        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
		/*
		 * try { URL url = new URL("file:///"
		 * +System.getProperty("user.dir")+"\\reports\\"+repName);
		 * 
		 * // Create Email MultiPartEmail email = new MultiPartEmail();
		 * 
		 * email.setHostName("smtp.gmail.com"); email.setSmtpPort(465);
		 * email.setAuthenticator(new DefaultAuthenticator("saitanujar@gmail.com",
		 * "Rsai05062001@")); email.setSSLOnConnect(true);
		 * 
		 * // Sender & Receiver email.setFrom("saitanujar@gmail.com");
		 * email.addTo("rsaitanuja@gmail.com");
		 * 
		 * email.setSubject("Test Results Report");
		 * email.setMsg("Please find attached Extent Report");
		 * 
		 * // Attach Report EmailAttachment attachment = new EmailAttachment();
		 * attachment.setURL(url);
		 * attachment.setDisposition(EmailAttachment.ATTACHMENT);
		 * attachment.setName("ExtentReport.html");
		 * 
		 * email.attach(attachment);
		 * 
		 * email.send();
		 * 
		 * System.out.println("Email sent successfully");
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
    }

}
