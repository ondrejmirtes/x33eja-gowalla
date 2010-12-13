/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model.item;

import cz.cvut.x33eja.gowalla.model.Image;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class ItemTypeTest {

    private ItemType it;

	@Before
	public void setUp() {
		it = new ItemType();
	}

	@Test
	public void testName() {
		it.setName("foo");
		assertEquals("foo", it.getName());
	}

	@Test
	public void testImage() {
		Image im = new Image("http://foobar.cz/test.jpg");
		it.setImage(im);
		assertEquals(im, it.getImage());
	}


}