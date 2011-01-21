/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
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
@URLMapping(id="logout", pattern="/logout", viewId="/faces/dummy-logout.xhtml")
public class LogoutBean {

    /** Creates a new instance of LogoutBean */
    public LogoutBean() {
    }

	@URLAction
	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.removeAttribute("person");
		return "pretty:index";
	}

}
