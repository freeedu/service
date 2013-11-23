package org.personal.mason.feop.oauth.contact.domain.model;

import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the contacts database table.
 */
@Entity
@Table(name = "contacts")
public class Contact extends FOEPPersistable<Long> {

    private static final long serialVersionUID = 7754986492566919598L;

    @Column(name = "company_name", length = 255)
    private String companyName;

    @Column(name = "contact_name", length = 255)
    private String contactName;

    @Column(name = "department_name", length = 255)
    private String departmentName;

    @Column(name = "first_name", length = 80)
    private String firstName;

    @Column(name = "job_title_name", length = 255)
    private String jobTitleName;

    @Column(name = "last_name", length = 80)
    private String lastName;

    @Column(name = "link_to_contact_id")
    private Long linkToContactId;

    @Column(name = "middle_name", length = 80)
    private String middleName;

    @Column(name = "nick_name", length = 80)
    private String nickName;

    @Lob
    private String note;

    @Column(name = "photo_url", length = 255)
    private String photoUrl;

    // bi-directional many-to-one association to AccountBasic
    @OneToMany(mappedBy = "contact")
    private List<AccountBasic> accountBasics;

    // bi-directional many-to-one association to ContactAddress
    @OneToMany(mappedBy = "contact")
    private List<ContactAddress> contactAddresses;

    // bi-directional many-to-one association to ContactEmail
    @OneToMany(mappedBy = "contact")
    private List<ContactEmail> contactEmails;

    // bi-directional many-to-one association to ContactInstantMessage
    @OneToMany(mappedBy = "contact")
    private List<ContactInstantMessage> contactInstantMessages;

    // bi-directional many-to-one association to ContactPhone
    @OneToMany(mappedBy = "contact")
    private List<ContactPhone> contactPhones;

    // bi-directional many-to-one association to ContactRecord
    @OneToMany(mappedBy = "contact")
    private List<ContactRecord> contactRecords;

    // bi-directional many-to-one association to ContactRemindDate
    @OneToMany(mappedBy = "contact")
    private List<ContactRemindDate> contactRemindDates;

    // bi-directional many-to-one association to ContactResource
    @OneToMany(mappedBy = "contact")
    private List<ContactResource> contactResources;

    // bi-directional many-to-one association to ContactSetting
    @OneToMany(mappedBy = "contact")
    private List<ContactSetting> contactSettings;

    // bi-directional many-to-one association to Contact
    @ManyToOne
    @JoinColumn(name = "related_contact_id")
    private Contact contact;

    // bi-directional many-to-one association to Contact
    @OneToMany(mappedBy = "contact")
    private List<Contact> contacts;

