package cz.cvut.x33eja.gowalla.model.person;

import cz.cvut.x33eja.gowalla.model.EntityManagerTestCase;
import cz.cvut.x33eja.gowalla.model.item.Item;
import cz.cvut.x33eja.gowalla.model.item.ItemType;
import cz.cvut.x33eja.gowalla.model.oauth.OAuth;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class PersonPersistTest extends EntityManagerTestCase {

	private Person p;

	@Before
	public void setUp() {
		p = new Person();
		p.setName("foo");
		p.setNick("bar");
		p.addItem(new Item());
		p.addFollowedType(new ItemType());

		OAuth oa = new OAuth();
		oa.setToken("foo");
		oa.setTokenSecret("bar");
		p.setOAuth(oa);
	}

	@Test
	public void testPersist() {
		em.persist(p);
	}

	@Test
	public void testFind() {
		em.persist(p);
		Person found = em.find(Person.class, p.getId());
		assertEquals(p, found);
	}

	@Test
	public void testRemove() {
		em.persist(p);
		assertNotNull(em.find(Person.class, p.getId()));
		em.remove(p);
		assertNull(em.find(Person.class, p.getId()));
	}
}
