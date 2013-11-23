package org.personal.mason.feop.oauth.contact.domain.model;

import javax.persistence.*;

/**
 * The persistent class for the contact_instant_message database table.
 */
@Entity
@Table(name = "contact_instant_message")
public class ContactInstantMessage extends FOEPPersistable<Long> {

    private static final long serialVersionUID = 9053653995042048902L;

    @Column(name = "im_address", nullable = false, length = 40)
    private String imAddress;

    @Column(name = "im_label", length = 40)
    private String imLabel;

    @Column(nullable = false)
    private int priority;

    // bi-directional many-to-one association to Contact
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    public ContactInstantMessage() {
    }

    public String getImAddress() {
        return this.imAddress;
    }

    public void setImAddress(String imAddress) {
        this.imAddress = imAddress;
    }

    public String getImLabel() {
        return this.imLabel;
    }

    public void setImLabel(String imLabel) {
        this.imLabel = imLabel;
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