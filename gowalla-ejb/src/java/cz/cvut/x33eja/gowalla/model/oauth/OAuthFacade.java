package cz.cvut.x33eja.gowalla.model.oauth;

import cz.cvut.x33eja.gowalla.model.AbstractFacade;
import cz.cvut.x33eja.gowalla.model.IGowallaFacadeLocal;
import cz.cvut.x33eja.gowalla.model.person.IPersonFacadeLocal;
import cz.cvut.x33eja.gowalla.model.person.Person;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.NoResultException;

/**
 *
 * @author Ondřej Mirtes
 */
@Stateless
public class OAuthFacade extends AbstractFacade<OAuth> implements IOAuthFacadeLocal {

	@Inject
	IGowallaFacadeLocal gowallaFacade;

	@Inject
	IPersonFacadeLocal personFacade;

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public OAuthFacade() {
		super(OAuth.class);
	}

	public IGowallaFacadeLocal getGowallaFacade() {
		return gowallaFacade;
	}

	public void setGowallaFacade(IGowallaFacadeLocal gowallaFacade) {
		this.gowallaFacade = gowallaFacade;
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	@Override
	public String getOAuthRequestUrl() {
		return gowallaFacade.getOAuthRequestUrl();
	}

	@Override
	public Person getPerson(String code) {
		gowallaFacade.setAuthKey(code);
		OAuth auth = gowallaFacade.getOAuth();
		Person person = auth.getPerson();
		Person foundPerson = personFacade.find(person.getId());
		if (foundPerson != null) {
			return foundPerson;
		}
		create(auth);
		return person;
	}

	@Override
	public void refreshToken(String code) {
		OAuth auth = findByCode(code);
		if (auth == null) {
			throw new IllegalStateException("You cannot refresh non existent user.");
		}
		gowallaFacade.setAuthKey(code);
		OAuth newAuth = gowallaFacade.refreshOAuthToken(auth.getRefreshToken());
		auth.setAccessToken(newAuth.getAccessToken());
		auth.setRefreshToken(newAuth.getRefreshToken());
		auth.setExpiresAt(newAuth.getExpiresAt());
		edit(auth);
	}

	public OAuth findByCode(String code) {
		Query q = em.createQuery("SELECT o FROM OAuth o WHERE o.code = :code");
		q.setParameter("code", code);
		try {
			return (OAuth) q.getSingleResult();
		} catch (NoResultException exc) {
			return null;
		}
	}
	
}
