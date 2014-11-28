package manager;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJBException;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import util.SMTPAuthenticator;

public class CustomerEmailManager {

    String emailServerName = "mailauth.comp.nus.edu.sg";
// Replace with your real name and unix email address
    String emailFromAddress = "Island Furniture Customer Service<a0099050@comp.nus.edu.sg>";
// Replace with your real name and NUSNET email address
    String toEmailAddress;
    String mailer = "JavaMailer";

    public CustomerEmailManager() {
    }

    public void setToEmailAddress(String email) {
        this.toEmailAddress = email;
    }

    public void emailCustAcct(String customerName, String custUserName, String custPwd) {
        String text = "\n\nDear " + customerName + "\nYour New Password: " + custPwd + "\n\nPlease use this new password to login to Island Furniture Website.";

        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", emailServerName);
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true"); 
            props.put("mail.smtp.debug", "true");
            javax.mail.Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            Message msg = new MimeMessage(session);
            if (msg != null) {
                msg.setFrom(InternetAddress.parse(emailFromAddress, false)[0]);
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress, false));
                msg.setSubject("[ISLAND FURNITURE] New Password for Customer User Account");
                String messageText = text;
                msg.setText(messageText);
                msg.setHeader("X-Mailer", mailer);
                msg.setSentDate(new Date());
                Transport.send(msg);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new EJBException(e.getMessage());
        }
    }
}
