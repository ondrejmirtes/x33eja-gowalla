/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model;

import cz.cvut.x33eja.gowalla.model.person.PersonFacade;
import cz.cvut.x33eja.gowalla.model.person.Person;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vasek
 */
public class GowallaFacadeTest extends EntityManagerTestCase {

	private GowallaFacade gowalla;

	private PersonFacade personFacade;

	@Before
	public void startUp() {
		personFacade = new PersonFacade();
		personFacade.setEntityManager(em);

		gowalla = new GowallaFacade();
		gowalla.setPersonFacade(personFacade);
	}

	@Test
	public void testUpdatePersonLocation() throws Exception {
		Person person = new Person();
		personFacade.create(person);

		gowalla.updatePersonLocation(person);

		assertNotNull(person.getLocation().getLatitude());
		assertNotNull(person.getLocation().getLongitude());
	}

}