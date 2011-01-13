package cz.cvut.x33eja.gowalla.model;

import com.ginsberg.gowalla.exception.GowallaException;
import com.ginsberg.gowalla.exception.GowallaRequestException;
import cz.cvut.x33eja.gowalla.model.item.Item;
import cz.cvut.x33eja.gowalla.model.item.ItemType;
import cz.cvut.x33eja.gowalla.model.person.Person;
import cz.cvut.x33eja.gowalla.model.person.PersonFacade;
import cz.cvut.x33eja.gowalla.model.spot.Spot;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import com.ginsberg.gowalla.Gowalla;
import com.ginsberg.gowalla.dto.GeoPoint;
import cz.cvut.x33eja.gowalla.model.item.ItemTypeFacade;
import cz.cvut.x33eja.gowalla.model.spot.SpotFacade;
import java.util.List;
import java.util.HashMap;
import javax.inject.Inject;
import javax.persistence.EntityManager;


/**
 *
 * @author Vašek Purchart
 */
@Stateful
public class GowallaFacade implements IGowallaFacadeLocal {

	Gowalla gowalla;

	@Inject
	PersonFacade personFacade;

	@Inject
	ItemTypeFacade itemTypeFacade;

	@Inject
	SpotFacade spotFacade;

	public static final String UNIT_TEST_API_KEY = "2c25a80e43114d0b8e290c0c98d74756";
	public static final String SECRET_KEY = "7e57ed5b2dad4aa6b869e08044177e43";
	public static final String AUTH_KEY = "fd47346444c9563bffea9af27a1b92e0";

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public ItemTypeFacade getItemTypeFacade() {
		return itemTypeFacade;
	}

	public void setItemTypeFacade(ItemTypeFacade itemTypeFacade) {
		this.itemTypeFacade = itemTypeFacade;
	}

	public PersonFacade getPersonFacade() {
		return personFacade;
	}

	public void setPersonFacade(PersonFacade personFacade) {
		this.personFacade = personFacade;
	}

	public SpotFacade getSpotFacade() {
		return spotFacade;
	}

	public void setSpotFacade(SpotFacade spotFacade) {
		this.spotFacade = spotFacade;
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public GowallaFacade() {
		initGowalla();
	}

	private void initGowalla() {
		try {
			com.ginsberg.gowalla.oauth.OAuth oauth = new com.ginsberg.gowalla.oauth.OAuth(UNIT_TEST_API_KEY, SECRET_KEY, "http://localhost/");
			com.ginsberg.gowalla.dto.oauth.OAuthAccessTokenResponse token = oauth.requestAccessToken(com.ginsberg.gowalla.oauth.OAuth.Scope.READ_WRITE, AUTH_KEY);
			gowalla = new Gowalla("UnitTests", UNIT_TEST_API_KEY, new com.ginsberg.gowalla.auth.OAuth2Authentication(token.getAccessToken()));
			gowalla.setRateLimiter(new com.ginsberg.gowalla.rate.RequestsOverTime(5));
		} catch (GowallaRequestException ex) {
			Logger.getLogger(GowallaFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	@Override
	public void updatePersonLocation(Person person) {
		try {
			GeoPoint point = gowalla.getSpot(gowalla.getUser().getLastCheckin().getSpot().getId()).getGeoLocation();
			Location location = new Location(point.getLatitude().doubleValue(), point.getLongitude().doubleValue());
			person.setLocation(location);
			personFacade.edit(person);
		} catch (GowallaException ex) {
			Logger.getLogger(GowallaFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void updateSpotItems(Spot spot) {
		try {
			List<com.ginsberg.gowalla.dto.Item> newItemsList = gowalla.getItemsAtSpot(spot.getId().intValue());
			HashMap<Integer, com.ginsberg.gowalla.dto.Item> newItems = new HashMap<Integer, com.ginsberg.gowalla.dto.Item>();
			for (com.ginsberg.gowalla.dto.Item iItem : newItemsList) {
				newItems.put(iItem.getId(), iItem);
			}

			for (Item iItem : spot.getItems()) {
				int id = iItem.getId().intValue();
				if (!newItems.containsKey(id)) {
					spot.removeItem(iItem); // remove items which are no longer present
				}

				newItemsList.remove(id);
			}

			for (com.ginsberg.gowalla.dto.Item iItem : newItemsList) { // add new items
				Item newItem = new Item();
				newItem.setId(Long.valueOf(iItem.getId()));
				newItem.setItemType(getItemType(iItem));
				spot.addItem(newItem);
			}

			spotFacade.edit(spot);
		} catch (GowallaException ex) {
			Logger.getLogger(GowallaFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private ItemType getItemType(com.ginsberg.gowalla.dto.Item item) {
		ItemType itemType = itemTypeFacade.findByName(item.getName());
		if (itemType == null) {
			itemType = new ItemType();
			itemType.setName(item.getName());
			itemType.setImage(new Image(item.getImageUrl()));
			itemTypeFacade.create(itemType);
		}

		return itemType;
	}

	@Override
	public void updateNearestSpots(Location location, Integer count) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean hasPersonThisItemType(Person person, ItemType itemType) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
