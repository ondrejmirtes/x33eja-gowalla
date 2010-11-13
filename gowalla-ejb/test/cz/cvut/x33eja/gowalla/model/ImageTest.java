package cz.cvut.x33eja.gowalla.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class ImageTest {

	@Test
	public void testUrl() {
		Image im = new Image("http://localhost/test.jpg");
		assertEquals("http://localhost/test.jpg", im.getUrl());

		im.setUrl("http://localhost/another.jpg");
		assertEquals("http://localhost/another.jpg", im.getUrl());
	}

}