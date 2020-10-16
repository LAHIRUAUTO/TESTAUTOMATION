import Pages.DcsHome.DcsDashBoard;
import Pages.Flights.FlightCreate;
import Pages.Flights.FlightEdit;
import Pages.Flights.FlightSearch;
import Pages.Flights.FlightView;
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
    Sheet SearchFlight = wb.getSheet("Search_Flight");


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
            FlightCreate newFlight = PageFactory.initElements(driver, FlightCreate.class);
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
        String operatedBy = CreateFlight.getCell("B7").getContents();
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

        FlightCreate newFlightCreate = PageFactory.initElements(driver, FlightCreate.class);

        newFlightCreate.gotoFlightManagerLink();
        newFlightCreate.clickFlight();
        newFlightCreate.clickWrapper();
        newFlightCreate.clickAddFlight();
        newFlightCreate.selectTimeMode(timeMode);
        newFlightCreate.selectOperationType(operationType);
        newFlightCreate.selecrAircraftModel(aircraftModel);
        newFlightCreate.enterFlightNumber(flightNumber);
        newFlightCreate.selectSeatMap(seatMap);
        newFlightCreate.selectOperatedBy(operatedBy);
        newFlightCreate.selectFlightType(flightType);
        newFlightCreate.selectAircradtTail(aircraftTail);
        newFlightCreate.openCallender();
        newFlightCreate.selectDepartureDate(departureDate);
        newFlightCreate.selectSsrTemplate(ssrTemplate);
        newFlightCreate.selectDepartureAirport(departureAirport);
        newFlightCreate.selectArrivalAirport(arrivalAirport);
        newFlightCreate.enterDepartureTimeHH(departureTimHH);
        newFlightCreate.enterArrivalTimeHH(arrivalTimeHH);
        newFlightCreate.enterDepartureTimeMM(departureTimMM);
        newFlightCreate.enterArrivalTimeMM(arrivalTimeMM);
        newFlightCreate.enterDepartureOffSet(departureOffset);
        newFlightCreate.enterArrivalOffSet(arrivalOffset);
        newFlightCreate.selectDepartureTerminal(departureTerminal);
        newFlightCreate.selectArrivalTerminal(arrivalTerminal);
        newFlightCreate.clicksaveButton();
        newFlightCreate.validateMessage();
    }

    @Test(dependsOnMethods = {"LogInToTheDCS", "GotoMainMenu"}, priority = 4, retryAnalyzer = Retry.class)
    public void searchFlight (){
        MainMenu newMainMenu = PageFactory.initElements(driver, MainMenu.class);
        FlightCreate newFlightCreate = PageFactory.initElements(driver, FlightCreate.class);
        FlightSearch newFlighSearch = PageFactory.initElements(driver, FlightSearch.class);

        String flightNumber = SearchFlight.getCell("B5").getContents();

        newMainMenu.clickMainMenuLink();
        newFlightCreate.gotoFlightManagerLink();
        newFlightCreate.clickFlight();
        newFlightCreate.clickWrapper();
        newFlighSearch.searByFlightNumber(flightNumber);
        newFlighSearch.searchResult();


    }

    @Test(dependsOnMethods = {"LogInToTheDCS", "GotoMainMenu", "searchFlight"}, priority = 5, retryAnalyzer = Retry.class)
    public void viewFlight ()  {

        FlightView newFlighView = PageFactory.initElements(driver, FlightView.class);
        newFlighView.viewFlight();
    }

    @Test (dependsOnMethods = {"LogInToTheDCS", "GotoMainMenu", "searchFlight", "viewFlight"}, priority = 6, retryAnalyzer = Retry.class)
    public void editFlight ()  {

        FlightEdit newFlighEdit = PageFactory.initElements(driver, FlightEdit.class);
        newFlighEdit.editFlight();
    }

    @Test(dependsOnMethods = {"LogInToTheDCS"}, priority = 7, retryAnalyzer = Retry.class)
    public void GotoDashBoard() throws InterruptedException {
        DcsDashBoard newDashBoard = PageFactory.initElements(driver, DcsDashBoard.class);
        newDashBoard.clickDashBoard();
        newDashBoard.checkDashBoardtitle();
    }

    @Test(dependsOnMethods = {"LogInToTheDCS", "GotoDashBoard"}, priority = 8, retryAnalyzer = Retry.class)
    public void LoadFlightToCheckIn() throws InterruptedException {

        String flightDesignator = DashBoardSh.getCell("A2").getContents();
        DcsDashBoard newDashBoard = PageFactory.initElements(driver, DcsDashBoard.class);
        newDashBoard.searchFlight(flightDesignator);
        newDashBoard.loadCheckInFlight();
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);

    }

    @Test(dependsOnMethods = {"LogInToTheDCS"}, priority = 9, retryAnalyzer = Retry.class)
    public void LogOut() {
        MainMenu newMainMenu = PageFactory.initElements(driver, MainMenu.class);
        newMainMenu.clickMainMenuLink();
        newMainMenu.gotoLogOutButtonLink();
        newMainMenu.clickLogOut();
    }


}

