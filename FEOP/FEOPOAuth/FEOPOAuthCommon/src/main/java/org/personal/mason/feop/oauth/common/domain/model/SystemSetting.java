package org.personal.mason.feop.oauth.common.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/4/13
 * Time: 12:40 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "system_settings")
public class SystemSetting extends FOEPPersistable<Long> {
    @Column(name = "setting_profile")
    private String profile;

    @Column(name = "setting_key")
    private String key;

    @Column(name = "setting_value")
    private String value;

    @Column(length = 255)
    private String description;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    private Date startDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "end_time", nullable = true)
    private Date endDate;

    private Boolean disabled;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
