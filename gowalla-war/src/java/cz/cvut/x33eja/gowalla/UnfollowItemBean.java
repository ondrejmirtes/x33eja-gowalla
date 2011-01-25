/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import cz.cvut.x33eja.gowalla.model.item.IItemTypeFacadeLocal;
import cz.cvut.x33eja.gowalla.model.item.Item;
import cz.cvut.x33eja.gowalla.model.item.ItemType;
import cz.cvut.x33eja.gowalla.model.person.IPersonFacadeLocal;
import cz.cvut.x33eja.gowalla.model.person.Person;
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
@URLMapping(id="unfollowItem", pattern="/items-unfollow/#{itemId:unfollowItemBean.itemId}", viewId="/faces/dummy-unfollow-item.xhtml")
public class UnfollowItemBean {

	private String itemId;

	private Person person;

	@EJB
	private IItemTypeFacadeLocal itemTypeFacade;

	@EJB
	private IPersonFacadeLocal personFacade;

    /** Creates a new instance of UnfollowItemBean */
    public UnfollowItemBean() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		person = (Person) session.getAttribute("person");
    }

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@URLAction
	public String unfollowItem() {
		ItemType item = itemTypeFacade.find((long) Integer.parseInt(itemId));
		personFacade.removeFollowedItemType(person, item);
		return "pretty:items";
	}

}
