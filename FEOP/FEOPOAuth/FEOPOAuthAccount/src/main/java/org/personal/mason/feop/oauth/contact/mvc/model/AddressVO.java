package org.personal.mason.feop.oauth.contact.mvc.model;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("address")
public class AddressVO {

private Long id;
private String label;
private String postcode;
private Long contact;
private int version;
private String country;
private String line1;
private String line2;
private String line3;
private String line4;
private String line5;
private String line6;
private String line7;
private String line8;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getLabel() {
	return label;
}

public void setLabel(String label) {
	this.label = label;
}

public String getPostcode() {
	return postcode;
}

public void setPostcode(String postcode) {
	this.postcode = postcode;
}

public Long getContact() {
	return contact;
}

public void setContact(Long contact) {
	this.contact = contact;
}

public int getVersion() {
	return version;
}

public void setVersion(int version) {
	this.version = version;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getLine1() {
	return line1;
}

public void setLine1(String line1) {
	this.line1 = line1;
}

public String getLine2() {
	return line2;
}

public void setLine2(String line2) {
	this.line2 = line2;
}

public String getLine3() {
	return line3;
}

public void setLine3(String line3) {
	this.line3 = line3;
}

public String getLine4() {
	return line4;
}

public void setLine4(String line4) {
	this.line4 = line4;
}

public String getLine5() {
	return line5;
}

public void setLine5(String line5) {
	this.line5 = line5;
}

public String getLine6() {
	return line6;
}

public void setLine6(String line6) {
	this.line6 = line6;
}

public String getLine7() {
	return line7;
}

public void setLine7(String line7) {
	this.line7 = line7;
}

public String getLine8() {
	return line8;
}

public void setLine8(String line8) {
	this.line8 = line8;
}

}
