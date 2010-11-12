package cz.cvut.x33eja.gowalla.model;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasek Purchart
 */
@Local
public interface IGowallaFacadeLocal {

    void updatePersonLocation(Person person);

	void updateSpotItems(Spot spot);

	void updateNearestSpots(Location location, Integer count);

	boolean hasPersonThisItemType(Person person, ItemType itemType);

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
