/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import cz.cvut.x33eja.gowalla.model.person.Person;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ondrej
 */
@ManagedBean
@RequestScoped
@URLMapping(id="index", pattern="/", viewId="/faces/index.xhtml")
public class IndexBean {

    /** Creates a new instance of IndexBean */
    public IndexBean() {
    }

	@URLAction
	public String index() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if (session.getAttribute("person") != null) {
			return "pretty:items";
		}

		return null;
	}

	public String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Person person = (Person) session.getAttribute("person");
		if (person != null) {
			return person.getName();
		} else {
			return null;
		}
	}

}
