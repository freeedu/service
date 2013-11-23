package org.personal.mason.feop.oauth.contact.mvc.model;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("infoType")
public class InfoTypeVO {

    private Long id;
    private String name;
    private String description;
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
