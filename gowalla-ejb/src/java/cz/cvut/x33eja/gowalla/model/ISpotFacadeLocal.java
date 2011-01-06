package cz.cvut.x33eja.gowalla.model;

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

    List<Spot> findAll();

    List<Spot> findRange(int[] range);

    int count();

}
