package cz.cvut.x33eja.gowalla.model;

/**
 * Specific location on the map - contains latitude and longitude coordinates.
 *
 * @author Vasek Purchart
 * @author Ondřej Mirtes
 */
public class Location {

	private double latitude;
	private double longitude;

	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Location)) {
			return false;
		}

		return (latitude == ((Location) object).getLatitude() && longitude == ((Location) object).getLongitude());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
		hash = 37 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
		return hash;
	}
}
