/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model;

import cz.cvut.x33eja.gowalla.model.item.ItemFacade;
import cz.cvut.x33eja.gowalla.model.item.ItemTypeFacade;
import cz.cvut.x33eja.gowalla.model.spot.Spot;
import cz.cvut.x33eja.gowalla.model.spot.SpotFacade;
import cz.cvut.x33eja.gowalla.model.person.PersonFacade;
import cz.cvut.x33eja.gowalla.model.person.Person;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Vasek
 */
public class GowallaFacadeTest extends EntityManagerTestCase {

	private GowallaFacade gowalla;

	private PersonFacade personFacade;

	private SpotFacade spotFacade;

	private ItemFacade itemFacade;

	private ItemTypeFacade itemTypeFacade;

	public static final String AUTH_KEY = "fd47346444c9563bffea9af27a1b92e0";

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	@Before
	public void startUp() {
		personFacade = new PersonFacade();
		personFacade.setEntityManager(em);

		spotFacade = new SpotFacade();
		spotFacade.setEntityManager(em);

		itemFacade = new ItemFacade();
		itemFacade.setEntityManager(em);

		itemTypeFacade = new ItemTypeFacade();
		itemTypeFacade.setEntityManager(em);

		gowalla = new GowallaFacade();
		gowalla.setAuthKey(AUTH_KEY);
		gowalla.setPersonFacade(personFacade);
		gowalla.setSpotFacade(spotFacade);
		gowalla.setItemFacade(itemFacade);
		gowalla.setItemTypeFacade(itemTypeFacade);
	}

	@Test
	public void testUpdatePersonLocation() throws Exception {
		Person person = new Person();
		personFacade.create(person);

		gowalla.updatePersonLocation(person);

		assertNotNull(person.getLocation().getLatitude());
		assertNotNull(person.getLocation().getLongitude());
	}

	@Test
	public void testUpdateSpotItems() throws Exception {
		long id = 4085165L;
		Spot spot = spotFacade.find(id);
		if (spot == null) {
			spot = new Spot();
			spot.setId(id);
			spotFacade.create(spot);
		}

		gowalla.updateSpotItems(spot);

		assertThat(0, not(spot.getItems().size()));
	}

}