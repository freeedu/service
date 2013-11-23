package org.personal.mason.feop.oauth.contact.spi;


public interface BaseService<V, M> {

    public V toViewObject(M model);

    public M toModel(V view);
}
