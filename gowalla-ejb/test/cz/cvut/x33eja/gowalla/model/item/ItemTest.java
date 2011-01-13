package cz.cvut.x33eja.gowalla.model.item;

import cz.cvut.x33eja.gowalla.model.person.Person;
import cz.cvut.x33eja.gowalla.model.spot.Spot;
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
		Spot s = new Spot();
		Person p = new Person();
		item.setSpot(s);
		item.setPerson(p);
		assertEquals(p, item.getPerson());
		assertNull(item.getSpot());
		assertNull(item.getCollection());
	}

	@Test
	public void testCollection() {
		Collection c = new Collection();
		item.setCollection(c);
		assertEquals(c, item.getCollection());
		assertNull(item.getSpot());
		assertNull(item.getPerson());
	}

	@Test
	public void testSpot() {
		Spot s = new Spot();
		item.setSpot(s);
		assertEquals(s, item.getSpot());
		assertNull(item.getPerson());
		assertNull(item.getCollection());
	}

	@Test
	public void testItemType() {
		ItemType it = new ItemType();
		item.setItemType(it);
		assertEquals(item, item.getItemType());
	}

}