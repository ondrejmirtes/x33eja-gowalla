package cz.cvut.x33eja.gowalla.model;

import javax.persistence.Entity;

/**
 * Three-way authorization credentials for accessing the Gowalla API
 * via the user identity without his username and passord.
 *
 * @author Vasek Purchart
 */
@Entity
public class OAuth extends SimpleEntity {
	
	private String token;

	private String tokenSecret;

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

}
