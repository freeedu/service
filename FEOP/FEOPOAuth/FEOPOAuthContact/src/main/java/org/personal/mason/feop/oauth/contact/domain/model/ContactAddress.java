package org.personal.mason.feop.oauth.contact.domain.model;

import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

import javax.persistence.*;

/**
 * The persistent class for the contact_address database table.
 */
@Entity
@Table(name = "contact_address")
public class ContactAddress extends FOEPPersistable<Long> {

    private static final long serialVersionUID = -7202249264363473637L;

    @Column(name = "address_country", length = 80)
    private String addressCountry;

    @Column(name = "address_line1", length = 120)
    private String addressLine1;

    @Column(name = "address_line2", length = 120)
    private String addressLine2;

    @Column(name = "address_line3", length = 120)
    private String addressLine3;

    @Column(name = "address_line4", length = 120)
    private String addressLine4;

    @Column(name = "address_line5", length = 120)
    private String addressLine5;

    @Column(name = "address_line6", length = 120)
    private String addressLine6;

    @Column(name = "address_line7", length = 120)
    private String addressLine7;

    @Column(name = "address_line8", length = 120)
    private String addressLine8;

    @Column(length = 40)
    private String label;

    @Column(length = 20)
    private String postcode;

    // bi-directional many-to-one association to Contact
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    public ContactAddress() {
    }

    public String getAddressCountry() {
        return this.addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return this.addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return this.addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getAddressLine5() {
        return this.addressLine5;
    }

    public void setAddressLine5(String addressLine5) {
        this.addressLine5 = addressLine5;
    }

    public String getAddressLine6() {
        return this.addressLine6;
    }

    public void setAddressLine6(String addressLine6) {
        this.addressLine6 = addressLine6;
    }

    public String getAddressLine7() {
        return this.addressLine7;
    }

    public void setAddressLine7(String addressLine7) {
        this.addressLine7 = addressLine7;
    }

    public String getAddressLine8() {
        return this.addressLine8;
    }

    public void setAddressLine8(String addressLine8) {
        this.addressLine8 = addressLine8;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}