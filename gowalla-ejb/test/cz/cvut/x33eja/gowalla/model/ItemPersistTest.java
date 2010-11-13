package cz.cvut.x33eja.gowalla.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class ItemPersistTest extends EntityManagerTestCase {

	private Item item;

	@Before
	public void setUp() {
		item = new Item();
		item.setNumber(42);
		item.setItemType(new ItemType());
		item.setPerson(new Person());
		item.setSpot(new Spot());
		item.setCollection(new Collection());
	}

	@Test
	public void testPersist() {
		em.persist(item);
	}

	@Test
	public void testFind() {
		em.persist(item);
		Item found = em.find(Item.class, item.getId());
		assertEquals(item, found);
	}

	@Test
	public void testRemove() {
		em.persist(item);
		assertNotNull(em.find(Item.class, item.getId()));
		em.remove(item);
		assertNull(em.find(Spot.class, item.getId()));
	}
}