package cz.cvut.x33eja.gowalla.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class ItemTypePersistTest extends EntityManagerTestCase {

	private ItemType it;

	@Before
	public void setUp() {
		it = new ItemType();
		it.setName("foo");
		it.setImage(new Image("http://foo.cz/bar.jpg"));
	}

	@Test
	public void testPersist() {
		em.persist(it);
	}

	@Test
	public void testFind() {
		em.persist(it);
		ItemType found = em.find(ItemType.class, it.getId());
		assertEquals(it, found);
	}

	@Test
	public void testRemove() {
		em.persist(it);
		assertNotNull(em.find(ItemType.class, it.getId()));
		em.remove(it);
		assertNull(em.find(ItemType.class, it.getId()));
	}
}
