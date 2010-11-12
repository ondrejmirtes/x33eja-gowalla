package cz.cvut.x33eja.gowalla.model;

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

}
