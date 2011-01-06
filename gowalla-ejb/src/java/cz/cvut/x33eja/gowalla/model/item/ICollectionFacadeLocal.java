package cz.cvut.x33eja.gowalla.model.item;

import cz.cvut.x33eja.gowalla.model.person.Person;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasek Purchart
 */
@Local
public interface ICollectionFacadeLocal {

	/**
	 * Find which items (item types) are missing from user's collection
	 *
	 * @param person
	 * @return
	 */
    List<ItemType> findMissingItemTypes(Person person);

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	void create(Collection collection);

    void edit(Collection collection);

    void remove(Collection collection);

    Collection find(Object id);

    List<Collection> findAll();

    List<Collection> findRange(int[] range);

    int count();

}
