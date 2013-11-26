package org.personal.mason.feop.oauth.contact.domain.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the contact_record database table.
 */
@Entity
@Indexed
@Table(name = "contact_record")
public class ContactRecord extends FOEPPersistable<Long> {

    private static final long serialVersionUID = -3829838155744166550L;

    @Field
    @Lob
    private String accomplishment;

    @Field
    @Column(length = 255)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @Field
    @Column(length = 255)
    private String type;

    // bi-directional many-to-one association to Contact
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public ContactRecord() {
    }

    public String getAccomplishment() {
        return this.accomplishment;
    }

    public void setAccomplishment(String accomplishment) {
        this.accomplishment = accomplishment;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}