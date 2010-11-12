package cz.cvut.x33eja.gowalla.model;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasek Purchart
 */
@Local
public interface ISpotFacadeLocal {

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
