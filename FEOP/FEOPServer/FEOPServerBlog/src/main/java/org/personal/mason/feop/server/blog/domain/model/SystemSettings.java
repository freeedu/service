package org.personal.mason.feop.server.blog.domain.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "system_settings")
public class SystemSettings extends AbstractPersistable<Long> {

    private static final long serialVersionUID = 268514826697363336L;

    @Column(name = "setting_profile")
    private String profile;

    @Column(name = "setting_key")
    private String key;
    @Column(name = "setting_value")
    private String value;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
