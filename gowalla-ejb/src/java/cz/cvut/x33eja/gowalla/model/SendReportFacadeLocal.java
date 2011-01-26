/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model;

import cz.cvut.x33eja.gowalla.model.person.Person;
import javax.ejb.Local;

/**
 *
 * @author Ondřej Mirtes
 */
@Local
public interface SendReportFacadeLocal {

	public void sendReport(Person person);

	public void flushReports();
    
}
