package org.personal.mason.feop.oauth.contact.mvc.model;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("im")
public class IMVO {
    private Long id;
    private String imAddress;
    private String label;
    private int priority;
    private Long contact;
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImAddress() {
        return imAddress;
    }

    public void setImAddress(String imAddress) {
        this.imAddress = imAddress;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

}
