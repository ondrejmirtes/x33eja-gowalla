package cz.cvut.x33eja.gowalla.model.item;

import cz.cvut.x33eja.gowalla.model.EntityManagerTestCase;
import cz.cvut.x33eja.gowalla.model.spot.Spot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class CollectionPersistTest extends EntityManagerTestCase {

	private Collection c;

	@Before
	public void setUp() {
		c = new Collection();
		c.addItem(new Item());
		c.addItem(new Item());
	}

	@Test
	public void testPersist() {
		em.persist(c);
	}

	@Test
	public void testFind() {
		em.persist(c);
		Collection found = em.find(Collection.class, c.getId());
		assertEquals(c, found);
	}

	@Test
	public void testRemove() {
		em.persist(c);
		assertNotNull(em.find(Collection.class, c.getId()));
		em.remove(c);
		assertNull(em.find(Spot.class, c.getId()));
	}
}
