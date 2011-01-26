/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model;

import cz.cvut.x33eja.gowalla.model.oauth.IOAuthFacadeLocal;
import cz.cvut.x33eja.gowalla.model.person.IPersonFacadeLocal;
import cz.cvut.x33eja.gowalla.model.person.Person;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
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

	@EJB
	public IGowallaFacadeLocal gowallaFacade;

	@EJB
	public IPersonFacadeLocal personFacade;

	@EJB
	public IOAuthFacadeLocal oauthFacade;

	@Override
    public void sendReport(Person person) {
		try {
			gowallaFacade.setAuthKey(person.getOAuth().getCode());
			gowallaFacade.updatePersonLocation(person);
			gowallaFacade.updateNearestSpots(person.getLocation());
			Properties props = new Properties();
			props.put("mail.smtp.host", "localhost");
			Message message = new MimeMessage(Session.getDefaultInstance(null));
			message.setFrom(new InternetAddress("gospot@gowalla.com", "GoSpot Report"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(person.getEmail(), person.getName()));
			message.setSubject("Report");
			message.setText("test");
			Transport.send(message);
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(SendReportFacade.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(SendReportFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
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
 
}
