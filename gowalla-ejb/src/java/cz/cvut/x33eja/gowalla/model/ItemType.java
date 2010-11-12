/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model;

import com.sun.jndi.toolkit.url.Uri;
import javax.persistence.Entity;

/**
 *
 * @author ondrej
 */
@Entity
public class ItemType extends SimpleEntity {

    private String name;

    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image.getUrl();
    }

}
