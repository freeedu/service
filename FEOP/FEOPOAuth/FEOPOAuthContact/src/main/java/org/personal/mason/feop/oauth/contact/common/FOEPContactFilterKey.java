package org.personal.mason.feop.oauth.contact.common;

import org.hibernate.search.filter.FilterKey;
import org.personal.mason.feop.oauth.contact.domain.model.Contact;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 11/27/13
 * Time: 4:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class FOEPContactFilterKey extends FilterKey {

    private Contact contact;


    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact(){
        return  contact;
    }

    @Override
    public boolean equals(Object otherKey){
        if(this.contact == null || !(otherKey instanceof FOEPContactFilterKey)){
            return  false;
        }

        FOEPContactFilterKey filterKey = (FOEPContactFilterKey) otherKey;
        return filterKey.contact.getId() != null && filterKey.contact.getId() == this.contact.getId();
    }

    @Override
    public int hashCode() {
       if(this.contact == null){
           return 0;
       }
       return this.contact.hashCode();
    }
}
