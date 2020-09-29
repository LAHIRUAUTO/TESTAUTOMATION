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

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

//import org.openqa.selenium.*;



public class Utils {
    public static WebDriver driver;
    static ExtentTest test;
    static ExtentReports report;
    @BeforeSuite
    public static void Intialize () {


        System.setProperty("webdriver.gecko.driver", "/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/resouces/chromedriver_linux64");
        // Create Object of ChromeOption Class
        ChromeOptions option=new ChromeOptions();

        //add the –headless argument in option class which will run test in Headless mode
        option.addArguments("–headless");

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
    public static void sendEmail(String from, String to, String subject,
                            String text) throws MessagingException {
        // Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                "proavostest@gmail.com",
                                "1Slite0614");// change accordingly
                    }
                });

        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(from));
            /*
             * for (String cc : ccs)
             * message.addRecipient(Message.RecipientType.CC,new
             * InternetAddress(cc));
             */
            message.setSubject(subject);
            // Option 1: To send normal text message
             message.setText(text);
            // Option 2: Send the actual HTML message, as big as you like
            // message.setContent("<h1>This is actual message</h1></br></hr>" +
            // text, "text/html");

            // Set the attachment path
            String filename = "/home/user/Desktop/Sample_Structure_Test_Automation_Project/Test_Result.html";

            BodyPart objMessageBodyPart = new MimeBodyPart();
            // Option 3: Send text along with attachment
            objMessageBodyPart.setContent(
                    "<h1>Mail from Selenium Project!</h1></br>" + text, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(objMessageBodyPart);

            objMessageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filename);
            objMessageBodyPart.setDataHandler(new DataHandler(source));
            objMessageBodyPart.setFileName(filename);
            multipart.addBodyPart(objMessageBodyPart);
            message.setContent(multipart);

            // send message
            Transport.send(message);

            System.out.println("message sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }// End of SEND method



    @AfterSuite
    public static void close () {
       driver.close();
    }



}
