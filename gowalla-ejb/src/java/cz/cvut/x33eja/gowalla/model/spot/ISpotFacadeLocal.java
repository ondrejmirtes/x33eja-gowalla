package cz.cvut.x33eja.gowalla.model.spot;

import cz.cvut.x33eja.gowalla.model.Location;
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

    Spot find(Object id);

    List<Spot> findAll();

    List<Spot> findRange(int[] range);

    int count();

}
