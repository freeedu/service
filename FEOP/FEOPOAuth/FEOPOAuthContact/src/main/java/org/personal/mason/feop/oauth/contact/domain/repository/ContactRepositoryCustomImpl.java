package org.personal.mason.feop.oauth.contact.domain.repository;

import org.personal.mason.feop.oauth.contact.domain.model.Contact;
import org.personal.mason.feop.oauth.contact.domain.model.ContactRecord;
import org.personal.mason.feop.oauth.contact.domain.model.ContactRemindDate;
import org.personal.mason.feop.oauth.contact.domain.model.ContactResource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactRepositoryCustomImpl implements ContactRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public List<Contact> findByContactAndContactRemindDateRemindDate(Contact contact, Date date) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Contact> criteria = criteriaBuilder.createQuery(Contact.class);
            Root<Contact> root = criteria.from(Contact.class);
            criteria.distinct(true).select(root);



            Predicate contactPredicate = criteriaBuilder.equal(root.get("contact"), contact);
            SetJoin<Contact, ContactRemindDate> remindDateSetJoin = root.joinSet("contactRemindDate");

            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Contact> findByContactAndContactRecord(Contact contact, ContactRecord record) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Contact> findByContactAndContactResource(Contact contact, ContactResource resource) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Contact> findUpdateContacts(Contact contact) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Contact> findByContactAndQuery(Contact contact, String query) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Contact> findContactMightKnown(Contact contact) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
