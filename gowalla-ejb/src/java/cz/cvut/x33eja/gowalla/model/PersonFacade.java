package cz.cvut.x33eja.gowalla.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ondrej
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person> implements PersonFacadeLocal {
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
