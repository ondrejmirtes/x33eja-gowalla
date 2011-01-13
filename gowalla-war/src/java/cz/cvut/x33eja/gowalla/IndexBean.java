/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ondrej
 */
@ManagedBean
@RequestScoped
@URLMapping(id="index", pattern="/", viewId="/index.xhtml")
public class IndexBean {

    /** Creates a new instance of IndexBean */
    public IndexBean() {
    }

	@URLAction
	public void test() {
		
	}

}
