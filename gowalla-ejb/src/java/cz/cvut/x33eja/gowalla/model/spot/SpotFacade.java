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
	public List<Spot> findNearestSpots(Location location, Integer distance) {
		return (List<Spot>) getEntityManager().createQuery("SELECT s FROM Spot s WHERE ((ABS(:lat - s.latitude))*(ABS(:lat - s.latitude)) +  (ABS(:lng - s.longitude))*(ABS(:lng - s.longitude))) < :dist")
				.setParameter("lat", location.getLatitude())
				.setParameter("lng", location.getLongitude())
				.setParameter("dist", Math.pow(distance, 2))
				.getResultList();
	}
    
}
