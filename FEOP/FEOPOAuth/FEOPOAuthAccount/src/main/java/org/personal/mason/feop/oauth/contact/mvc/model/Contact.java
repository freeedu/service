package org.personal.mason.feop.oauth.contact.mvc.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("contact")
public class Contact {

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
private List<Contact> contacts = new ArrayList<>();
private List<Record> records = new ArrayList<>();
private List<Resource> resources = new ArrayList<>();
private List<Address> addresses = new ArrayList<>();
private List<Email> emails = new ArrayList<>();
private List<IM> ims = new ArrayList<>();
private List<Phone> phones = new ArrayList<>();
private List<RemindDate> remindDates = new ArrayList<>();
private List<Setting> settinngs = new ArrayList<>();

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

public List<Contact> getContacts() {
	return contacts;
}

public void setContacts(List<Contact> contacts) {
	this.contacts = contacts;
}

public List<Record> getRecords() {
	return records;
}

public void setRecords(List<Record> records) {
	this.records = records;
}

public List<Resource> getResources() {
	return resources;
}

public void setResources(List<Resource> resources) {
	this.resources = resources;
}

public List<Address> getAddresses() {
	return addresses;
}

public void setAddresses(List<Address> addresses) {
	this.addresses = addresses;
}

public List<Email> getEmails() {
	return emails;
}

public void setEmails(List<Email> emails) {
	this.emails = emails;
}

public List<IM> getIms() {
	return ims;
}

public void setIms(List<IM> ims) {
	this.ims = ims;
}

public List<Phone> getPhones() {
	return phones;
}

public void setPhones(List<Phone> phones) {
	this.phones = phones;
}

public List<RemindDate> getRemindDates() {
	return remindDates;
}

public void setRemindDates(List<RemindDate> remindDates) {
	this.remindDates = remindDates;
}

public List<Setting> getSettinngs() {
	return settinngs;
}

public void setSettinngs(List<Setting> settinngs) {
	this.settinngs = settinngs;
}

}
