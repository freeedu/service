package org.personal.mason.feop.oauth.contact.common;


import org.apache.lucene.index.Term;
import org.apache.lucene.search.CachingWrapperFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.QueryWrapperFilter;
import org.hibernate.search.annotations.Factory;
import org.hibernate.search.annotations.Key;
import org.hibernate.search.filter.FilterKey;
import org.personal.mason.feop.oauth.contact.domain.model.Contact;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 11/27/13
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class FOEPContactFilterFactory {
    private Contact contact;

    @Factory
    public Filter getFilter() {
        PhraseQuery query = new PhraseQuery();


        Term term = new Term("contact.id", contact.getId().toString());//new Term("contact.id", contact.getId().toString());
        query.add(term);
        Filter filter = new QueryWrapperFilter(query);
        return new CachingWrapperFilter(filter);
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Key
    public FilterKey getKey() {
        FOEPContactFilterKey key = new FOEPContactFilterKey();
        key.setContact(this.contact);
        return key;
    }
}
