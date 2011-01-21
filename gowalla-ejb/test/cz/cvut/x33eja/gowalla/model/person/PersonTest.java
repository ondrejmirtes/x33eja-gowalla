package cz.cvut.x33eja.gowalla.model.person;

import cz.cvut.x33eja.gowalla.model.Location;
import cz.cvut.x33eja.gowalla.model.item.Collection;
import cz.cvut.x33eja.gowalla.model.item.Item;
import cz.cvut.x33eja.gowalla.model.oauth.OAuth;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class PersonTest {

	private Person p;

	@Before
	public void setUp() {
		p = new Person();
	}

	@Test
	public void testName() {
		p.setName("foo");
		assertEquals("foo", p.getName());
	}

	@Test
	public void testNick() {
		p.setNick("bar");
		assertEquals("bar", p.getNick());
	}

	@Test
	public void testLocation() {
		Location loc = new Location(50.10, 14.11);
		p.setLocation(loc);
		assertEquals(loc, p.getLocation());
	}

	@Test
	public void testOAuth() {
		OAuth oa = new OAuth();
		oa.setAccessToken("foo");
		oa.setRefreshToken("bar");

		p.setOAuth(oa);
		assertEquals(oa, p.getOAuth());
	}

	@Test
	public void testCollection() {
		Collection col = new Collection();
		col.addItem(new Item());
		col.addItem(new Item());

		p.setCollection(col);
		assertEquals(col, p.getCollection());
	}

	@Test
	public void testItems() {
		Item i = new Item();
		p.addItem(i);
		assertThat(p.getItems(), hasItem(i));

		p.removeItem(i);
		assertThat(p.getItems(), not(hasItem(i)));
	}

	@Test
	public void testFollowedTypes() {
		//ItemType = new ItemType
	}
}
