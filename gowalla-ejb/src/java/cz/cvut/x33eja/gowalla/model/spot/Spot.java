package cz.cvut.x33eja.gowalla.model.spot;

import cz.cvut.x33eja.gowalla.model.Image;
import cz.cvut.x33eja.gowalla.model.item.Item;
import cz.cvut.x33eja.gowalla.model.Location;
import cz.cvut.x33eja.gowalla.model.SimpleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Check-in location containing items.
 * @author Vasek Purchart
 */
@Entity
public class Spot extends SimpleEntity {

	private String name;
	private double latitude;
	private double longitude;
	private String image;
	@OneToMany(cascade={CascadeType.PERSIST}, mappedBy="spot")
	private List<Item> items = new ArrayList<Item>();

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

	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		items.add(item);
		item.setSpot(this);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}
}
