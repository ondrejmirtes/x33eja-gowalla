package cz.cvut.x33eja.gowalla.model;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Vasek Purchart
 */
@Entity
public class Person extends SimpleEntity {

    private String nick;

    private String name;

	private double latitude;

	private double longitude;

	@OneToOne
	private OAuth oAuth;

	@OneToOne
    private Collection collection;

    @OneToMany
    private List<Item> items = new ArrayList<Item>();

    @ManyToMany
    private List<ItemType> followedTypes;

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
		this.oAuth = oAuth;
	}

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
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
