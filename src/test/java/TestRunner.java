import Pages.DcsHome.DcsDashBoard;
import Pages.Flights.Flights;
import Pages.HomePage.LoginPage;
import Pages.MainMenu.MainMenu;
import Utils.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileNotFoundException;

/*import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;*/

public class TestRunner extends Utils {


    static ExtentTest test;
    static ExtentReports report;

    public TestRunner() throws FileNotFoundException {
    }



    @BeforeSuite
    public static void startTest() {
        report = new ExtentReports(System.getProperty("user.dir") + "/Test_Result.html", true);
        test = report.startTest("Test Result");
    }

    @Test(priority = 1)
    public void LogInToTheDCS() {
        LoginPage newloginpage = PageFactory.initElements(driver, LoginPage.class);
        newloginpage.enterUsername("SYSTEM");
        newloginpage.enterPassword("1Slite0614");
        newloginpage.clicklogInButton();
    }

    @Test(dependsOnMethods = {"LogInToTheDCS"}, priority = 2)
    public void GotoMainMenu() throws InterruptedException {

        MainMenu newMainMenu = PageFactory.initElements(driver, MainMenu.class);
        Thread.sleep(2000);
        newMainMenu.clickMainMenuLink();

    }

    @Test(dependsOnMethods = {"LogInToTheDCS", "GotoMainMenu"}, priority = 3)

    public void goToFlights() throws InterruptedException {

        Flights newFlight = PageFactory.initElements(driver, Flights.class);
        Thread.sleep(1000);
        newFlight.gotoFlightManagerLink();
        Thread.sleep(1000);
        newFlight.clickFlight();

    }

    @Test(dependsOnMethods = {"LogInToTheDCS"}, priority = 4)
    public void GotoDashBoard () throws InterruptedException {
        DcsDashBoard newDashBoard = PageFactory.initElements(driver, DcsDashBoard.class);
        Thread.sleep(2000);
        newDashBoard.clickDashBoard();
        newDashBoard.checkDashBoardtitle();
        newDashBoard.loadCheckInFlight();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
            test.log(LogStatus.INFO, "Test Case " +  result.getName() + " Running" );

        if (ITestResult.FAILURE == result.getStatus()) {
            test.log(LogStatus.FAIL, "Test Case " +  result.getName() + " Faild" );

            try {
                TakesScreenshot ts = (TakesScreenshot) driver;

                File source = ts.getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(source, new File("/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/Failure_Screen_Capture/" + result.getName() + ".png"));

                System.out.println("Test Failed Screenshot taken " +  result.getName());
            } catch (Exception e) {

                System.out.println("Exception while taking screenshot " + e.getMessage());
            }


        }
        else if (ITestResult.SUCCESS == result.getStatus()){
            test.log(LogStatus.PASS, "Test Case " +  result.getName() + " Passed" );

            try {
                TakesScreenshot ts = (TakesScreenshot) driver;

                File source = ts.getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(source, new File("/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/Success_Screen_Capture/" + result.getName() + ".png"));

                System.out.println("Test Passed Screenshot taken " +  result.getName());
            } catch (Exception e) {

                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }

        else if (ITestResult.SKIP == result.getStatus()){
            test.log(LogStatus.SKIP, "Test Case " +  result.getName() + " Passed" );

            try {
                TakesScreenshot ts = (TakesScreenshot) driver;

                File source = ts.getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(source, new File("/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/Success_Screen_Capture/" + result.getName() + ".png"));

                System.out.println("Test Skiped Screenshot taken " +  result.getName());
            } catch (Exception e) {

                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

        @AfterClass
        public static void endTest ()
        {
            report.endTest(test);
            report.flush();

        }


        @AfterSuite
        public static void endSuite () throws MessagingException {

            //Utils.sendEmail("proavostest@gmail.com", "vikasithasouth@gmail.com", "Test Email Attachment", "Test Email Attachment");
        }





    }

