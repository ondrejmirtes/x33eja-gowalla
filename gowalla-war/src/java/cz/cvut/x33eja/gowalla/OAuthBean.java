/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLQueryParameter;
import cz.cvut.x33eja.gowalla.model.GowallaFacade;
import cz.cvut.x33eja.gowalla.model.oauth.IOAuthFacadeLocal;
import cz.cvut.x33eja.gowalla.model.person.IPersonFacadeLocal;
import cz.cvut.x33eja.gowalla.model.person.Person;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author ondrej
 */
@ManagedBean
@RequestScoped
@URLMapping(id="oauth", pattern="/oauth-redirect", viewId="/faces/dummy-oauth.xhtml")
public class OAuthBean {

	@EJB
	private IOAuthFacadeLocal oauthFacade;

	@URLQueryParameter("code")
	private String code;

    /** Creates a new instance of OAuthBean */
    public OAuthBean() {
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRedirect() {
		return oauthFacade.getOAuthRequestUrl();
	}

	@URLAction
	public String login() {
		Person person = oauthFacade.getPerson(code);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("person", person);
		return "pretty:index";
	}

}
