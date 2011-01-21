/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ondřej Mirtes
 */
@ManagedBean
@RequestScoped
@URLMapping(id="items", pattern="/items", viewId="/faces/items.xhtml")
public class ItemsBean {

    /** Creates a new instance of ItemsBean */
    public ItemsBean() {
    }

}
