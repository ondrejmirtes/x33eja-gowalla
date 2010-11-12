package cz.cvut.x33eja.gowalla.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author ondrej
 */
@Entity
public class Person extends SimpleEntity {

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
