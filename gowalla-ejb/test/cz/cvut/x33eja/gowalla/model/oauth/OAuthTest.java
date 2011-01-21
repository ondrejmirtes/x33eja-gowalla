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
		oa.setAccessToken("foo");
		assertEquals("foo", oa.getAccessToken());
	}

	@Test
	public void testTokenSecret() {
		oa.setRefreshToken("bar");
		assertEquals("bar", oa.getRefreshToken());
	}

}