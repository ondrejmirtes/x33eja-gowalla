package cz.cvut.x33eja.gowalla.model.oauth;

import cz.cvut.x33eja.gowalla.model.EntityManagerTestCase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class OAuthPersistTest extends EntityManagerTestCase {

	private OAuth oa;

	@Before
	public void setUp() {
		oa = new OAuth();
		oa.setToken("foo");
		oa.setTokenSecret("bar");
	}

	@Test
	public void testPersist() {
		em.persist(oa);
	}

	@Test
	public void testFind() {
		em.persist(oa);
		OAuth found = em.find(OAuth.class, oa.getId());
		assertEquals(oa, found);
	}

	@Test
	public void testRemove() {
		em.persist(oa);
		assertNotNull(em.find(OAuth.class, oa.getId()));
		em.remove(oa);
		assertNull(em.find(OAuth.class, oa.getId()));
	}
}