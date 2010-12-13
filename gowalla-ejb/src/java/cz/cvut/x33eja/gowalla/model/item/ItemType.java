package cz.cvut.x33eja.gowalla.model.item;

import cz.cvut.x33eja.gowalla.model.Image;
import cz.cvut.x33eja.gowalla.model.SimpleEntity;
import javax.persistence.Entity;

/**
 * Category of items.
 *
 * @author Ondřej Mirtes
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
