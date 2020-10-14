package Utilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class BrowserBase {

    public static WebDriver driver;

    @Parameters({"browser", "url"})
    @BeforeClass
    public static void Intialize(String browser, String url) throws Exception {

        //Load Property File
        File src = new File("/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/App.properties");
        FileInputStream objfile = new FileInputStream(src);
        Properties obj = new Properties();
        obj.load(objfile);
        String setChromeDriver = obj.getProperty("ChromeDriver");
        String setFirefoxDriver = obj.getProperty("FirefoxDriver");
        String setOperaDriver = obj.getProperty("OperaDriver");
        String setGeckoDriver = obj.getProperty("GeckoDriver");

        if (browser.equalsIgnoreCase("firefox")) {
            //create firefox instance
            System.setProperty(setGeckoDriver, setFirefoxDriver);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities = DesiredCapabilities.firefox();
            capabilities.setBrowserName("firefox");
            //capabilities.setVersion("your firefox version");
            capabilities.setPlatform(Platform.LINUX);
            capabilities.setCapability("marionette", false);
            driver = new FirefoxDriver(capabilities);
            driver.get(url);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("chrome")) {
            //set path to chromedriver.exe
            System.setProperty(setGeckoDriver, setChromeDriver);
            //create chrome instance
            driver = new ChromeDriver();
            driver.get(url);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("chromeheadless")) {

            //set path to chromedriver.exe
            System.setProperty(setGeckoDriver, setChromeDriver);
            ChromeOptions option = new ChromeOptions();
            option.setHeadless(true);
            //create chrome instance
            driver = new ChromeDriver(option);
            driver.get(url);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("opera")) {
            //set path to opera driver
            System.setProperty("webdriver.opera.driver", setOperaDriver);
            //create Opera instance
            driver = new OperaDriver();
            driver.get(url);
            driver.manage().window().maximize();
        } else {
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }


    }

    @AfterClass
    public static void close() {

        driver.close();
    }
}
