package cz.cvut.x33eja.gowalla.model;

import javax.persistence.Entity;

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

}
