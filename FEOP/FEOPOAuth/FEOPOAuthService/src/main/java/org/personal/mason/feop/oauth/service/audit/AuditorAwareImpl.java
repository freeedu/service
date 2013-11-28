package org.personal.mason.feop.oauth.service.audit;

import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.AuditorAware;

import java.io.Serializable;

public class AuditorAwareImpl<T> implements AuditorAware<Auditable<T, Serializable>> {

    private Auditable<T, Serializable> auditor;

    public void setAuditor(Auditable<T, Serializable> auditor) {
        this.auditor = auditor;
    }

    @Override
    public Auditable<T, Serializable> getCurrentAuditor() {
        return auditor;
    }

}
