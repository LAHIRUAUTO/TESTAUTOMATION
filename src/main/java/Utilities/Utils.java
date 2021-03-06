package Utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



public class Utils extends BrowserBase {

    static ExtentTest test;
    static ExtentReports report;

    @BeforeSuite
    public static void startTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm/");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now) + ": Test suite started ");
        report = new ExtentReports(System.getProperty("user.dir") + "/Extent_Reports/Test_Result.html", true);
        test = report.startTest("Test Result");

    }

    @BeforeMethod
    public static void currerntThreadId() {
        //System.out.println("Current Thread ID: "+Thread.currentThread().getId());
    }

    @BeforeMethod
    public static void implicitWait() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public static WebElement elementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));

    }


    public void selectelementByText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void waitElementVisible(String xpath) {
        WebDriverWait explicitwait = new WebDriverWait(driver,10);
        explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitElementClickable(String xpath) {
        WebDriverWait explicitwait = new WebDriverWait(driver,10);
        explicitwait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void mouseHover(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void doubleClick(WebElement element) {
        Actions action = new Actions(driver);
        action.doubleClick(element).perform();
    }


    @AfterMethod

    public void tearDown(ITestResult result) throws IOException {

        //Load Property File
        File src = new File("/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/App.properties");
        FileInputStream objfile = new FileInputStream(src);
        Properties obj = new Properties();
        obj.load(objfile);
        String setProjectPath = obj.getProperty("ProjectPath");


        test.log(LogStatus.INFO, driver + " - Test Case " + result.getName() + " Running");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm/");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));

        if (ITestResult.FAILURE == result.getStatus()) {
            test.log(LogStatus.FAIL, driver + " - Test Case " + result.getName() + " Faild");

            try {
                TakesScreenshot ts = (TakesScreenshot) driver;

                File source = ts.getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(source, new File(setProjectPath + "/Screen_Capture_Result/Failure_Screen_Capture/" + result.getName() + ".png"));

                //System.out.println("Running the Test Case : " + result.getName());
                //System.out.println("Test Failed Screenshot taken " + result.getName());

            } catch (Exception e) {

                System.out.println("Exception while taking screenshot " + e.getMessage());
            }


        } else if (ITestResult.SUCCESS == result.getStatus()) {
            test.log(LogStatus.PASS, driver + " - Test Case " + result.getName() + " Passed");

            try {
                TakesScreenshot ts = (TakesScreenshot) driver;

                File source = ts.getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(source, new File(setProjectPath + "/Screen_Capture_Result/Success_Screen_Capture/" + result.getName() + ".png"));

                //System.out.println("Running the Test Case : " + result.getName());
                //System.out.println("Test Passed Screenshot taken " + result.getName());
            } catch (Exception e) {

                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        } else if (ITestResult.SKIP == result.getStatus()) {
            test.log(LogStatus.SKIP, driver + " - Test Case " + result.getName() + " Passed");

            try {
                TakesScreenshot ts = (TakesScreenshot) driver;

                File source = ts.getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(source, new File(setProjectPath + "/Screen_Capture_Result/Skip_Screen_Capture/"+ result.getName() + ".png"));

                //System.out.println("Running the Test Case : " + result.getName());
                //System.out.println("Test Skiped Screenshot taken " + result.getName());
            } catch (Exception e) {

                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    @AfterClass
    public static void endTest() {
        report.endTest(test);
        report.flush();

    }



    @AfterSuite
    public static void endSuite() throws MessagingException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm/");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        ZipUtils.creatZipFile();
        TestReportSender.sendPDFReportByGMail("dcs.selenium.report@gmail.com", "1Slite0614", "vikasithasouth@gmail.com", dtf.format(now)+" : DCS Selenium Test Result", "Dear Mr Vikasitha,");
        //PNLSender.sendPNLByGMail();
    }
    }

