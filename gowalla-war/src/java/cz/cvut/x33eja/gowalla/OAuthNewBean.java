/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import cz.cvut.x33eja.gowalla.model.GowallaFacade;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author ondrej
 */
@ManagedBean
@RequestScoped
@URLMapping(id="oauthNew", pattern="/oauthh", viewId="/oauth.xhtml")
public class OAuthNewBean {

    /** Creates a new instance of OAuthNewBean */
    public OAuthNewBean() {
    }

	@URLAction
	public void newAuth() throws IOException {
		System.out.println("GOWALLA OAUTH NEW");
		UriBuilder builder = UriBuilder.fromUri("https://gowalla.com/api/oauth/new");
		builder.queryParam("redirect_uri", "http://localhost:8080/gowalla-war/oauth-redirect");
		builder.queryParam("client_id", GowallaFacade.API_KEY);
		FacesContext.getCurrentInstance().getExternalContext().redirect(builder.toString());
	}

}
