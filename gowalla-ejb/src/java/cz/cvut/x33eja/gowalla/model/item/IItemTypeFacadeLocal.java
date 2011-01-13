package cz.cvut.x33eja.gowalla.model.item;

import cz.cvut.x33eja.gowalla.model.person.Person;
import cz.cvut.x33eja.gowalla.model.spot.Spot;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasek Purchart
 */
@Local
public interface IItemTypeFacadeLocal {

	/**
	 * Find items of ItemType at specified spot
	 *
	 * @param itemType
	 * @param spot
	 * @return
	 */
    List<Item> findItems(List<ItemType> itemType, Spot spot);

	/**
	 * Find all items (of item types which user follows) at given list of spots
	 *
	 * @param person
	 * @param spots
	 * @return
	 */
	List<Item> findFollowedItems(Person person, List<Spot> spots);

	ItemType findByName(String name);

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	void create(ItemType itemType);

    void edit(ItemType itemType);

    void remove(ItemType itemType);

    ItemType find(Object id);

    List<ItemType> findAll();

    List<ItemType> findRange(int[] range);

    int count();
	
}
