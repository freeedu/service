package org.personal.mason.feop.oauth.contact.domain.model;

import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "contact_info_type")
public class ContactInfoType extends FOEPPersistable<Long> {

    private static final long serialVersionUID = -5212794572090869477L;

    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @Column(name = "description", length = 255)
    private String description;

    // bi-directional many-to-one association to ContactPhone
    @OneToMany(mappedBy = "infoType")
    private List<ContactInfoCommon> infoCommon;

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

}
