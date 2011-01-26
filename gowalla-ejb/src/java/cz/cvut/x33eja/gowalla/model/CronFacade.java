/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model;

import cz.cvut.x33eja.gowalla.model.person.IPersonFacadeLocal;
import cz.cvut.x33eja.gowalla.model.person.Person;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Ondřej Mirtes
 */
@Stateless
public class CronFacade implements CronFacadeLocal {

	@Inject
	private IPersonFacadeLocal personFacade;

	@Inject
	private IGowallaFacadeLocal gowallaFacade;

	/*@Schedule(second="0", minute="0/2", hour="0/1")*/
    public void updatePersons() {
		for (Person p : personFacade.findAll()) {
			gowallaFacade.updatePersonLocation(p);
			gowallaFacade.updateNearestSpots(p.getLocation());
		}
	}
 
}
