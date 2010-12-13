package cz.cvut.x33eja.gowalla.model.spot;

import cz.cvut.x33eja.gowalla.model.Image;
import cz.cvut.x33eja.gowalla.model.Location;
import cz.cvut.x33eja.gowalla.model.item.Item;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class SpotTest {

	private Spot spot;

	@Before
	public void setUp() {
		spot = new Spot();
	}

	@Test
	public void testName() {
		spot.setName("foo");
		assertEquals("foo", spot.getName());
	}

	@Test
	public void testLocation() {
		Location loc = new Location(50.123, 14.456);
		spot.setLocation(loc);
		assertEquals(loc, spot.getLocation());
	}

	@Test
	public void testImage() {
		Image im = new Image("http://localhost/test.jpg");
		spot.setImage(im);
		assertEquals(im, spot.getImage());
	}

	@Test
	public void testItem() {
		Item item = new Item();
		spot.addItem(item);
		assertThat(spot.getItems(), hasItem(item));

		spot.removeItem(item);
		assertThat(spot.getItems(), not(hasItem(item)));
	}
}
