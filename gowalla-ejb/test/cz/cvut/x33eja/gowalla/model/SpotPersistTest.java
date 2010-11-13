package cz.cvut.x33eja.gowalla.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class SpotPersistTest extends EntityManagerTestCase {

	private Spot s;

	@Before
	public void setUp() {
		s = new Spot();
		s.setName("foo");
		s.setImage(new Image("http://localhost/test.jpg"));
		s.setLocation(new Location(50.123, 14.856));
		s.addItem(new Item());
	}

	@Test
	public void testPersist() {
		em.persist(s);
	}

	@Test
	public void testFind() {
		em.persist(s);
		Spot found = em.find(Spot.class, s.getId());
		assertEquals(s, found);
	}

	@Test
	public void testRemove() {
		em.persist(s);
		assertNotNull(em.find(Spot.class, s.getId()));
		em.remove(s);
		assertNull(em.find(Spot.class, s.getId()));
	}
}