package cz.cvut.x33eja.gowalla.model;

import cz.cvut.x33eja.gowalla.model.item.ItemType;
import cz.cvut.x33eja.gowalla.model.oauth.OAuth;
import cz.cvut.x33eja.gowalla.model.person.Person;
import cz.cvut.x33eja.gowalla.model.spot.Spot;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasek Purchart
 */
@Local
public interface IGowallaFacadeLocal {

	public String getOAuthRequestUrl();

	public void setAuthKey(String authKey);

	public OAuth getOAuth();

	public OAuth refreshOAuthToken(String refreshToken);

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

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
	 */
	public void updateNearestSpots(Location location);

	/**
	 * Download list of nearest items and add them to DB (if they are not alreday there)
	 *
	 * @param location
	 * @param count
	 */
	void updateNearestSpots(Location location, Integer count);

	/**
	 * List ItemTypes which has this user not in his collection
	 *
	 * @param person
	 * @return
	 */
	List<ItemType> getMissingItemTypes(Person person);

	/**
	 * List ItemTypes which has this user in his collection
	 *
	 * @param person
	 * @return
	 */
	List<ItemType> getCollectionItemTypes(Person person);

	/**
	 * Update list of ItemTypes
	 *
	 * @param person
	 */
	void updateItemTypes(Person person);

}
