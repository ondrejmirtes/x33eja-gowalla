package cz.cvut.x33eja.gowalla.model.item;

import cz.cvut.x33eja.gowalla.model.AbstractFacade;
import cz.cvut.x33eja.gowalla.model.person.Person;
import cz.cvut.x33eja.gowalla.model.spot.Spot;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.NoResultException;

/**
 *
 * @author Vašek Purchart
 */
@Stateless
public class ItemTypeFacade extends AbstractFacade<ItemType> implements IItemTypeFacadeLocal {

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public ItemTypeFacade() {
		super(ItemType.class);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	@Override
	public List<Item> findItems(List<ItemType> itemType, Spot spot) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<Item> findFollowedItems(Person person, List<Spot> spots) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ItemType findByName(String name) {
		Query q = em.createQuery("SELECT i FROM " + ItemType.class.getName() + " WHERE name = :name");
		q.setParameter("name", name);
		try {
			return (ItemType) q.getSingleResult();
		} catch (NoResultException exc) {
			return null;
		}
	}
	
}