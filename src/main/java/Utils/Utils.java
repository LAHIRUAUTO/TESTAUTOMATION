package Utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

//import org.openqa.selenium.*;



public class Utils {
    public static WebDriver driver;

    static ExtentTest test;
    static ExtentReports report;
    @Parameters("browser")
    @BeforeClass
    public static void Intialize (String browser) throws Exception {

        //Load Property File
        File src=new File("/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/App.properties");
        FileInputStream objfile = new FileInputStream(src);
        Properties obj = new Properties();
        obj.load(objfile);
        String setChromeDriver = obj.getProperty("ChromeDriver");
        String setFirefoxDriver = obj.getProperty("FirefoxDriver");
        String setOperaDriver = obj.getProperty("OperaDriver");
        String setGeckoDriver = obj.getProperty("GeckoDriver");





        //Enable headless browser testing
        /*// Create Object of ChromeOption Class
        ChromeOptions option=new ChromeOptions();

        //Set the setHeadless is equal to true which will run test in Headless mode
        option.setHeadless(true);*/

        //driver = new ChromeDriver(option);

        //Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("firefox")){
            //create firefox instance
            System.setProperty(setGeckoDriver, setFirefoxDriver);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities = DesiredCapabilities.firefox();
            capabilities.setBrowserName("firefox");
            //capabilities.setVersion("your firefox version");
            capabilities.setPlatform(Platform.LINUX);
            capabilities.setCapability("marionette", false);
            driver = new FirefoxDriver(capabilities);
            driver.get("https://dcsqa.avtra.com/dcs/#/login/en/IR");
            driver.manage().window().maximize();
        }

        else if(browser.equalsIgnoreCase("chrome")){
            //set path to chromedriver.exe
            System.setProperty(setGeckoDriver, setChromeDriver);
            //create chrome instance
            driver = new ChromeDriver();
            driver.get("https://dcsqa.avtra.com/dcs/#/login/en/IR");
            driver.manage().window().maximize();
        }

        else if(browser.equalsIgnoreCase("opera")){
            //set path to opera driver
            System.setProperty("webdriver.opera.driver", setOperaDriver);
            //create Opera instance
            driver = new OperaDriver();
            driver.get("https://dcsqa.avtra.com/dcs/#/login/en/IR");
            driver.manage().window().maximize();
        }

        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }







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




    @AfterClass
    public static void close () {

        driver.close();
    }

}
