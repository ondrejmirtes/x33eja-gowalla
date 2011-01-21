package cz.cvut.x33eja.gowalla.model.oauth;

import cz.cvut.x33eja.gowalla.model.SimpleEntity;
import cz.cvut.x33eja.gowalla.model.person.Person;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Three-way authorization credentials for accessing the Gowalla API
 * via the user identity without his username and passord.
 *
 * @author Vasek Purchart
 */
@Entity
public class OAuth extends SimpleEntity {

	private String code;

	private String accessToken;

	private String refreshToken;

	private long expiresAt;

	
	@OneToOne(cascade={CascadeType.PERSIST}, mappedBy = "oAuth")
	private Person person;

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String token) {
		this.accessToken = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String token) {
		this.refreshToken = token;
	}

	public long getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(long expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		if (this.person != person) {
			this.person = person;
			person.setOAuth(this);
		}
	}



}
