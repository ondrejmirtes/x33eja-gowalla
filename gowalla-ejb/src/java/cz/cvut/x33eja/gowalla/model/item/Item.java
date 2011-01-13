package cz.cvut.x33eja.gowalla.model.item;

import cz.cvut.x33eja.gowalla.model.SimpleEntity;
import cz.cvut.x33eja.gowalla.model.spot.Spot;
import cz.cvut.x33eja.gowalla.model.person.Person;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ondřej Mirtes
 */
@Entity
public class Item extends SimpleEntity {

	private Integer number;
	@ManyToOne(cascade={CascadeType.PERSIST})
	private Person person;
	@ManyToOne(cascade={CascadeType.PERSIST})
	private Collection collection;
	@ManyToOne(cascade={CascadeType.PERSIST})
	private Spot spot;
	@ManyToOne(cascade={CascadeType.PERSIST})
	private ItemType itemType;

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		resetPlacement();
		this.person = person;
	}

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		resetPlacement();
		this.collection = collection;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		resetPlacement();
		this.spot = spot;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	private void resetPlacement() {
		this.spot = null;
		this.collection = null;
		this.person = null;
	}
}