    public Contact() {
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getJobTitleName() {
        return this.jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getLinkToContactId() {
        return this.linkToContactId;
    }

    public void setLinkToContactId(Long linkToContactId) {
        this.linkToContactId = linkToContactId;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<AccountBasic> getAccountBasics() {
        return this.accountBasics;
    }

    public void setAccountBasics(List<AccountBasic> accountBasics) {
        this.accountBasics = accountBasics;
    }

    public AccountBasic addAccountBasic(AccountBasic accountBasic) {
        getAccountBasics().add(accountBasic);
        accountBasic.setContact(this);

        return accountBasic;
    }

    public AccountBasic removeAccountBasic(AccountBasic accountBasic) {
        getAccountBasics().remove(accountBasic);
        accountBasic.setContact(null);

        return accountBasic;
    }

    public List<ContactAddress> getContactAddresses() {
        return this.contactAddresses;
    }

    public void setContactAddresses(List<ContactAddress> contactAddresses) {
        this.contactAddresses = contactAddresses;
    }

    public ContactAddress addContactAddress(ContactAddress contactAddress) {
        getContactAddresses().add(contactAddress);
        contactAddress.setContact(this);

        return contactAddress;
    }

    public ContactAddress removeContactAddress(ContactAddress contactAddress) {
        getContactAddresses().remove(contactAddress);
        contactAddress.setContact(null);

        return contactAddress;
    }

    public List<ContactEmail> getContactEmails() {
        return this.contactEmails;
    }

    public void setContactEmails(List<ContactEmail> contactEmails) {
        this.contactEmails = contactEmails;
    }

    public ContactEmail addContactEmail(ContactEmail contactEmail) {
        getContactEmails().add(contactEmail);
        contactEmail.setContact(this);

        return contactEmail;
    }

    public ContactEmail removeContactEmail(ContactEmail contactEmail) {
        getContactEmails().remove(contactEmail);
        contactEmail.setContact(null);

        return contactEmail;
    }

    public List<ContactInstantMessage> getContactInstantMessages() {
        return this.contactInstantMessages;
    }

    public void setContactInstantMessages(List<ContactInstantMessage> contactInstantMessages) {
        this.contactInstantMessages = contactInstantMessages;
    }

    public ContactInstantMessage addContactInstantMessage(ContactInstantMessage contactInstantMessage) {
        getContactInstantMessages().add(contactInstantMessage);
        contactInstantMessage.setContact(this);

        return contactInstantMessage;
    }

    public ContactInstantMessage removeContactInstantMessage(ContactInstantMessage contactInstantMessage) {
        getContactInstantMessages().remove(contactInstantMessage);
        contactInstantMessage.setContact(null);

        return contactInstantMessage;
    }

    public List<ContactPhone> getContactPhones() {
        return this.contactPhones;
    }

    public void setContactPhones(List<ContactPhone> contactPhones) {
        this.contactPhones = contactPhones;
    }

    public ContactPhone addContactPhone(ContactPhone contactPhone) {
        getContactPhones().add(contactPhone);
        contactPhone.setContact(this);

        return contactPhone;
    }

    public ContactPhone removeContactPhone(ContactPhone contactPhone) {
        getContactPhones().remove(contactPhone);
        contactPhone.setContact(null);

        return contactPhone;
    }

    public List<ContactRecord> getContactRecords() {
        return this.contactRecords;
    }

    public void setContactRecords(List<ContactRecord> contactRecords) {
        this.contactRecords = contactRecords;
    }

    public ContactRecord addContactRecord(ContactRecord contactRecord) {
        getContactRecords().add(contactRecord);
        contactRecord.setContact(this);

        return contactRecord;
    }

    public ContactRecord removeContactRecord(ContactRecord contactRecord) {
        getContactRecords().remove(contactRecord);
        contactRecord.setContact(null);

        return contactRecord;
    }

    public List<ContactRemindDate> getContactRemindDates() {
        return this.contactRemindDates;
    }

    public void setContactRemindDates(List<ContactRemindDate> contactRemindDates) {
        this.contactRemindDates = contactRemindDates;
    }

    public ContactRemindDate addContactRemindDate(ContactRemindDate contactRemindDate) {
        getContactRemindDates().add(contactRemindDate);
        contactRemindDate.setContact(this);

        return contactRemindDate;
    }

    public ContactRemindDate removeContactRemindDate(ContactRemindDate contactRemindDate) {
        getContactRemindDates().remove(contactRemindDate);
        contactRemindDate.setContact(null);

        return contactRemindDate;
    }

    public List<ContactResource> getContactResources() {
        return this.contactResources;
    }

    public void setContactResources(List<ContactResource> contactResources) {
        this.contactResources = contactResources;
    }

    public ContactResource addContactResource(ContactResource contactResource) {
        getContactResources().add(contactResource);
        contactResource.setContact(this);

        return contactResource;
    }

    public ContactResource removeContactResource(ContactResource contactResource) {
        getContactResources().remove(contactResource);
        contactResource.setContact(null);

        return contactResource;
    }

    public List<ContactSetting> getContactSettings() {
        return this.contactSettings;
    }

    public void setContactSettings(List<ContactSetting> contactSettings) {
        this.contactSettings = contactSettings;
    }

    public ContactSetting addContactSetting(ContactSetting contactSetting) {
        getContactSettings().add(contactSetting);
        contactSetting.setContact(this);

        return contactSetting;
    }

    public ContactSetting removeContactSetting(ContactSetting contactSetting) {
        getContactSettings().remove(contactSetting);
        contactSetting.setContact(null);

        return contactSetting;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Contact addContact(Contact contact) {
        getContacts().add(contact);
        contact.setContact(this);

        return contact;
    }

    public Contact removeContact(Contact contact) {
        getContacts().remove(contact);
        contact.setContact(null);

        return contact;
    }

}