package cz.cvut.x33eja.gowalla.model.item;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

/**
 *
 * @author Ondřej Mirtes
 */
public class CollectionTest {

	private Collection c;

	@Before
	public void setUp() {
		c = new Collection();
	}

	@Test
	public void testItems() {
		Item im = new Item();
		c.addItem(im);
		assertThat(c.getItems(), hasItem(im));
	}


}
