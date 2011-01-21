/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import cz.cvut.x33eja.gowalla.model.item.IItemTypeFacadeLocal;
import cz.cvut.x33eja.gowalla.model.item.ItemType;
import cz.cvut.x33eja.gowalla.model.person.Person;
import java.util.List;
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

	private IItemTypeFacadeLocal itemTypeFacade;

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
	}

	@URLAction
	public String protect() {
		if (person == null) {
			return "pretty:index";
		}

		return null;
	}

}
