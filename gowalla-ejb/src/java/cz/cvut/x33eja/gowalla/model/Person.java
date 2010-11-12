package cz.cvut.x33eja.gowalla.model;

import com.sun.istack.internal.Nullable;
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

	private float latitude;

	private float longitude;

	@OneToOne
	@Nullable
	private OAuth oAuth;

	@OneToOne
    private Collection collection;

    @OneToMany
    private List<Item> items;

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

	public OAuth getOAuth() {
		return oAuth;
	}

	public void setOAuth(OAuth $oAuth) {
		this.oAuth = $oAuth;
	}

	public void setLocation(Location location) {
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
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

}
