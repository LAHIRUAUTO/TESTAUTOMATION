import Pages.DcsHome.DcsDashBoard;
import Pages.Flights.Flights;
import Pages.HomePage.LoginPage;
import Pages.MainMenu.MainMenu;
import Utilities.Retry;
import Utilities.Utils;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;


public class TestRunner extends Utils {


    public TestRunner() throws IOException, BiffException {
    }

    String FilePath = "/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/Test Data/testdata.xls";
    FileInputStream fs = new FileInputStream(FilePath);
    Workbook wb = Workbook.getWorkbook(fs);
    Sheet DcsLogginSh = wb.getSheet("DcsLoggin");
    Sheet DashBoardSh = wb.getSheet("DashBoard");


    @Test(priority = 1, retryAnalyzer = Retry.class, description = "DCS Log in test case")
    public void LogInToTheDCS() throws IOException, BiffException {


        String username = DcsLogginSh.getCell("A2").getContents();
        String password = DcsLogginSh.getCell("B2").getContents();

        LoginPage newloginpage = PageFactory.initElements(driver, LoginPage.class);
        newloginpage.enterUsername(username);
        newloginpage.enterPassword(password);
        newloginpage.clicklogInButton();
    }


    //@Dataprovider annotation for loggin function
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

    @Test(dependsOnMethods = {"LogInToTheDCS"}, priority = 2, retryAnalyzer = Retry.class)
    public void GotoMainMenu() throws InterruptedException {

        MainMenu newMainMenu = PageFactory.initElements(driver, MainMenu.class);
        newMainMenu.clickMainMenuLink();

    }

    @Test(dependsOnMethods = {"LogInToTheDCS", "GotoMainMenu"}, priority = 3, enabled = true, retryAnalyzer = Retry.class)

    public void createFlight() throws InterruptedException {

        Flights newFlight = PageFactory.initElements(driver, Flights.class);
        newFlight.gotoFlightManagerLink();
        newFlight.clickFlight();
        newFlight.clickWrapper();
        newFlight.clickAddFlight();
        newFlight.selectTimeMode("UTC");
        newFlight.selectOperationType("Charter");
        newFlight.selecrAircraftModel("300B4-605R-J18Y243");
        newFlight.enterFlightNumber("3003");
        newFlight.selectSeatMap("SEAT300B4605RJ18Y243");
        newFlight.selectOperatedBy("IR");
        newFlight.selectFlightType("Domestic");
        newFlight.selectAircradtTail("EP-IBB");
        newFlight.openCallender();
        newFlight.selectDepartureDate("25");
        newFlight.selectSsrTemplate("SSR300B4605RJ18Y243");
        newFlight.selectDepartureAirport("IKA");
        newFlight.selectArrivalAirport("IST");
        newFlight.enterDepartureTimeHH("06");
        newFlight.enterArrivalTimeHH("08");
        newFlight.enterDepartureTimeMM("00");
        newFlight.enterArrivalTimeMM("30");
        newFlight.enterDepartureOffSet("0");
        newFlight.enterArrivalOffSet("0");
        newFlight.selectDepartureTerminal("T1");
        newFlight.selectArrivalTerminal("T1");
        newFlight.clicksaveButton();


    }

    @Test(dependsOnMethods = {"LogInToTheDCS"}, priority = 4, retryAnalyzer = Retry.class)
    public void GotoDashBoard() throws InterruptedException {
        DcsDashBoard newDashBoard = PageFactory.initElements(driver, DcsDashBoard.class);
        newDashBoard.clickDashBoard();
        newDashBoard.checkDashBoardtitle();
    }

    @Test(dependsOnMethods = {"LogInToTheDCS", "GotoDashBoard"}, priority = 5, retryAnalyzer = Retry.class)
    public void LoadFlightToCheckIn() throws InterruptedException {

        String flightDesignator = DashBoardSh.getCell("A2").getContents();
        DcsDashBoard newDashBoard = PageFactory.initElements(driver, DcsDashBoard.class);
        newDashBoard.searchFlight(flightDesignator);
        newDashBoard.loadCheckInFlight();
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);

    }

    @Test(priority = 6, retryAnalyzer = Retry.class)
    public void LogOut() {
        MainMenu newMainMenu = PageFactory.initElements(driver, MainMenu.class);
        newMainMenu.clickMainMenuLink();
        newMainMenu.gotoLogOutButtonLink();
        newMainMenu.clickLogOut();
    }


}

