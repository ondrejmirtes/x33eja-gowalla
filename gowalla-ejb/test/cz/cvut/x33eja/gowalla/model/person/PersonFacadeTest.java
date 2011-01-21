package cz.cvut.x33eja.gowalla.model.person;

import cz.cvut.x33eja.gowalla.model.item.ItemType;
import cz.cvut.x33eja.gowalla.model.EntityManagerTestCase;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vasek
 */
public class PersonFacadeTest extends EntityManagerTestCase {

	private PersonFacade personFacade;

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	@Before
	public void startUp() {
		personFacade = new PersonFacade();
		personFacade.setEntityManager(em);
	}

	@Test
	public void testAddFollowedItemType() throws Exception {
		Person person = new Person();
		personFacade.create(person);

		ItemType it = new ItemType();
		it.setName("addFollowed");
		personFacade.addFollowedItemType(person, it);

		Person found = personFacade.find(person.getId());
		assertTrue(found.getFollowedTypes().size() > 0);
		assertTrue(found.getFollowedTypes().get(0).getName().equals("addFollowed"));
	}

	@Test
	public void testRemoveFollowedItemType() throws Exception {
		Person person = new Person();
		personFacade.create(person);

		ItemType it = new ItemType();
		it.setName("test");
		personFacade.addFollowedItemType(person, it);

		personFacade.removeFollowedItemType(person, person.getFollowedTypes().get(0)); // it has not updated ID (strange, since person does)

		Person found = personFacade.find(person.getId());
		System.out.println(found.getFollowedTypes().size());
		assertTrue(found.getFollowedTypes().isEmpty());
	}
	
}