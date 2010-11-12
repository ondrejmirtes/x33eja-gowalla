/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author Ondřej Mirtes
 */
abstract public class EntityManagerTestCase {

	private static final String TEST_PERSISTENCE_UNIT = "gowalla-testPU";

	private EntityManagerFactory emf;
	protected EntityManager em;
	protected EntityTransaction tr;

	@Before
	public void initEntityManager() {
		emf = Persistence.createEntityManagerFactory(TEST_PERSISTENCE_UNIT);
		em = emf.createEntityManager();
		tr = em.getTransaction();
		tr.begin();
	}

	@After
	public void closeEntityManager() {
		tr.commit();
		em.close();
		emf.close();
	}

}
