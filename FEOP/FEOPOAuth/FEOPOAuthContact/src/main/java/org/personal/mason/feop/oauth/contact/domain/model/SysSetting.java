package org.personal.mason.feop.oauth.contact.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 11/26/13
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "sys_setting")
public class SysSetting extends FOEPPersistable<Long> {

    @Column(name = "setting_key", unique = true, nullable = false)
    private String key;
    @Column(name = "setting_value")
    private String value;
    @Column(length = 255)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
