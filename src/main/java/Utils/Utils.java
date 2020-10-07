package Utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//import org.openqa.selenium.*;



public class Utils {
    public static WebDriver driver;
    public static WebDriver driver2;

    static ExtentTest test;
    static ExtentReports report;
    @BeforeSuite
    public static void Intialize () throws IOException {

        //Load Property File
        File src=new File("/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/App.properties");
        FileInputStream objfile = new FileInputStream(src);
        Properties obj = new Properties();
        obj.load(objfile);
        String setChromeDriver = obj.getProperty("ChromeDriver");
        String setFirefoxDriver = obj.getProperty("FirefoxDriver");
        String setGeckoDriver = obj.getProperty("GeckoDriver");


        System.setProperty(setGeckoDriver, setChromeDriver);
        System.setProperty(setGeckoDriver, setFirefoxDriver);

        //Enable headless browser testing
        /*// Create Object of ChromeOption Class
        ChromeOptions option=new ChromeOptions();

        //Set the setHeadless is equal to true which will run test in Headless mode
        option.setHeadless(true);*/

        //driver = new ChromeDriver(option);

        /*driver = new ChromeDriver();
        driver.get("https://dcsqa.avtra.com/dcs/#/login/en/IR");
        driver.manage().window().maximize();*/

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities = DesiredCapabilities.firefox();
        capabilities.setBrowserName("firefox");
        //capabilities.setVersion("your firefox version");
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setCapability("marionette", false);
        driver2 = new FirefoxDriver(capabilities);
        driver2.get("https://dcsqa.avtra.com/dcs/#/login/en/IR");
        driver2.manage().window().maximize();


    }

    @BeforeMethod
    public static void currerntThreadId (){
        System.out.println("Current Thread ID: "+Thread.currentThread().getId());
    }


    public static WebElement elementByXpath(String xpath){
        return driver.findElement(By.xpath(xpath));

    }

    public void mouseHover (WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }




    @AfterSuite
    public static void close () {

        driver.close();
    }

}
