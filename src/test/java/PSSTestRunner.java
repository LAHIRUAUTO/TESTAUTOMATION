import DCSPages.MainMenu.MainMenu;
import PSSPages.AdminHomePage.AdminLoginPage;
import PSSPages.AgentHomePage.AgentLoginPage;
import Utilities.Retry;
import Utilities.Utils;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;


public class PSSTestRunner extends Utils {


    public PSSTestRunner() throws IOException, BiffException {

    }
    Logger logger=Logger.getLogger("PSSTestRunner");

    String FilePath = "/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/Test Data/PSStestdata.xls";
    FileInputStream fs = new FileInputStream(FilePath);
    Workbook wb = Workbook.getWorkbook(fs);
    Sheet PssAdminLogginSh = wb.getSheet("PssAdminLoggin");
    Sheet PssAgentLogginSh = wb.getSheet("PssAdminLoggin");




    @Test(priority = 1, retryAnalyzer = Retry.class, description = "PSS Admin Log in test case")
    public void LogInToThePSSAdmin() throws IOException, BiffException {


        String username = PssAdminLogginSh.getCell("A2").getContents();
        String password = PssAdminLogginSh.getCell("B2").getContents();

        AdminLoginPage newadminloginpage = PageFactory.initElements(driver, AdminLoginPage.class);
        newadminloginpage.enterUsername(username);
        newadminloginpage.enterPassword(password);
        newadminloginpage.clicklogInButton();
        logger.info("Test Case: LogInToThePSSAdmin - Passed");
    }

    @Test(priority = 1, retryAnalyzer = Retry.class, description = "PSS Agent Log in test case")
    public void LogInToThePSSAgent() throws IOException, BiffException {


        String username = PssAgentLogginSh.getCell("A2").getContents();
        String password = PssAgentLogginSh.getCell("B2").getContents();

        AgentLoginPage newagentloginpage = PageFactory.initElements(driver, AgentLoginPage.class);
        newagentloginpage.enterUsername(username);
        newagentloginpage.enterPassword(password);
        newagentloginpage.clicklogInButton();
        logger.info("Test Case: LogInToThePSSAgent - Passed");
    }




    @Test(dependsOnMethods = {"LogInToThePSSAdmin"}, priority = 9, retryAnalyzer = Retry.class)
    public void LogOutPSSAdmin() {
        MainMenu newMainMenu = PageFactory.initElements(driver, MainMenu.class);
        newMainMenu.clickMainMenuLink();
        newMainMenu.gotoLogOutButtonLink();
        newMainMenu.clickLogOut();
    }


}

