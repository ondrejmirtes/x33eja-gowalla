/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model.oauth;

import cz.cvut.x33eja.gowalla.model.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vasek
 */
public class OAuthFacadeTest extends EntityManagerTestCase {

	private OAuthFacade oAuthFacade;

	private GowallaFacade gowallaFacade;

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	@Before
	public void startUp() {
		gowallaFacade = new GowallaFacade();

		oAuthFacade = new OAuthFacade();
		oAuthFacade.setEntityManager(em);
		oAuthFacade.setGowallaFacade(gowallaFacade);
	}

	@Test
	public void testgetOAuthRequestUrl() throws Exception {
		System.out.println(oAuthFacade.getOAuthRequestUrl());
	}

	@Test
	public void testGetPerson() throws Exception {
		oAuthFacade.getPerson(GowallaFacadeTest.AUTH_KEY);
	}

	@Test
	public void testRefreshToken() throws Exception {
		fail("@todo je potřeba předělat, asi by nemělo používat code, ale nějak podle ID, ale stejně vrací 400");
		oAuthFacade.refreshToken(GowallaFacadeTest.AUTH_KEY);
	}

}