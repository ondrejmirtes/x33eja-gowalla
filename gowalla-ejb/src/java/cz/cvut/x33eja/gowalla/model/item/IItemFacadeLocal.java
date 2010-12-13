package cz.cvut.x33eja.gowalla.model.item;

import cz.cvut.x33eja.gowalla.model.person.Person;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasek Purchart
 */
@Local
public interface IItemFacadeLocal {

	// custom methods here

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	void create(Item item);

    void edit(Item item);

    void remove(Item item);

    Person find(Object id);

    List<Person> findAll();

    List<Person> findRange(int[] range);

    int count();

}
