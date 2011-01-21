package cz.cvut.x33eja.gowalla.model.person;

import cz.cvut.x33eja.gowalla.model.item.ItemType;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ondřej Mirtes
 */
@Local
public interface IPersonFacadeLocal {

	void addFollowedItemType(Person person, ItemType itemType);

	void removeFollowedItemType(Person person, ItemType itemType);

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	void create(Person person);

	void edit(Person person);

	void remove(Person person);

	Person find(Object id);

	List<Person> findAll();

	List<Person> findRange(int[] range);

	int count();
	
}
