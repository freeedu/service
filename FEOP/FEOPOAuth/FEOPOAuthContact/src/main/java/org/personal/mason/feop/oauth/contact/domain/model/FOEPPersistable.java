package org.personal.mason.feop.oauth.contact.domain.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@MappedSuperclass
public abstract class FOEPPersistable<PK extends Serializable> extends AbstractPersistable<PK> {

    private static final long serialVersionUID = 2651661172234624795L;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    @Version
    private int version;

    @PreUpdate
    public void beforeUpdate() {
        this.lastUpdateTime = Calendar.getInstance().getTime();
        this.version += 1;
    }

    @PrePersist
    public void beforePersist() {
        this.createTime = Calendar.getInstance().getTime();
        this.version = 0;
    }

    @Override
    public void setId(PK id) {
          super.setId(id);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
