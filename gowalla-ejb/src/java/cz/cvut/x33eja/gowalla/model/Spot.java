package cz.cvut.x33eja.gowalla.model;

import java.util.List;
import javax.persistence.OneToMany;

/**
 *
 * @author Vasek Purchart
 */
public class Spot extends SimpleEntity {

	private String name;

	private float latitude;

	private float longitude;

	private String image;

	@OneToMany
    private List<Item> items;

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return new Location(this.latitude, this.longitude);
	}

	public void setLocation(Location location) {
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
	}

	public void setImage(Image image) {
        this.image = image.getUrl();
    }

	public Image getImage() {
		return new Image(this.image);
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void removeFollowedType(Item type) {
		items.remove(type);
	}

}
