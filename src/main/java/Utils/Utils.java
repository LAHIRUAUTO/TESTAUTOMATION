package Utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//import org.openqa.selenium.*;



public class Utils {
    public static WebDriver driver;
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
        String setGeckoDriver = obj.getProperty("GeckoDriver");


        System.setProperty(setGeckoDriver, setChromeDriver);

        //Enable headless browser testing
        // Create Object of ChromeOption Class
        ChromeOptions option=new ChromeOptions();

        //Set the setHeadless is equal to true which will run test in Headless mode
        option.setHeadless(true);

        //driver = new ChromeDriver(option);

        driver = new ChromeDriver(option);
        driver.get("https://dcsqa.avtra.com/dcs/#/login/en/IR");
        driver.manage().window().maximize();
    }


    public static WebElement elementByXpath(String xpath){
        return driver.findElement(By.xpath(xpath));

    }

    public void mouseHover (WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }


    //Screen capture
    public static String getScreenshot(WebDriver driver)
        {
            TakesScreenshot ts=(TakesScreenshot) driver;

            File src=ts.getScreenshotAs(OutputType.FILE);

            String path=System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";

            File destination=new File(path);

            try
            {
                FileUtils.copyFile(src, destination);
            } catch (IOException e)
            {
                System.out.println("Capture Failed "+e.getMessage());
            }

            return path;
        }



    //Email sender
    public static void sendEmail() throws MessagingException {
        // Setup mail server
        String to = "vikasithasouth@gmail.com";
        String from = "proavostest@gmail.com";
        String host = "localhost"; // or IP address
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try {

            // javax.mail.internet.MimeMessage class
            // is mostly used for abstraction.
            MimeMessage message = new MimeMessage(session);

            // header field of the header.
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("subject");
            message.setText("Hello, aas is sending email ");

            // Send message
            Transport.send(message);
            System.out.println("Yo it has been sent..");
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }// End of SEND method

    @AfterSuite
    public static void close () {
       driver.close();
    }

}
