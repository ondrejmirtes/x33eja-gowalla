/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model;

import cz.cvut.x33eja.gowalla.model.item.IItemFacadeLocal;
import cz.cvut.x33eja.gowalla.model.item.IItemTypeFacadeLocal;
import cz.cvut.x33eja.gowalla.model.item.Item;
import cz.cvut.x33eja.gowalla.model.person.IPersonFacadeLocal;
import cz.cvut.x33eja.gowalla.model.person.Person;
import cz.cvut.x33eja.gowalla.model.spot.ISpotFacadeLocal;
import cz.cvut.x33eja.gowalla.model.spot.Spot;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ondřej Mirtes
 */
@Stateless
public class SendReportFacade implements SendReportFacadeLocal {

	@Inject
	private ISpotFacadeLocal spotFacade;

	@Inject
	private IItemTypeFacadeLocal itemTypeFacade;

	@Inject
	private IItemFacadeLocal itemFacade;

	@Inject
	private IPersonFacadeLocal personFacade;

	@Override
    public void sendReport(Person person) {
		List<Spot> spots = spotFacade.findNearestSpots(person.getLocation(), 20);
		//List<Item> items = itemTypeFacade.findFollowedItems(person, spots);
		List<Item> items = itemFacade.findAll();

		StringBuilder sb = new StringBuilder();
		sb.append("<ul>");

		System.out.println(spots.size());
		System.out.println(items.size());

		for (Item i : items) {
			sb.append("<li>").append(i.getNumber()).append("</li>");
		}

		sb.append("</ul>");

		sendEmail(person.getEmail(), person.getName(), sb.toString());
	}

	@Override
	@Schedule(second="0", minute="0/2", hour="0/1")
	public void flushReports() {
		for (Person p : personFacade.findAll()) {
			if (p.getEmail() != null) {
				sendReport(p);
			}
		}
	}

	private void sendEmail(String toEmail, String toName, String text) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.starttls.enable","true");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("ondrej@mirtes.cz", "6DgjWPGhQ9sB6XM");
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("gospot@gowalla.com", "GoSpot Report"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, toName));
			message.setSubject("GoSpot Report");
			message.setContent(text, "text/html");

			Transport.send(message);

		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(SendReportFacade.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(SendReportFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
 
}
