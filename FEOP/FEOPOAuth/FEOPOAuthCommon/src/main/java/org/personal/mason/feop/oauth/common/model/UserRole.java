package org.personal.mason.feop.oauth.common.model;

import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.xml.bind.annotation.XmlRootElement;

@JsonRootName(value = "role")
@XmlRootElement(name = "role")
public class UserRole {

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
