package cz.cvut.x33eja.gowalla.model;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasek Purchart
 */
@Local
public interface IItemFacadeLocal {

	// custom methods here

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	void create(Item item);

    void edit(Item item);

    void remove(Item item);

    Person find(Object id);

    List<Item> findAll();

    List<Item> findRange(int[] range);

    int count();

}
