/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import cz.cvut.x33eja.gowalla.model.IGowallaFacadeLocal;
import cz.cvut.x33eja.gowalla.model.item.IItemTypeFacadeLocal;
import cz.cvut.x33eja.gowalla.model.item.ItemType;
import cz.cvut.x33eja.gowalla.model.person.IPersonFacadeLocal;
import cz.cvut.x33eja.gowalla.model.person.Person;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ondřej Mirtes
 */
@ManagedBean
@RequestScoped
@URLMapping(id="items", pattern="/items", viewId="/faces/items.xhtml")
public class ItemsBean {

	@EJB
	private IItemTypeFacadeLocal itemTypeFacade;

	@EJB
	private IGowallaFacadeLocal gowallaFacade;

	@EJB
	private IPersonFacadeLocal personFacade;

	private String itemId;

	private Person person;

    /** Creates a new instance of ItemsBean */
    public ItemsBean() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		person = (Person) session.getAttribute("person");
    }

	public Person getPerson() {
		return person;
	}

	public List<ItemType> getNotMissingItemTypes() {
		return itemTypeFacade.findNotFollowedItemTypes(person);
		//gowallaFacade.setAuthKey(person.getOAuth().getCode());
		//return gowallaFacade.getMissingItemTypes(person);
	}

	@URLAction
	public String protect() {
		if (person == null) {
			return "pretty:index";
		}

		return null;
	}

	@URLAction
	public void updateItems() {
		//gowallaFacade.setAuthKey(person.getOAuth().getCode());
		//gowallaFacade.updateItemTypes(person);
	}

	public String addItem() {
		ItemType item = itemTypeFacade.find((long) Integer.parseInt(itemId));
		personFacade.addFollowedItemType(person, item);
		itemId = "0";
		return "pretty:items";
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}
