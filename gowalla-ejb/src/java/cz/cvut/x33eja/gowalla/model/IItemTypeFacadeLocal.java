package cz.cvut.x33eja.gowalla.model;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasek Purchart
 */
@Local
public interface IItemTypeFacadeLocal {

    List<Item> findItems(List<ItemType> itemType, Spot spot);

	List<Item> findFollowedItems(Person person, List<Spot> spots);

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	void create(ItemType itemType);

    void edit(ItemType itemType);

    void remove(ItemType itemType);

    Person find(Object id);

    List<Person> findAll();

    List<Person> findRange(int[] range);

    int count();
	
}
