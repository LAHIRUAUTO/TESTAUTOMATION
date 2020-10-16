package Utilities;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class TestReportSender {

    static void sendPDFReportByGMail(String from, String pass, String to, String subject, String body) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm/");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));

        Properties props = System.getProperties();

        String host = "smtp.gmail.com";

        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.host", host);

        props.put("mail.smtp.user", from);

        props.put("mail.smtp.password", pass);

        props.put("mail.smtp.port", "587");

        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(session);

        try {

            //Set from address

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

//Set subject

            message.setSubject(subject);

            message.setText(body);

            BodyPart objMessageBodyPart = new MimeBodyPart();
            BodyPart objMessageBodyPart2 = new MimeBodyPart();

            objMessageBodyPart.setText(dtf.format(now) + " : Test Result Report Attached");
            objMessageBodyPart2.setText(dtf.format(now) + " : Test Result Screen Capture Attached");

            Multipart multipart = new MimeMultipart();
            Multipart multipart2 = new MimeMultipart();

            multipart.addBodyPart(objMessageBodyPart);
            multipart2.addBodyPart(objMessageBodyPart2);

            objMessageBodyPart = new MimeBodyPart();
            objMessageBodyPart2 = new MimeBodyPart();

//Set path to the pdf report file

            String filename = "/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/Extent_Reports/Test_Result.html";
            String filename2 = "/home/user/Desktop/Sample_Structure_Test_Automation_Project with Page Factory/Screen_Capture_Result.zip";

//Create data source to attach the file in mail

            DataSource source = new FileDataSource(filename);
            objMessageBodyPart.setDataHandler(new DataHandler(source));
            objMessageBodyPart.setFileName(filename);
            multipart.addBodyPart(objMessageBodyPart);

            DataSource source2 = new FileDataSource(filename2);
            objMessageBodyPart2.setDataHandler(new DataHandler(source2));
            objMessageBodyPart2.setFileName(filename2);
            multipart.addBodyPart(objMessageBodyPart2);



            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");

            transport.connect(host, from, pass);

            transport.sendMessage(message, message.getAllRecipients());

            transport.close();

        }

        catch (AddressException ae) {

            ae.printStackTrace();

        }

        catch (MessagingException me) {

            me.printStackTrace();

        }

    }

}

