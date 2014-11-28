/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manager;

import entity.kms.KitchenPurchaseOrder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.ejb.EJBException;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import util.SMTPAuthenticator;

/**
 *
 * @author Meiling
 */
public class KitchenSupplierEmailManager {
    String emailServerName = "mailauth.comp.nus.edu.sg";
// Replace with your real name and unix email address
    String emailFromAddress = "Island Furniture Kitchen Management System<a0099050@comp.nus.edu.sg>";
// Replace with your real name and NUSNET email address
    String toEmailAddress;
    String mailer = "JavaMailer";

    public KitchenSupplierEmailManager() {
    }

    public void setToEmailAddress(String email) {
        this.toEmailAddress = email;
    }

    public void emailSupKPO(KitchenPurchaseOrder selectedKitchenPurchaseOrder) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
        DecimalFormat decf = new DecimalFormat("#0.00");
        String text = "<p style=\"font-family:courier;\">Dear <b>" + selectedKitchenPurchaseOrder.getKitchenSupplier().getKsupplierName() + "</b>, <br><br>This is a <b><font color=\"red\">confirmation email</font></b> for the <b>purchase order</b> from <b>Island Furniture Restaurant</b>. </p>"
                + "<br><br><p style=\"font-family:courier;\">THE PURCHASE ORDER INFORMATION IS AS FOLLOWS:</p>"
                + "<hr/><p style=\"font-family:courier;\">"
                + "<span style=\"font-size=24\"><u>Purchase Order Information:</u></span><br>"
                + "<br><b>PURCHASE ORDER ID:</b>" + selectedKitchenPurchaseOrder.getKpurchaseOrderId()
                + "<br><b>INGREDIENT NAME:</b> " + selectedKitchenPurchaseOrder.getIngredient().getIngredientName() + "<br><b>QUANTITY: </b>" + selectedKitchenPurchaseOrder.getKqty()
                + "<br><b>SCHEDULED DELIVERY DATE: </b>" + df.format(selectedKitchenPurchaseOrder.getKscheduledDeliveryDate()) + "<br><b>UNIT PRICE: </b>$" + decf.format(selectedKitchenPurchaseOrder.getKunitPrice())
                + "<br><b>TOTAL PRICE: </b><b><u>$" + decf.format(selectedKitchenPurchaseOrder.getKtotalPrice()) + "</u></b></p>"
                + "<hr/>"
                + "<br><br><i>Please acknowledge the email to indicate the confirmation.</i>";

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
                msg.setSubject("[ISLAND FURNITURE RESTAURANT] CONFIRMATION OF NEW INCOMING PURCHASE ORDER");
                String messageText = text;
                //msg.setText(messageText);
                msg.setContent(messageText, "text/html");
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
