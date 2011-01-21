package cz.cvut.x33eja.gowalla.model.item;

import cz.cvut.x33eja.gowalla.model.person.Person;
import cz.cvut.x33eja.gowalla.model.EntityManagerTestCase;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vasek
 */
public class ItemTypeFacadeTest extends EntityManagerTestCase {

	private ItemTypeFacade itemTypeFacade;

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	@Before
	public void startUp() {
		itemTypeFacade = new ItemTypeFacade();
		itemTypeFacade.setEntityManager(em);
	}

	@Test
	public void testFindNotFollowedItemTypes() throws Exception {
		int cnt = itemTypeFacade.count();
		if (cnt == 0) {
			fail("This test requires ItemType data in database");
		}

		Person person = new Person();
		ItemType mantis = itemTypeFacade.find(1351L);
		person.addFollowedType(mantis);
		ItemType button = itemTypeFacade.find(1352L);
		person.addFollowedType(button);

		List<ItemType> notFollowed = itemTypeFacade.findNotFollowedItemTypes(person);
		assertTrue(notFollowed.size() > 0);
		assertFalse(notFollowed.contains(mantis));
		assertFalse(notFollowed.contains(button));
	}
	
}