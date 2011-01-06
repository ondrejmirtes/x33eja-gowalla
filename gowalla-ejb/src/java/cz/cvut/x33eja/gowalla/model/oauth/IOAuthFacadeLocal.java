package cz.cvut.x33eja.gowalla.model.oauth;

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

    OAuth find(Object id);

    List<OAuth> findAll();

    List<OAuth> findRange(int[] range);

    int count();

}
