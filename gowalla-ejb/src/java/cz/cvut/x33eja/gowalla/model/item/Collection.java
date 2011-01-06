package cz.cvut.x33eja.gowalla.model.item;

import cz.cvut.x33eja.gowalla.model.SimpleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * User's collection of items.
 * Items removing is disabled intentionally, because Gowalla does not allow it.
 * 
 * @author Ondřej Mirtes
 */
@Entity
public class Collection extends SimpleEntity {

	@OneToMany(cascade={CascadeType.PERSIST}, mappedBy="collection")
	private List<Item> items = new ArrayList<Item>();

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		items.add(item);
	}
}
