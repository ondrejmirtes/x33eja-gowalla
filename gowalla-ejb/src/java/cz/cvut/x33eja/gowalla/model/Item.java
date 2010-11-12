/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.model;

import com.sun.istack.internal.Nullable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author ondrej
 */
@Entity
public class Item extends SimpleEntity {

    private Integer number;

    @ManyToOne
    @Nullable
    private Person person;

    @ManyToOne
    @Nullable
    private Collection collection;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

}
