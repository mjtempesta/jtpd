/**
 * 
 */
package org.jtpd.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.jtpd.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.mail.smtp.SMTPTransport;

/**
 * @author altuga
 * 
 */
// TODO  Bu sýnýf her türlü maili göndermek icin mi yazýldý?
public class EmailSender extends Thread {
	
	@Autowired
	private IUserService userService;

	private static final String smtpAddress = "195.87.6.119";

    private static final String fromAddress = "info@jtpd.org";

	//private static String smtpPassword = "*****";

	private static final String messageSignature = "\n\nJava Teknolojileri ve Programcilari Dernegi\n  http://www.jtpd.org";

	private static Properties props = new Properties();

	private Logger logger = Logger.getLogger("appLogger");

	private Session mailSesion = null;

	private String toAddress;

	private String message;

	private String subject;
	
	private boolean itIsForPasswordRecovery = false;

	public EmailSender() {
		props.put("mail.smtp.host", smtpAddress);
        //props.put("mail.smtp.auth", "true");
        props.put("mail.transport.protocol", "smtp");        
        //props.put("mail.user", fromAddress);
        //props.put("mail.password", smtpPassword);
        props.put("mail.smtp.port", 25);


		mailSesion = Session.getInstance(props, null);
	}

	public void run() {
		SMTPTransport t = null;
		try {
			Thread.sleep(1000);

			// we must check if user want to get email or not.
			if (!userService.canIsendEmail(toAddress)) {
				// TODO Burada niye password recowery için olup olmadýðý kontrol ediliyor?
				if (itIsForPasswordRecovery) {
					// can send email for password recovery
				} else {
					return;
				}
				
			}
			MimeMessage msg = new MimeMessage(mailSesion);

			msg.setFrom(new InternetAddress(fromAddress, "JTPD"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					toAddress));
			msg.setSubject(subject, "utf-8");
			msg.setText(this.message, "utf-8");
			msg.setSentDate(new Date());

			t = (SMTPTransport) mailSesion.getTransport("smtp");

			int tryNumber = 0;
			while (true) {
				try {
					tryNumber++;
					t.connect();
					if (t.isConnected()) {
						if (logger.isDebugEnabled()) {
							logger.debug("SMTP : The connection has been established to SMTP host.");
						}
						break;
					} else {
						if (tryNumber > 30) {
							logger.warn("SMTP : The connection has NOT been established to SMTP host. The try number has been exceeded.");
							break;
						}
					}
				} catch (Exception ex) {
                    System.out.println( t.getLastServerResponse() );
					Thread.sleep(5000);
					logger.error("SMTP : An error occured while trying to establish a connection to SMTP. System is going to try again.", ex);
					if (tryNumber > 30) {
						logger.warn("SMTP : The connection has NOT been established to SMTP host. The try number has been exceeded.");
						break;
					}
				}
			}
           

			t.sendMessage(msg, msg.getAllRecipients());
			if (logger.isDebugEnabled()) {
				logger.debug("SMTP : Mail has been sent to -> " + toAddress);
			}
		} catch (Exception e) {
			logger.error("SMTP : Mail Sending Exception", e);
		} finally {
			try {
				if (t != null) {
					t.close();
				}
			} catch (MessagingException e) {
				logger.error("SMTP : An error occured while closing the SMTP connection.", e);
			}
		}
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static void main(String arsg[]) {
		ArrayList<String> liste = new ArrayList();
		liste.add("altug@hakia.com");
		liste.add("upux@yahoo.com");
		liste.add("albilgin@hotmail.com");
		liste.add("altuga@gmail.com");

		for (String s : liste) {

			EmailSender esw = new EmailSender();
			esw.setToAddress(s);
            esw.setSubject("test") ;
			esw.setMessage("yeni haber geldi ");
			esw.start();
		}

		System.out.println("gonderdim");
		// MailBusiness.sendMail(a,"Deneme","Deneme","C:\\Documents and
		// Settings\\Ozan\\Desktop\\ebulten.htm");
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public boolean isItIsForPasswordRecovery() {
		return itIsForPasswordRecovery;
	}

	public void setItIsForPasswordRecovery(boolean itIsForPasswordRecovery) {
		this.itIsForPasswordRecovery = itIsForPasswordRecovery;
	}
}
