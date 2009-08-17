/*
 * MailBusiness.java
 *
 * Created on 08 October 2005, 14:21
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.jtpd.mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.jtpd.domain.model.Activities;
import org.jtpd.domain.model.ActivityCustomer;
import org.jtpd.domain.model.Customer;

import com.sun.mail.smtp.SMTPTransport;

/**
 * 
 * @author Ozan
 */
public class ActivityMailSender {

	private static String smtpAddress = "mail.jtpd.org";

	private static String fromAddress = "egitim@jtpd.org";

	private static String smtpPassword = "egiTim5621";

	private static String messageSignature = "\n\nJava Teknolojileri ve Programcilari Dernegi\n  http://www.jtpd.org";

	private static Properties props = System.getProperties();

	private Logger logger = Logger.getLogger("appLogger");

	private Session mailSesion = null;

	/** Creates a new instance of Mail */
	public ActivityMailSender() {
		connect();
	}

	private void connect() {
		props.put("mail.smtp.host", smtpAddress);
		props.put("mail.smtp.auth", "true");
		mailSesion = Session.getInstance(props, null);
	}

	// TODO COMMENTLERI KALDIR
	public void sendMail(Customer customer, Activities activity,
			ActivityCustomer ac) throws Exception {
//		HttpServletRequest request = (HttpServletRequest) FacesContext
//				.getCurrentInstance().getExternalContext().getRequest();
//		StringBuffer urlPath = request.getRequestURL();
//		int index = urlPath.indexOf("public");
//		String FromStart = urlPath.substring(0, index - 1);
//		String path = FacesContext.getCurrentInstance().getExternalContext()
//				.getRequestContextPath()
//				+ "/content/Email.html";
//
//		URL url = new URL(FromStart + path);
//
//		InputStreamReader isr = new InputStreamReader(url.openStream());
//		BufferedReader reader = new BufferedReader(isr);
//		int counter = 0;
//		StringBuffer email = new StringBuffer();
//		String content = reader.readLine();
//		while (content != null) {
//
//			if (content.indexOf("$") != -1) {
//				// we have found $
//
//				if (counter == 0) { // change name and surname
//					content = content.replaceAll("[$]", customer.getName()
//							+ " " + customer.getSurname());
//				} else if (counter == 1) { // change training start date
//					content = content
//							.replaceAll("[$]", activity.getStartDate());
//				} else if (counter == 2) { // change name of the training
//					content = content.replaceAll("[$]", activity
//							.getTrainingNames().getActivityName());
//				} else if (counter == 3) { // put the verification link
//
//					String id = Integer.toString(ac.getId());
//					// TODO JSF baðýmlýlýðý kaldýrýlmalý
//					String newContent = FromStart
//							+ FacesContext.getCurrentInstance()
//									.getExternalContext()
//									.getRequestContextPath()
//							+ "/TrainingRegistrationVerification.jsf?r="
//							+ URLEncoder.encode(EncryptionService.getInstance()
//									.encrypt(id));
//					content = content.replaceAll("[$]", newContent);
//				}
//
//				counter++;
//			}
//
//			email.append(content); // add the new email content
//			content = reader.readLine();
//		}
//
//		this.sendMail(customer.getEmail(), activity.getTrainingNames()
//				.getActivityName()
//				+ " onay epostasi " + " ", email.toString());
	}

	public void sendMail(String toAddress, String subject, String message) {

		SMTPTransport t = null;
		try {
			MimeMessage msg = new MimeMessage(mailSesion);

			Multipart multipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setContent(message, "text/html; charset=\"UTF-8\"");

			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			msg.setFrom(new InternetAddress(fromAddress));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					toAddress));
			msg.setSubject("JTPD Egitim Onay Epostasi");
			// msg.setText(message + messageSignature);
			msg.setSentDate(new Date());
			t = (SMTPTransport) mailSesion.getTransport("smtp");
			t.connect(smtpAddress, fromAddress, smtpPassword);
			t.sendMessage(msg, msg.getAllRecipients());
			if (logger.isDebugEnabled()) {
				System.out.println("Mail has been sent to -> " + toAddress);
				logger.debug("Mail has been sent to -> " + toAddress);
			}
		} catch (Exception e) {
			System.out.println("Mail Sending Exception " + e);
			logger.error("Mail Sending Exception", e);
		} finally {
			try {
				t.close();
			} catch (MessagingException e) {
				System.out.println("Mail Sending Exception " + e);
				logger.error("Mail Sending Exception", e);
			}
		}
	}

	public static void main(String arsg[]) {
		ArrayList a = new ArrayList();
		a.add("ozanc@java.org.tr");
		a.add("ozan_cil@yahoo.com");
		a.add("altuga@java.org.tr");
		a.add("altuga@gmail.com");
		ActivityMailSender business = new ActivityMailSender();
		business
				.sendMail(
						"albilgin@hotmail.com",
						"Deneme",
						"MEET PEOPLE who asked the same question \r. altuga@jtpd.org. Please click on the activation link below to complete the posting process. \r Activate my message If you can't click on the link please copy and paste the line below into your browser's address bar. http://meet.hakia.com/activate_post.aspx?id=iRX8jsN%2bgmU%3d&r=xNqpjy1ZLmU%3d&n=teach+finance");

		System.out.println("gonderdim");
	}
}