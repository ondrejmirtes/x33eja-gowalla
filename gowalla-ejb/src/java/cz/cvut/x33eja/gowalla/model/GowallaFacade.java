package cz.cvut.x33eja.gowalla.model;

import com.ginsberg.gowalla.dto.GeoPoint;
import com.ginsberg.gowalla.exception.GowallaException;
import com.ginsberg.gowalla.exception.GowallaRequestException;
import com.ginsberg.gowalla.Gowalla;
import com.ginsberg.gowalla.ItemContext;
import com.ginsberg.gowalla.dto.UserEvent;

import cz.cvut.x33eja.gowalla.model.item.*;
import cz.cvut.x33eja.gowalla.model.oauth.OAuth;
import cz.cvut.x33eja.gowalla.model.person.*;
import cz.cvut.x33eja.gowalla.model.spot.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author Vašek Purchart
 */
@Stateless
public class GowallaFacade implements IGowallaFacadeLocal {

	Gowalla gowalla;

	com.ginsberg.gowalla.oauth.OAuth oAuth;

	String authKey;

	com.ginsberg.gowalla.dto.oauth.OAuthAccessTokenResponse accessToken;

	@Inject
	IPersonFacadeLocal personFacade;

	@Inject
	IItemFacadeLocal itemFacade;

	@Inject
	IItemTypeFacadeLocal itemTypeFacade;

	@Inject
	ISpotFacadeLocal spotFacade;

	public static final String API_KEY = "2c25a80e43114d0b8e290c0c98d74756";
	public static final String SECRET_KEY = "7e57ed5b2dad4aa6b869e08044177e43";
	public static final String CALLBACK_URL = "http://localhost:8080/gowalla-war/oauth-redirect";

	private static final int SPOT_RADIUS = 50;

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public String getAuthKey() {
		return authKey;
	}

	@Override
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public IItemFacadeLocal getItemFacade() {
		return itemFacade;
	}

	public void setItemFacade(ItemFacade itemFacade) {
		this.itemFacade = itemFacade;
	}

	public IItemTypeFacadeLocal getItemTypeFacade() {
		return itemTypeFacade;
	}

	public void setItemTypeFacade(ItemTypeFacade itemTypeFacade) {
		this.itemTypeFacade = itemTypeFacade;
	}

	public IPersonFacadeLocal getPersonFacade() {
		return personFacade;
	}

	public void setPersonFacade(PersonFacade personFacade) {
		this.personFacade = personFacade;
	}

	public ISpotFacadeLocal getSpotFacade() {
		return spotFacade;
	}

