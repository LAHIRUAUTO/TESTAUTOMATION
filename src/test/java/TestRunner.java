import Pages.DcsHome.DcsDashBoard;
import Pages.Flights.Flights;
import Pages.HomePage.LoginPage;
import Pages.MainMenu.MainMenu;
import Utils.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
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

/*import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;*/

public class TestRunner extends Utils {


    static ExtentTest test;
    static ExtentReports report;

    public TestRunner() throws IOException, BiffException {
    }


    @BeforeSuite
    public static void startTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm/");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        report = new ExtentReports(System.getProperty("user.dir") + "/Extent_Reports/" +  dtf.format(now) + "Test_Result.html", true);
        test = report.startTest("Test Result");

    }
    String FilePath = "/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/Test Data/testdata.xls";
    FileInputStream fs = new FileInputStream(FilePath);
    Workbook wb = Workbook.getWorkbook(fs);
    Sheet DcsLogginSh = wb.getSheet("DcsLoggin");
    Sheet DashBoardSh = wb.getSheet("DashBoard");



    @BeforeSuite
    public static void implicitWait() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void LogInToTheDCS() throws IOException, BiffException {


        String username = DcsLogginSh.getCell("A2").getContents();
        String password = DcsLogginSh.getCell("B2").getContents();

            LoginPage newloginpage = PageFactory.initElements(driver, LoginPage.class);
            newloginpage.enterUsername(username);
            newloginpage.enterPassword(password);
            newloginpage.clicklogInButton();


    }



    //@Dataprovider annotation checking for loggin function
    /*@Test(priority = 1, dataProvider="getData")
    public void LogInToTheDCS(String username, String password) throws IOException, BiffException {


        //String username = DcsLogginSh.getCell("A2").getContents();
        //String password = DcsLogginSh.getCell("B2").getContents();
        try {
            LoginPage newloginpage = PageFactory.initElements(driver, LoginPage.class);
            MainMenu newMainMenu = PageFactory.initElements(driver, MainMenu.class);
            Flights newFlight = PageFactory.initElements(driver, Flights.class);
            newloginpage.enterUsername(username);
            newloginpage.enterPassword(password);
            newloginpage.clicklogInButton();
            newMainMenu.clickMainMenuLink();
            newMainMenu.gotoLogOutButtonLink();
            newMainMenu.clickLogOut();

        }
        catch (Exception e) {
            System.out.println("Loging Failure");
        }
    }
    @DataProvider
    public Object [][] getData (){
        //Rows - Number of times your test has to be repeated.
        //Columns - Number of parameters in test data.
        Object[][] data = new Object[3][2];

        // 1st row
        data[0][0] = "SYSTEM";
        data[0][1] = "1Slite0614";

        // 2nd row
        data[1][0] = "LAHIRU";
        data[1][1] = "Lahirupw";

        // 3rd row
        data[2][0] = "TEST";
        data[2][1] = "TESTPW";

        return data;


    }*/

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
    public void GotoDashBoard() throws InterruptedException {
        DcsDashBoard newDashBoard = PageFactory.initElements(driver, DcsDashBoard.class);
        Thread.sleep(2000);
        newDashBoard.clickDashBoard();
        newDashBoard.checkDashBoardtitle();
    }

    @Test(dependsOnMethods = {"LogInToTheDCS","GotoDashBoard"}, priority = 5)
    public void LoadFlightToCheckIn() throws InterruptedException {

        String flightDesignator = DashBoardSh.getCell("A2").getContents();
        DcsDashBoard newDashBoard = PageFactory.initElements(driver, DcsDashBoard.class);
        Thread.sleep(2000);
        newDashBoard.searchFlight(flightDesignator);
        newDashBoard.loadCheckInFlight();
    }

    @Test (priority = 6)
    public void LogOut (){
        MainMenu newMainMenu = PageFactory.initElements(driver, MainMenu.class);
        newMainMenu.clickMainMenuLink();
        newMainMenu.gotoLogOutButtonLink();
        newMainMenu.clickLogOut();
    }

    @AfterMethod

    public void tearDown(ITestResult result) throws IOException {

        //Load Property File
        File src = new File("/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/App.properties");
        FileInputStream objfile = new FileInputStream(src);
        Properties obj = new Properties();
        obj.load(objfile);
        String setProjectPath = obj.getProperty("ProjectPath");


        test.log(LogStatus.INFO, "Test Case " + result.getName() + " Running");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm/");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        if (ITestResult.FAILURE == result.getStatus()) {
            test.log(LogStatus.FAIL, "Test Case " + result.getName() + " Faild");

            try {
                TakesScreenshot ts = (TakesScreenshot) driver;

                File source = ts.getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(source, new File(setProjectPath + "/Screen_Capture_Result/Failure_Screen_Capture/" +  dtf.format(now) + result.getName() +  ".png"));

                System.out.println("Test Failed Screenshot taken " + result.getName());
            } catch (Exception e) {

                System.out.println("Exception while taking screenshot " + e.getMessage());
            }


        } else if (ITestResult.SUCCESS == result.getStatus()) {
            test.log(LogStatus.PASS, "Test Case " + result.getName() + " Passed");

            try {
                TakesScreenshot ts = (TakesScreenshot) driver;

                File source = ts.getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(source, new File(setProjectPath + "/Screen_Capture_Result/Success_Screen_Capture/"  +  dtf.format(now) + result.getName() +  ".png"));

                System.out.println("Test Passed Screenshot taken " + result.getName());
            } catch (Exception e) {

                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        } else if (ITestResult.SKIP == result.getStatus()) {
            test.log(LogStatus.SKIP, "Test Case " + result.getName() + " Passed");

            try {
                TakesScreenshot ts = (TakesScreenshot) driver;

                File source = ts.getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(source, new File(setProjectPath + "/Screen_Capture_Result/Skip_Screen_Capture/"  +  dtf.format(now) + result.getName() +  ".png"));

                System.out.println("Test Skiped Screenshot taken " + result.getName());
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

        //Utils.sendEmail("proavostest@gmail.com", "vikasithasouth@gmail.com", "Test Email Attachment", "Test Email Attachment");
    }


}

