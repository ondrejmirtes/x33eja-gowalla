package cz.cvut.x33eja.gowalla.model.oauth;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class OAuthTest {

    private OAuth oa;

	@Before
	public void setUp() {
		oa = new OAuth();
	}

	@Test
	public void testToken() {
		oa.setToken("foo");
		assertEquals("foo", oa.getToken());
	}

	@Test
	public void testTokenSecret() {
		oa.setTokenSecret("bar");
		assertEquals("bar", oa.getTokenSecret());
	}

}