package org.personal.mason.feop.oauth.contact.mvc.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.PathParam;

import org.personal.mason.feop.oauth.contact.mvc.model.Contact;
import org.personal.mason.feop.oauth.contact.mvc.model.InfoType;
import org.personal.mason.feop.oauth.contact.mvc.model.Record;
import org.personal.mason.feop.oauth.contact.mvc.model.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("contact/q")
public class ContactQueryApi {

/**
 * List the contacts has remind date with the given date
 * 
 * @param accountId
 * @param date
 * @return
 */
@RequestMapping(value = { "remind" }, method = RequestMethod.GET)
@ResponseBody
public List<Contact> findContactByRemindDate(Long accountId, Date date) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * List the contacts which are binded and has information updated.
 * 
 * @param accountId
 * @return
 */
@RequestMapping(value = { "updates" }, method = RequestMethod.GET)
@ResponseBody
public List<Contact> findUpdatedContact(Long accountId) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Find the contact with contact id
 * 
 * @param contactId
 * @return
 */
@RequestMapping(value = { "{id}" }, method = RequestMethod.GET)
@ResponseBody
public Contact findContact(@PathParam("id") Long contactId) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Find contact with record of mine
 * 
 * @param accountId
 * @param record
 * @return
 */
@RequestMapping(value = { "rec" }, method = RequestMethod.GET)
@ResponseBody
public List<Contact> findContactWithRecord(Long accountId, Record record) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Find contact with resource of mine
 * 
 * @param accountId
 * @param resource
 * @return
 */
@RequestMapping(value = { "res" }, method = RequestMethod.GET)
@ResponseBody
public List<Contact> findContactWithResource(Long accountId, Resource resource) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Find contacts with key word.
 * 
 * @param accountId
 * @param query
 * @return
 */
@RequestMapping(value = { "kw" }, method = RequestMethod.GET)
@ResponseBody
public List<Contact> findContactWithString(Long accountId, String query) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Find the contacts that user may known
 * 
 * @param accoutId
 * @return
 */
@RequestMapping(value = { "known" }, method = RequestMethod.GET)
@ResponseBody
public List<Contact> findContactsKnown(Long accoutId) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Find all the Contact info types.
 * 
 * @return
 */
@RequestMapping(value = { "info/type/list" }, method = RequestMethod.GET)
@ResponseBody
public List<InfoType> listAllTypes() {
	throw new RuntimeException("Not Implemented Exception");
}
}