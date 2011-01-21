package cz.cvut.x33eja.gowalla.model.person;

import cz.cvut.x33eja.gowalla.model.item.Collection;
import cz.cvut.x33eja.gowalla.model.item.Item;
import cz.cvut.x33eja.gowalla.model.item.ItemType;
import cz.cvut.x33eja.gowalla.model.Location;
import cz.cvut.x33eja.gowalla.model.oauth.OAuth;
import cz.cvut.x33eja.gowalla.model.SimpleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * User of the application.
 *
 * @author Vasek Purchart
 * @author Ondřej Mirtes
 */
@Entity
public class Person extends SimpleEntity {

	private String nick;

	private String name;

	private double latitude;

	private double longitude;

	@OneToOne(cascade={CascadeType.PERSIST})
	private OAuth oAuth;

	@OneToOne(cascade={CascadeType.PERSIST})
	private Collection collection;

	/**
	 * Items "in pack"
	 */
	@OneToMany(cascade={CascadeType.PERSIST}, mappedBy="person")
	private List<Item> items = new ArrayList<Item>();

	/**
	 * Items in vault
	 */
	@ManyToMany(cascade={CascadeType.PERSIST})
	private List<ItemType> followedTypes = new ArrayList<ItemType>();

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Location getLocation() {
		return new Location(this.latitude, this.longitude);
	}

	public void setLocation(Location location) {
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
	}

	public OAuth getOAuth() {
		return oAuth;
	}

	public void setOAuth(OAuth oAuth) {
		if (this.oAuth !=  oAuth) {
			this.oAuth = oAuth;
			oAuth.setPerson(this);
		}
	}

	private Collection getCollection() {
		if (collection == null) {
			collection = new Collection();
		}
		
		return collection;
	}

	public void addCollectionItem(Item item) {
		getCollection().addItem(item);
	}

	public List<Item> getCollectionItems() {
		return getCollection().getItems();
	}

	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	public List<ItemType> getFollowedTypes() {
		return followedTypes;
	}

	public void addFollowedType(ItemType type) {
		followedTypes.add(type);
	}

	public void removeFollowedType(ItemType type) {
		followedTypes.remove(type);
	}
}
