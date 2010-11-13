package cz.cvut.x33eja.gowalla.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Collectable item.
 * 
 * @author Ondřej Mirtes
 */
public class ItemTest {

	private Item item;

	@Before
	public void setUpClass() {
		item = new Item();
	}

	@Test
	public void testNumber() {
		item.setNumber(42);
		assertEquals(new Integer(42), item.getNumber());
	}

	@Test
	public void testPerson() {
		Person p = new Person();
		item.setPerson(p);
		assertEquals(p, item.getPerson());
	}

	@Test
	public void testCollection() {
		Collection c = new Collection();
		item.setCollection(c);
		assertEquals(c, item.getCollection());
	}

	@Test
	public void testSpot() {
		Spot s = new Spot();
		item.setSpot(s);
		assertEquals(s, item.getSpot());
	}

	@Test
	public void testItemType() {
		ItemType it = new ItemType();
		item.setItemType(it);
		assertEquals(item, item.getItemType());
	}

}