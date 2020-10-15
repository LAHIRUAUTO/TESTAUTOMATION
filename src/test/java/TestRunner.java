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
    Sheet CreateFlight = wb.getSheet("Create_Flight");


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
        String timeMode = CreateFlight.getCell("B2").getContents();
        String operationType = CreateFlight.getCell("B3").getContents();
        String aircraftModel = CreateFlight.getCell("B4").getContents();
        String flightNumber = CreateFlight.getCell("B5").getContents();
        String seatMap = CreateFlight.getCell("B6").getContents();
        String operatedby = CreateFlight.getCell("B7").getContents();
        String flightType = CreateFlight.getCell("B8").getContents();
        String aircraftTail = CreateFlight.getCell("B9").getContents();
        String departureDate = CreateFlight.getCell("B10").getContents();
        String ssrTemplate = CreateFlight.getCell("B11").getContents();
        String departureAirport = CreateFlight.getCell("B12").getContents();
        String arrivalAirport = CreateFlight.getCell("B13").getContents();
        String departureTimHH = CreateFlight.getCell("B14").getContents();
        String arrivalTimeHH = CreateFlight.getCell("B15").getContents();
        String departureTimMM = CreateFlight.getCell("B16").getContents();
        String arrivalTimeMM = CreateFlight.getCell("B17").getContents();
        String departureOffset = CreateFlight.getCell("B18").getContents();
        String arrivalOffset = CreateFlight.getCell("B19").getContents();
        String departureTerminal = CreateFlight.getCell("B20").getContents();
        String arrivalTerminal = CreateFlight.getCell("B21").getContents();



        Flights newFlight = PageFactory.initElements(driver, Flights.class);
        newFlight.gotoFlightManagerLink();
        newFlight.clickFlight();
        newFlight.clickWrapper();
        newFlight.clickAddFlight();
        newFlight.selectTimeMode(timeMode);
        newFlight.selectOperationType(operationType);
        newFlight.selecrAircraftModel(aircraftModel);
        newFlight.enterFlightNumber(flightNumber);
        newFlight.selectSeatMap(seatMap);
        newFlight.selectOperatedBy(operatedby);
        newFlight.selectFlightType(flightType);
        newFlight.selectAircradtTail(aircraftTail);
        newFlight.openCallender();
        newFlight.selectDepartureDate(departureDate);
        newFlight.selectSsrTemplate(ssrTemplate);
        newFlight.selectDepartureAirport(departureAirport);
        newFlight.selectArrivalAirport(arrivalAirport);
        newFlight.enterDepartureTimeHH(departureTimHH);
        newFlight.enterArrivalTimeHH(arrivalTimeHH);
        newFlight.enterDepartureTimeMM(departureTimMM);
        newFlight.enterArrivalTimeMM(arrivalTimeMM);
        newFlight.enterDepartureOffSet(departureOffset);
        newFlight.enterArrivalOffSet(arrivalOffset);
        newFlight.selectDepartureTerminal(departureTerminal);
        newFlight.selectArrivalTerminal(arrivalTerminal);
        newFlight.clicksaveButton();
        newFlight.validateMessage();


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

    @Test(dependsOnMethods = {"LogInToTheDCS"}, priority = 6, retryAnalyzer = Retry.class)
    public void LogOut() {
        MainMenu newMainMenu = PageFactory.initElements(driver, MainMenu.class);
        newMainMenu.clickMainMenuLink();
        newMainMenu.gotoLogOutButtonLink();
        newMainMenu.clickLogOut();
    }


}

