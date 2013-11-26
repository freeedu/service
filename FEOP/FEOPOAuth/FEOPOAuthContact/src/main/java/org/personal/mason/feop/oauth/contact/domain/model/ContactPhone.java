package org.personal.mason.feop.oauth.contact.domain.model;

import org.hibernate.search.annotations.Field;

import javax.persistence.*;

/**
 * The persistent class for the contact_phone database table.
 */
@Entity
@Table(name = "contact_phone")
public class ContactPhone extends FOEPPersistable<Long> {

    private static final long serialVersionUID = -8693624053049777573L;

    @Column(name = "phone_label", length = 255)
    private String phoneLabel;

    @Field
    @Column(name = "phone_number", nullable = false, length = 40)
    private String phoneNumber;

    @Column(nullable = false)
    private int priority;

    // bi-directional many-to-one association to Contact
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    public ContactPhone() {
    }

    public String getPhoneLabel() {
        return this.phoneLabel;
    }

    public void setPhoneLabel(String phoneLabel) {
        this.phoneLabel = phoneLabel;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}