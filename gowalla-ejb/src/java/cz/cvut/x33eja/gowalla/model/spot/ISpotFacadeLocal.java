package cz.cvut.x33eja.gowalla.model.spot;

import cz.cvut.x33eja.gowalla.model.Location;
import cz.cvut.x33eja.gowalla.model.person.Person;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasek Purchart
 */
@Local
public interface ISpotFacadeLocal {

	/**
	 * Find nearest spots to given location (limited by maximum count)
	 *
	 * @param location
	 * @param count
	 * @return
	 */
    List<Spot> findNearestSpots(Location location, Integer count);

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	void create(Spot spot);

    void edit(Spot spot);

    void remove(Spot spot);

    Person find(Object id);

    List<Person> findAll();

    List<Person> findRange(int[] range);

    int count();

}
