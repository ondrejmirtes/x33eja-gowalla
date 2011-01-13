/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLQueryParameter;
import cz.cvut.x33eja.gowalla.model.GowallaFacade;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author ondrej
 */
@ManagedBean
@RequestScoped
@URLMapping(id="oauth", pattern="/oauth-redirect", viewId="/oauth.xhtml")
public class OAuthBean {

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
		UriBuilder builder = UriBuilder.fromUri("https://gowalla.com/api/oauth/new");
		builder.queryParam("redirect_uri", "http://localhost:8080/gowalla-war/oauth-redirect");
		builder.queryParam("client_id", GowallaFacade.API_KEY);
		return builder.build().toString();
	}

	@URLAction
	public void login() {
		
	}

}
