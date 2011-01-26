package cz.cvut.x33eja.gowalla.model.item;

import cz.cvut.x33eja.gowalla.model.AbstractFacade;
import cz.cvut.x33eja.gowalla.model.person.Person;
import cz.cvut.x33eja.gowalla.model.spot.Spot;
import java.util.ArrayList;
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
		List<ItemType> types = person.getFollowedTypes();
		List<Item> items = new ArrayList<Item>();

		for (Spot s : spots) {
			for (Item i : s.getItems()) {
				if (types.contains(i.getItemType())) {
					items.add(i);
				}
			}
		}

		return items;
	}

	@Override
	public ItemType findByName(String name) {
		Query q = em.createQuery("SELECT i FROM ItemType i WHERE i.name = :name");
		q.setParameter("name", name);
		try {
			return (ItemType) q.getSingleResult();
		} catch (NoResultException exc) {
			return null;
		}
	}

	@Override
	public List<ItemType> findMissingItemTypes(Person person) {
		List<ItemType> list = findAll();
		for (Item item : person.getCollectionItems()) {
			list.remove(item.getItemType());
		}

		return list;
	}

	@Override
	public List<ItemType> findNotFollowedItemTypes(Person person) {
		List<ItemType> list = findAll();
		for (ItemType item : person.getFollowedTypes()) {
			list.remove(item);
		}

		return list;
	}
	
}
