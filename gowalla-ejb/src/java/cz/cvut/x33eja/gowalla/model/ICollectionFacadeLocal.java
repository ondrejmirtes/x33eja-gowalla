package cz.cvut.x33eja.gowalla.model;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasek Purchart
 */
@Local
public interface ICollectionFacadeLocal {

    List<ItemType> findMissingItemTypes(Person person);

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	void create(Collection collection);

    void edit(Collection collection);

    void remove(Collection collection);

    Person find(Object id);

    List<Person> findAll();

    List<Person> findRange(int[] range);

    int count();

}
