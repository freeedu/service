package org.personal.mason.feop.oauth.contact.common;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.DocIdSet;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.personal.mason.feop.oauth.contact.domain.model.Contact;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 11/27/13
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */

public class FOEPContactFilter extends Filter {
    private final String field;
    private final Contact contact;


    public FOEPContactFilter(String field, Contact contact) {
        this.field = field;
        this.contact = contact;
    }

    @Override
    public DocIdSet getDocIdSet(IndexReader reader) throws IOException {
        Query query = null;
        return new QueryWrapperFilter(query).getDocIdSet(reader);
    }
}
