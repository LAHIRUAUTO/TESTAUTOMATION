package Utilities;

import javax.mail.*;
import javax.mail.internet.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class PNLSender {


    static void sendPNLByGMail() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        String from = "dcs.selenium.report@gmail.com";
        String pass = "1Slite0614";
        String to = "lahiru@proavoslabs.com";
        String subject = dtf.format(now)+ "PNL";
        String body =
                "PNL\n" +
                "IR502/18OCT IFN PART1\n" +
                "RBD Y/YSVGQKMNXLRTBO\n" +
                "-THR00Y\n" +
                "-THR00S\n" +
                "-THR00V\n" +
                "-THR00G\n" +
                "-THR00Q\n" +
                "-THR02K\n" +
                "1SMITHAHMADZADEH/SIYAVASHMR-A2 .L/QOFHUU .R/STCR HK1\n" +
                ".R/TKNE HK1 0960110003415/1\n" +
                ".R/DOCS HK1/P/IR/A12345615/IR/17FEB92/M/26JUL24/SMITHAHMADZADEH/\n" +
                ".RN/SIYAVASH-1SMITHAHMADZADEH/SIYAVASHMR\n" +
                ".R/DOCS HK1/I//111111111126/IR-1SMITHAHMADZADEH/SIYAVASHMR\n" +
                "-THR00M\n" +
                "-THR00N\n" +
                "-THR00X\n" +
                "-THR00L\n" +
                "-THR00R\n" +
                "-THR00T\n" +
                "-THR00B\n" +
                "-THR00O\n" +
                "ENDPNL\n";

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

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);

            message.setText(body);

            BodyPart objMessageBodyPart = new MimeBodyPart();

            objMessageBodyPart.setText(body);

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(objMessageBodyPart);


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
