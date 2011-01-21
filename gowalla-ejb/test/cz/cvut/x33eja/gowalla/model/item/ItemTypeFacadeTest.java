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

	public static final long ITEM_TYPE_ID_MANTIS = 1351L;
	public static final long ITEM_TYPE_ID_BUTTON = 1352L;

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	@Before
	public void startUp() {
		itemTypeFacade = new ItemTypeFacade();
		itemTypeFacade.setEntityManager(em);
	}

	@Test
	public void testMissingItemTypes() throws Exception {
		int cnt = itemTypeFacade.count();
		if (cnt == 0) {
			fail("This test requires ItemType data in database");
		}

		Person person = new Person();
		Item item = new Item();
		ItemType it = itemTypeFacade.find(ITEM_TYPE_ID_MANTIS);
		item.setItemType(it);
		person.addCollectionItem(item);

		List<ItemType> missing = itemTypeFacade.findMissingItemTypes(person);
		assertTrue(missing.size() > 0);
		assertFalse(missing.contains(it));
	}

	@Test
	public void testFindNotFollowedItemTypes() throws Exception {
		int cnt = itemTypeFacade.count();
		if (cnt == 0) {
			fail("This test requires ItemType data in database");
		}

		Person person = new Person();
		ItemType mantis = itemTypeFacade.find(ITEM_TYPE_ID_MANTIS);
		person.addFollowedType(mantis);
		ItemType button = itemTypeFacade.find(ITEM_TYPE_ID_BUTTON);
		person.addFollowedType(button);

		List<ItemType> notFollowed = itemTypeFacade.findNotFollowedItemTypes(person);
		assertTrue(notFollowed.size() > 0);
		assertFalse(notFollowed.contains(mantis));
		assertFalse(notFollowed.contains(button));
	}
	
}