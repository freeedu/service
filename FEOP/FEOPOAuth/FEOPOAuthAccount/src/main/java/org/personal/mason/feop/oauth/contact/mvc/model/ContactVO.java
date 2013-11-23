package org.personal.mason.feop.oauth.contact.mvc.model;

import org.codehaus.jackson.map.annotate.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName("contact")
public class ContactVO {

    private Long id;
    private String company;
    private String name;
    private String department;
    private String firstName;
    private String middleName;
    private String lastName;
    private String jobTitle;
    private Long linkTo;
    private String nickName;
    private String note;
    private String photoUrl;
    private Long belongTo;
    private int version;
    private List<ContactVO> contacts = new ArrayList<>();
    private List<RecordVO> records = new ArrayList<>();
    private List<ResourceVO> resources = new ArrayList<>();
    private List<AddressVO> addresses = new ArrayList<>();
    private List<EmailVO> emails = new ArrayList<>();
    private List<IMVO> ims = new ArrayList<>();
    private List<PhoneVO> phones = new ArrayList<>();
    private List<RemindDateVO> remindDates = new ArrayList<>();
    private List<SettingVO> settinngs = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getLinkTo() {
        return linkTo;
    }

    public void setLinkTo(Long linkTo) {
        this.linkTo = linkTo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Long getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(Long belongTo) {
        this.belongTo = belongTo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<ContactVO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactVO> contacts) {
        this.contacts = contacts;
    }

    public List<RecordVO> getRecords() {
        return records;
    }

    public void setRecords(List<RecordVO> records) {
        this.records = records;
    }

    public List<ResourceVO> getResources() {
        return resources;
    }

    public void setResources(List<ResourceVO> resources) {
        this.resources = resources;
    }

    public List<AddressVO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressVO> addresses) {
        this.addresses = addresses;
    }

    public List<EmailVO> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailVO> emails) {
        this.emails = emails;
    }

    public List<IMVO> getIms() {
        return ims;
    }

    public void setIms(List<IMVO> ims) {
        this.ims = ims;
    }

    public List<PhoneVO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneVO> phones) {
        this.phones = phones;
    }

    public List<RemindDateVO> getRemindDates() {
        return remindDates;
    }

    public void setRemindDates(List<RemindDateVO> remindDates) {
        this.remindDates = remindDates;
    }

    public List<SettingVO> getSettinngs() {
        return settinngs;
    }

    public void setSettinngs(List<SettingVO> settinngs) {
        this.settinngs = settinngs;
    }

}
