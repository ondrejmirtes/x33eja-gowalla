/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model;

/**
 *
 * @author ondrej
 */
public class Image {

    private String url;

	public Image(String url) {
		this.url = url;
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}