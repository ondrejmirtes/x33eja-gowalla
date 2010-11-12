package cz.cvut.x33eja.gowalla.model;

/**
 *
 * @author Vasek Purchart
 */
public class Location {

	private float latitude;

	private float longitude;

	Location(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

}
