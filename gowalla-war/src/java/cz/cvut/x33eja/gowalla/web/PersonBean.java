/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.x33eja.gowalla.web;

import cz.cvut.x33eja.gowalla.model.Person;
import cz.cvut.x33eja.gowalla.model.IPersonFacadeLocal;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ondrej
 */
@Named(value="personBean")
@RequestScoped
public class PersonBean {

    @EJB
    private IPersonFacadeLocal facade;

    @NotEmpty(message="Name must be set!")
    private String name;

    /** Creates a new instance of PersonBean */
    public PersonBean() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String createPerson() {
        Person p = new Person();
        p.setName(name);
        facade.create(p);
        return "index";
    }

    public String editPerson() {
        Map requestMap = FacesContext.getCurrentInstance()
                 .getExternalContext().getRequestParameterMap();

        Person p = facade.find(Long.parseLong((String) requestMap.get("id")));

        p.setName(this.name);

        facade.edit(p);

        return "index";
    }

    public Person getPerson() {
        Map requestMap = FacesContext.getCurrentInstance()
                 .getExternalContext().getRequestParameterMap();

        Person p = facade.find(Long.parseLong((String) requestMap.get("id")));
        this.name = p.getName();

        return p;
    }

    public List<Person> getPersons() {
        return facade.findAll();
    }

}
