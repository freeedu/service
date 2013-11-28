package org.personal.mason.feop.oauth.contact.common;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.NumericField;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.TwoWayFieldBridge;
import org.personal.mason.feop.oauth.contact.domain.model.Contact;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 11/27/13
 * Time: 5:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class FOEPContactBridge implements TwoWayFieldBridge {

    @Override
    public Object get(String name, Document document) {
        NumericField idField = (NumericField) document.getFieldable(name + "_id");
        NumericField versionField = (NumericField) document.getFieldable(name + "_version");
        Contact contact = new Contact();
        contact.setId(idField.getNumericValue().longValue());
        contact.setVersion(versionField.getNumericValue().intValue());

        return contact;
    }


    @Override
    public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
        Contact contact = (Contact) value;
        Long id = contact.getId();
        int version = contact.getVersion();
        luceneOptions.addNumericFieldToDocument(name + "_id", id, document);
        luceneOptions.addNumericFieldToDocument(name + "_version", version, document);
    }

    @Override
    public String objectToString(Object object) {
        if (object instanceof Contact) {
            Contact contact = (Contact) object;

            return String.format("%d_%d", contact.getId(), contact.getVersion());
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
