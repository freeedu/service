package org.personal.mason.feop.oauth.contact.mvc.rest;

import java.util.Date;
import java.util.List;

import org.personal.mason.feop.oauth.contact.mvc.model.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contact/q")
public class ContactQueryApi {

public List<Contact> findContactByRemindDate(Long accountId, Date date) {
	throw new RuntimeException("Not Implemented Exception");
}

public List<Contact> findUpdatedContact(Long accountId) {
	throw new RuntimeException("Not Implemented Exception");
}

public Contact findContact(Long contactId) {
	throw new RuntimeException("Not Implemented Exception");
}



}
