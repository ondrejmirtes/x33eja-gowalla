package cz.cvut.x33eja.gowalla.model;

/**
 * Class representing URL to an image.
 * 
 * @author Ondřej Mirtes
 */
public class Image {

	private String url;

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public Image(String url) {
		this.url = url;
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public String getUrl() {
		return url;
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Image)) {
			return false;
		}

		return url.equals(((Image) object).getUrl());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + (this.url != null ? this.url.hashCode() : 0);
		return hash;
	}
}
