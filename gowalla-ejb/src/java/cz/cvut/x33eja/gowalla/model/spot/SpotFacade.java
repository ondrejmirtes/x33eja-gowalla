package cz.cvut.x33eja.gowalla.model.spot;

import cz.cvut.x33eja.gowalla.model.AbstractFacade;
import cz.cvut.x33eja.gowalla.model.Location;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Vašek Purchart
 */
@Stateless
public class SpotFacade extends AbstractFacade<Spot> implements ISpotFacadeLocal {

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public SpotFacade() {
		super(Spot.class);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	@Override
	public List<Spot> findNearestSpots(Location location, Integer count) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
    
}