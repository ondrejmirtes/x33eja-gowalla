package cz.cvut.x33eja.gowalla.model;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ondřej Mirtes
 */
@Local
public interface IPersonFacadeLocal {

	// custom methods here

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	void create(Person person);

	void edit(Person person);

	void remove(Person person);

	Person find(Object id);

	List<Person> findAll();

	List<Person> findRange(int[] range);

	int count();
	
}
