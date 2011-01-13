package cz.cvut.x33eja.gowalla.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class LocationTest {

	@Test
	public void testLatitudeAndLongitude() {
		Location loc = new Location(50.123, 17.859);
		assertEquals(50.123, loc.getLatitude(), 0);
		assertEquals(17.859, loc.getLongitude(), 0);
	}

	@Test
	public void testEquals() {
		Location loc1 = new Location(10.5689, 41.7835);
		Location loc2 = new Location(13.245, 75.222);
		assertFalse(loc1.equals(loc2));
	}

}