package org.personal.mason.feop.server.blog.domain.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

public class BlogPersistable extends AbstractPersistable<Long> {

    private static final long serialVersionUID = 6546618957685916754L;

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

}