	public void setSpotFacade(SpotFacade spotFacade) {
		this.spotFacade = spotFacade;
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	protected void initGowalla() {
		com.ginsberg.gowalla.dto.oauth.OAuthAccessTokenResponse token = getAccessToken();
		gowalla = new Gowalla("UnitTests", API_KEY, new com.ginsberg.gowalla.auth.OAuth2Authentication(token.getAccessToken()));
		gowalla.setRateLimiter(new com.ginsberg.gowalla.rate.RequestsOverTime(5));
	}

	protected Gowalla getGowalla() {
		if (gowalla == null) {
			initGowalla();
		}

		return gowalla;
	}

	protected com.ginsberg.gowalla.oauth.OAuth getGowallaOAuth() {
		if (oAuth == null) {
			oAuth = new com.ginsberg.gowalla.oauth.OAuth(API_KEY, SECRET_KEY, CALLBACK_URL);
		}

		return oAuth;
	}

	protected com.ginsberg.gowalla.dto.oauth.OAuthAccessTokenResponse getAccessToken() {
		if (accessToken == null) {
			accessToken = requestAccessToken();
		}

		return accessToken;
	}

	protected com.ginsberg.gowalla.dto.oauth.OAuthAccessTokenResponse requestAccessToken() {
		if (authKey == null) {
			throw new IllegalStateException("You must set the Auth Key first");
		}
		com.ginsberg.gowalla.oauth.OAuth auth = getGowallaOAuth();
		try {
			return auth.requestAccessToken(com.ginsberg.gowalla.oauth.OAuth.Scope.READ_WRITE, authKey);
		} catch (GowallaRequestException ex) {
			Logger.getLogger(GowallaFacade.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	protected OAuth processOAuthToken(com.ginsberg.gowalla.dto.oauth.OAuthAccessTokenResponse token) {
		OAuth auth = new OAuth();
		auth.setCode(authKey);
		auth.setAccessToken(token.getAccessToken());
		auth.setRefreshToken(token.getRefreshToken());
		Calendar calendar = Calendar.getInstance();
		auth.setExpiresAt((calendar.getTimeInMillis()/1000) + token.getExpiresIn());

		return auth;
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	@Override
	public String getOAuthRequestUrl() {
		com.ginsberg.gowalla.oauth.OAuth auth = getGowallaOAuth();
		return auth.createAuthorizationRequest();
	}

	@Override
	public OAuth getOAuth() {
		com.ginsberg.gowalla.dto.oauth.OAuthAccessTokenResponse token = getAccessToken();
		OAuth auth = processOAuthToken(token);
		Person person = getPerson(token.getUsername());
		auth.setPerson(person);

		return auth;
	}

	@Override
	public OAuth refreshOAuthToken(String refreshToken) {
		com.ginsberg.gowalla.oauth.OAuth auth = getGowallaOAuth();
		try {
			accessToken = auth.refreshAccessToken(refreshToken);
			return processOAuthToken(accessToken);
		} catch (GowallaRequestException ex) {
			Logger.getLogger(GowallaFacade.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public Person getPerson(String login) {
		Person person = new Person();
		try {
			com.ginsberg.gowalla.dto.User user = getGowalla().getUser(login);
			person.setId(new Long(user.getId()));
			person.setNick(login);
			person.setName(user.getName());
		} catch (GowallaException ex) {
			Logger.getLogger(GowallaFacade.class.getName()).log(Level.SEVERE, null, ex);
		}

		return person;
	}

	@Override
	public void updatePersonLocation(Person person) {
		try {
			UserEvent lastCheckin = getGowalla().getUser().getLastCheckin();
			if (lastCheckin != null) {
				GeoPoint point = getGowalla().getSpot(lastCheckin.getSpot().getId()).getGeoLocation();
				Location location = new Location(point.getLatitude().doubleValue(), point.getLongitude().doubleValue());
				person.setLocation(location);
				personFacade.edit(person);
			}
		} catch (GowallaException ex) {
			Logger.getLogger(GowallaFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void updateSpotItems(Spot spot) {
		try {
			List<com.ginsberg.gowalla.dto.Item> newItemsList = getGowalla().getItemsAtSpot(spot.getId().intValue());
			HashMap<Integer, com.ginsberg.gowalla.dto.Item> newItems = new HashMap<Integer, com.ginsberg.gowalla.dto.Item>();
			for (com.ginsberg.gowalla.dto.Item iItem : newItemsList) {
				newItems.put(iItem.getId(), iItem);
			}

			// TODO causes concurrent access exception
			/*for (Item iItem : spot.getItems()) {
				int id = iItem.getId().intValue();
				if (!newItems.containsKey(id)) {
					spot.removeItem(iItem); // remove items which are no longer present
				}
				newItems.remove(id);
			}*/

			for (com.ginsberg.gowalla.dto.Item iItem : newItemsList) { // add new items
				Item newItem = getItem(iItem);
				spot.addItem(newItem);
			}

			spotFacade.edit(spot);
		} catch (GowallaException ex) {
			Logger.getLogger(GowallaFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void updateNearestSpots(Location location) {
		updateNearestSpots(location, SPOT_RADIUS);
	}

	@Override
	public void updateNearestSpots(Location location, Integer radius) {
		try {
			GeoPoint point = new GeoPoint(location.getLatitude(), location.getLongitude());
			List<com.ginsberg.gowalla.dto.SimpleSpot> list = getGowalla().findSpotsNear(point, radius);
			for (com.ginsberg.gowalla.dto.SimpleSpot iSpot : list) {
				Spot spot = getSpot(iSpot);
				updateSpotItems(spot);
			}
		} catch (GowallaException ex) {
			Logger.getLogger(GowallaFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public List<ItemType> getMissingItemTypes(Person person) {
		List<ItemType> results = new ArrayList<ItemType>();
		try {
			results = processItems(getGowalla().getItemsForUser((int)(long) person.getId(), ItemContext.MISSING));
		} catch (GowallaException ex) {
			Logger.getLogger(GowallaFacade.class.getName()).log(Level.SEVERE, null, ex);
		}

		return results;
	}

	@Override
	public List<ItemType> getCollectionItemTypes(Person person) {
		List<ItemType> results = new ArrayList<ItemType>();
		try {
			results = processItems(getGowalla().getItemsForUser((int)(long) person.getId(), ItemContext.VAULT));
		} catch (GowallaException ex) {
			Logger.getLogger(GowallaFacade.class.getName()).log(Level.SEVERE, null, ex);
		}

		return results;
	}

	@Override
	public void updateItemTypes(Person person) {
		getCollectionItemTypes(person);
		getMissingItemTypes(person);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	private Item getItem(com.ginsberg.gowalla.dto.Item gowallaItem) {
		Long id = new Long(gowallaItem.getId());
		Item item = itemFacade.find(id);
		if (item == null) {
			item = new Item();
			item.setId(id);
			item.setItemType(getItemType(gowallaItem));
		}

		return item;
	}

	private ItemType getItemType(com.ginsberg.gowalla.dto.Item gowallaItem) {
		ItemType itemType = itemTypeFacade.findByName(gowallaItem.getName());
		if (itemType == null) {
			itemType = new ItemType();
			itemType.setName(gowallaItem.getName());
			itemType.setImage(new Image(gowallaItem.getImageUrl()));
			itemTypeFacade.create(itemType);
		}

		return itemType;
	}

	private List<ItemType> processItems(List<com.ginsberg.gowalla.dto.Item> gowallaItems) {
		List<ItemType> results = new ArrayList<ItemType>();
		for (com.ginsberg.gowalla.dto.Item iItem : gowallaItems) {
			ItemType itemType = getItemType(iItem);
			results.add(itemType);
		}
		return results;
	}

	private Spot getSpot(com.ginsberg.gowalla.dto.SimpleSpot gowallaSpot) {
		Long id = new Long(gowallaSpot.getId());
		Spot spot = spotFacade.find(id);
		if (spot == null) {
			spot = new Spot();
			spot.setId(id);
			spot.setName(gowallaSpot.getName());
			Location location = new Location(Double.valueOf(gowallaSpot.getLat()), Double.valueOf(gowallaSpot.getLng()));
			spot.setLocation(location);
			spot.setImage(new Image(gowallaSpot.getImageUrl()));
			spotFacade.create(spot);
		}

		return spot;
	}
	
}
