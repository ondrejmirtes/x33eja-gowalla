package cz.cvut.x33eja.gowalla.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ondřej Mirtes
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person> implements IPersonFacadeLocal {

	@PersistenceContext(unitName = "gowalla-ejbPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PersonFacade() {
		super(Person.class);
	}
}
