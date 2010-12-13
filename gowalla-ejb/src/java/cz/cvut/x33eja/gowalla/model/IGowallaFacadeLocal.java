package cz.cvut.x33eja.gowalla.model;

import cz.cvut.x33eja.gowalla.model.item.ItemType;
import cz.cvut.x33eja.gowalla.model.spot.Spot;
import cz.cvut.x33eja.gowalla.model.person.Person;
import javax.ejb.Local;

/**
 *
 * @author Vasek Purchart
 */
@Local
public interface IGowallaFacadeLocal {

	/**
	 * Update user's location based on last check-in
	 *
	 * @param person
	 */
    void updatePersonLocation(Person person);

	/**
	 * Update list of items present at given spot
	 *
	 * @param spot
	 */
	void updateSpotItems(Spot spot);

	/**
	 * Download list of nearest items and add them to DB (if they are not alreday there)
	 *
	 * @param location
	 * @param count
	 */
	void updateNearestSpots(Location location, Integer count);

	/**
	 * Checks in API if user has this ItemType in his collection
	 *
	 * @param person
	 * @param itemType
	 * @return
	 */
	boolean hasPersonThisItemType(Person person, ItemType itemType);

}