/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model;

import cz.cvut.x33eja.gowalla.model.item.*;
import cz.cvut.x33eja.gowalla.model.spot.*;
import cz.cvut.x33eja.gowalla.model.person.*;
import java.util.List;
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

	public static final String AUTH_KEY = "2113fef256942b82fc6c6e0452d0be62";

	public static long spotIdCVUT = 4085165L;

	public static long personIdOndra = 230916L;

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
		Spot spot = spotFacade.find(spotIdCVUT);
		if (spot == null) {
			spot = new Spot();
			spot.setId(spotIdCVUT);
			spotFacade.create(spot);
		}

		gowalla.updateSpotItems(spot);

		assertThat(0, not(spot.getItems().size()));
	}

	@Test
	public void testUpdateNearestSpots() throws Exception {
		Location location = new Location(50.07607, 14.41917);
		gowalla.updateNearestSpots(location);
	}

	@Test
	public void testGetMissingItemTypes() throws Exception {
		Person person = new Person();
		person.setId(personIdOndra);

		List<ItemType> missing = gowalla.getMissingItemTypes(person);
		boolean flag = false;
		for (ItemType item : missing) {
			if (item.getName().equals("Mickey Hat")) {
				flag = true;
				break;
			}
		}
		assertTrue(flag);
	}

	@Test
	public void testGetCollectionItemTypes() throws Exception {
		Person person = new Person();
		person.setId(personIdOndra);

		List<ItemType> collection = gowalla.getCollectionItemTypes(person);
		boolean flag = false;
		for (ItemType item : collection) {
			if (item.getName().equals("Marbles")) {
				flag = true;
				break;
			}
		}
		assertTrue(flag);
	}

	@Test
	public void testUpdateItemTypes() throws Exception {
		Person person = new Person();
		person.setId(personIdOndra);

		gowalla.updateItemTypes(person);
	}

}