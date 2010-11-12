package cz.cvut.x33eja.gowalla.model;

import javax.persistence.Entity;

/**
 *
 * @author ondrej
 */
@Entity
public class ItemType extends SimpleEntity {

    private String name;

    private String image;

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return new Image(image);
    }

    public void setImage(Image image) {
        this.image = image.getUrl();
    }

}
