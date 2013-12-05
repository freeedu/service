package org.personal.mason.feop.oauth.contact.domain.model;

import org.hibernate.search.annotations.Field;
import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the contact_resource database table.
 */
@Entity
@Table(name = "contact_resource")
public class ContactResource extends FOEPPersistable<Long> {

    private static final long serialVersionUID = -2726727210425809677L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "build_date")
    private Date buildDate;

    @Lob
    @Field
    private String description;

    @Field
    @Column(name = "resource_name", length = 255)
    private String resourceName;

    // bi-directional many-to-one association to Contact
    @ManyToOne
    private Contact contact;

    public ContactResource() {
    }

    public Date getBuildDate() {
        return this.buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}