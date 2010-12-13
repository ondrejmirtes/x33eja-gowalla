package cz.cvut.x33eja.gowalla.model.oauth;

import cz.cvut.x33eja.gowalla.model.person.Person;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasek Purchart
 */
@Local
public interface IOAuthFacadeLocal {

	// custom methods here

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	void create(OAuth oAuth);

    void edit(OAuth oAuth);

    void remove(OAuth oAuth);

    Person find(Object id);

    List<Person> findAll();

    List<Person> findRange(int[] range);

    int count();

}
