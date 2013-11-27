package org.personal.mason.feop.oauth.contact.domain.repository;

import org.apache.lucene.search.Filter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.personal.mason.feop.oauth.contact.domain.model.Contact;
import org.personal.mason.feop.oauth.contact.domain.model.ContactRecord;
import org.personal.mason.feop.oauth.contact.domain.model.ContactRemindDate;
import org.personal.mason.feop.oauth.contact.domain.model.ContactResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactRepositoryImpl implements ContactRepositoryCustom {
    private static final Logger logger = LoggerFactory.getLogger(ContactRepositoryImpl.class);

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    private FullTextEntityManager fullTextEntityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public FullTextEntityManager getFullTextEntityManager() {
        if (fullTextEntityManager == null) {
            fullTextEntityManager = Search.getFullTextEntityManager(em);
            updateFullTextIndex();
        }
        return fullTextEntityManager;
    }

    public void updateFullTextIndex() {
        try {
            getFullTextEntityManager().createIndexer().startAndWait();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteria = criteriaBuilder.createQuery(Contact.class);
        Root<Contact> fromContact = criteria.from(Contact.class);
        List<Contact> cs = em.createQuery(criteria.select(fromContact)).getResultList();
        if(cs != null){
            for (Contact c : cs){
                getFullTextEntityManager().index(c);
            }
        }
    }

    @Override
    public List<Contact> getByContactAndContactRemindDateRemindDate(Contact contact, Date date) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Contact> criteria = criteriaBuilder.createQuery(Contact.class);
            Root<Contact> fromContact = criteria.from(Contact.class);
            Join<Contact, ContactRemindDate> remindDateJoin = fromContact.join("contactRemindDates");

            Predicate contactPredicate = criteriaBuilder.equal(fromContact.get("contact"), contact);
            Predicate remindDatePredicate = criteriaBuilder.equal(remindDateJoin.get("remindDate"), date);

            criteria.distinct(true).where(contactPredicate, remindDatePredicate).select(fromContact);
            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Contact> getByContactAndContactRecord(Contact contact, ContactRecord record) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Contact> criteria = criteriaBuilder.createQuery(Contact.class);
            Root<Contact> fromContact = criteria.from(Contact.class);
            Join<Contact, ContactRecord> recordJoin = fromContact.join("contactRecords");

            List<Predicate> conditions = new ArrayList<>();
            Predicate contactPredicate = criteriaBuilder.equal(fromContact.get("contact"), contact);
            conditions.add(contactPredicate);

            if (record.getStartDate() != null) {
                Predicate startDatePredicate = criteriaBuilder.greaterThanOrEqualTo(recordJoin.<Date>get("startDate"), record.getStartDate());
                conditions.add(startDatePredicate);
            }

            if (record.getEndDate() != null) {
                Predicate endDatePredicate = criteriaBuilder.lessThanOrEqualTo(recordJoin.<Date>get("endDate"), record.getEndDate());
                conditions.add(endDatePredicate);
            }

            if (record.getAccomplishment() != null && !record.getAccomplishment().isEmpty()) {
                Predicate accomplishmentPredicate = criteriaBuilder.like(recordJoin.<String>get("accomplishment"), String.format("%%%s%%", record.getAccomplishment().trim()));
                conditions.add(accomplishmentPredicate);
            }

            if (record.getDescription() != null && !record.getDescription().isEmpty()) {
                Predicate descriptionPredicate = criteriaBuilder.like(recordJoin.<String>get("description"), String.format("%%%s%%", record.getDescription().trim()));
                conditions.add(descriptionPredicate);
            }


            criteria.distinct(true).where(conditions.toArray(new Predicate[]{})).select(fromContact);
            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Contact> getByContactAndContactResource(Contact contact, ContactResource resource) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Contact> criteria = criteriaBuilder.createQuery(Contact.class);
            Root<Contact> fromContact = criteria.from(Contact.class);
            Join<Contact, ContactResource> resourceJoin = fromContact.join("contactResources");

            List<Predicate> conditions = new ArrayList<>();
            Predicate contactPredicate = criteriaBuilder.equal(fromContact.get("contact"), contact);
            conditions.add(contactPredicate);

            if (resource.getBuildDate() != null) {
                Predicate buildDatePredicate = criteriaBuilder.greaterThanOrEqualTo(resourceJoin.<Date>get("buildDate"), resource.getBuildDate());
                conditions.add(buildDatePredicate);
            }

            if (resource.getResourceName() != null && !resource.getResourceName().isEmpty()) {
                Predicate resourceNamePredicate = criteriaBuilder.like(resourceJoin.<String>get("resourceName"), String.format("%%%s%%", resource.getResourceName().trim()));
                conditions.add(resourceNamePredicate);
            }

            if (resource.getDescription() != null && !resource.getDescription().isEmpty()) {
                Predicate descriptionPredicate = criteriaBuilder.like(resourceJoin.<String>get("description"), String.format("%%%s%%", resource.getDescription().trim()));
                conditions.add(descriptionPredicate);
            }

            criteria.distinct(true).where(conditions.toArray(new Predicate[]{})).select(fromContact);
            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Contact> getUpdateContacts(Contact contact) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Contact> criteria = criteriaBuilder.createQuery(Contact.class);
            Root<Contact> fromContact = criteria.from(Contact.class);

            Predicate contactPredicate = criteriaBuilder.equal(fromContact.get("contact"), contact);
            Predicate lineToPredicate = criteriaBuilder.isNull(fromContact.get("linkToContactId"));

            Subquery<Contact> linkedContact = criteria.subquery(Contact.class);
            Root<Contact> subQueryFrom = linkedContact.from(Contact.class);
            Predicate linkedContactVersionPredicate = criteriaBuilder.equal(subQueryFrom.get("id"), fromContact.get("linkToContactId"));

            Predicate versionPredicate = criteriaBuilder.lessThan(fromContact.<Integer>get("version"), subQueryFrom.<Integer>get("version"));

            criteria.distinct(true).where(contactPredicate, lineToPredicate, linkedContactVersionPredicate, versionPredicate).select(fromContact);

            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Contact> getByContactAndQuery(Long contactId, String queryString) {
        QueryBuilder qb = getFullTextEntityManager().getSearchFactory().buildQueryBuilder().forEntity(Contact.class).get();
        org.apache.lucene.search.Query query = qb.bool()
                .must(qb.keyword().onFields("companyName", "contactName", "departmentName", "firstName", "jobTitleName", "lastName", "nickName", "note",
                        "contactEmails.emailAddress", "contactCommons.value", "contactInstantMessages.imAddress", "contactPhones.phoneNumber",
                        "contactRecords.accomplishment", "contactRecords.description", "contactResources.resourceName", "contactResources.description").matching(queryString).createQuery())
                .must(qb.keyword().onField("relatedContactId").matching(contactId).createQuery())
                .createQuery();

        FullTextQuery fullTextQuery = getFullTextEntityManager().createFullTextQuery(query, Contact.class);

//        org.apache.lucene.search.Query query2 = qb.keyword().onField("contact").matching(contact).createQuery();
//
//        fullTextQuery.enableFullTextFilter("contact_filter").setParameter("query", query2);

        List<Contact> result = fullTextQuery.getResultList();

        return result;
    }

    @Override
    public List<Contact> getContactMightKnown(Contact contact) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
