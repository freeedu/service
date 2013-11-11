package org.personal.mason.feop.oauth.contact.mvc.rest;

import org.personal.mason.feop.oauth.contact.mvc.model.Account;
import org.personal.mason.feop.oauth.contact.mvc.model.Address;
import org.personal.mason.feop.oauth.contact.mvc.model.Contact;
import org.personal.mason.feop.oauth.contact.mvc.model.Device;
import org.personal.mason.feop.oauth.contact.mvc.model.Email;
import org.personal.mason.feop.oauth.contact.mvc.model.IM;
import org.personal.mason.feop.oauth.contact.mvc.model.InfoCommon;
import org.personal.mason.feop.oauth.contact.mvc.model.InfoType;
import org.personal.mason.feop.oauth.contact.mvc.model.Phone;
import org.personal.mason.feop.oauth.contact.mvc.model.Record;
import org.personal.mason.feop.oauth.contact.mvc.model.RemindDate;
import org.personal.mason.feop.oauth.contact.mvc.model.Resource;
import org.personal.mason.feop.oauth.contact.mvc.model.Setting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The ContactApi is using for all the non query operations for the whle contact
 * system.
 * 
 * @author mason
 * 
 */
@Controller
@RequestMapping("contact")
public class ContactApi {

/**
 * This method will be invoke from the mobile device endpoint. Required props:
 * 	name			the device name (such as 'abc's iphone')
 * 	identifier		the device identifier (usually is the device uuid)
 *  machineAddress	the device mac-address
 *  type			the device type (such as android-phone, iPhone, iPad etc.)
 *  oauthUser		the user oauth username
 *  oauthSecret		the user oauth password
 *  
 *  System use username and password to verify information and retrieve account 
 *  oauth_id, use oauth_id to get the account or create a new account. One identifier
 *  can bind only one account, if it bind to one exist account, it cannot bind to a 
 *  new account until it is unbind to the exist account.
 * 
 * @param device
 * @return
 */
@ResponseBody
@RequestMapping(value = { "regist" }, method = RequestMethod.POST)
public Account registWithDevice(Device device) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * this is used for web base registration. Use this method to create an 
 * account from the web interface. User first login to the oauth system
 *  and then get the information access token, use the token and the 
 * oauth_uid to create an account. 
 * 
 * @param oauthuid
 * @param token
 * @return
 */
@ResponseBody
@RequestMapping(value = { "regist" }, method = RequestMethod.GET)
public Account registWithOauth(String oauthuid, String token) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * After the account login success, use can use the bind this device to
 * my account operation to bind the device and account, then user can 
 * synthonize different device through bind different to one account.
 * 
 * @param device
 * @return
 */
@ResponseBody
@RequestMapping(value = { "device/bind" }, method = RequestMethod.POST)
public Account bindDevice(Device device) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Unbind the current device to the login account, after this operation 
 * the Data is not longer synthonize any more.
 * 
 * @param device
 * @return
 */
@ResponseBody
@RequestMapping(value = { "device/unbind" }, method = RequestMethod.POST)
public Account unbindDevice(Device device) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Find my Contact with accountId 
 * 
 * @param accountId
 * @param ismine
 * @return
 */
@ResponseBody
@RequestMapping(value = { "relation/get" }, method = RequestMethod.GET)
public Account getContact(String accountId) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Delete one of my contact
 * 
 * @param accountId
 * @param contact
 */
@ResponseBody
@RequestMapping(value = { "relation/delete" }, method = RequestMethod.DELETE)
public void deleteRelation(String accountId, Contact contact) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Add a contact
 * 
 * @param accountId
 * @param contact
 * @return
 */
@ResponseBody
@RequestMapping(value = { "relation/add" }, method = RequestMethod.POST)
public Contact newRelation(String accountId, Contact contact) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Update one of my contact include mine
 * 
 * @param accountId
 * @param contact
 * @return
 */
@ResponseBody
@RequestMapping(value = { "relation/update" }, method = RequestMethod.PUT)
public Contact updateRelation(String accountId, Contact contact) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Add address for contact
 * 
 * @param accountId
 * @param contactId
 * @param address
 * @return
 */
@ResponseBody
@RequestMapping(value = { "address/add" }, method = RequestMethod.POST)
public Address addContactAddress(Long accountId, Long contactId, Address address) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Update address for contact
 * 
 * @param accountId
 * @param contactId
 * @param address
 * @return
 */
@ResponseBody
@RequestMapping(value = { "address/update" }, method = RequestMethod.PUT)
public Address updateContactAddress(Long accountId, Long contactId, Address address) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Delete address for contact
 * 
 * @param accountId
 * @param contactId
 * @param address
 * @return
 */
@ResponseBody
@RequestMapping(value = { "address/delete" }, method = RequestMethod.DELETE)
public Address deleteContactAddress(Long accountId, Long contactId, Address address) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Add email for contact
 * 
 * @param accountId
 * @param contactId
 * @param email
 * @return
 */
@ResponseBody
@RequestMapping(value = { "email/add" }, method = RequestMethod.POST)
public Email addContactEmail(Long accountId, Long contactId, Email email) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Update email for contact
 * 
 * @param accountId
 * @param contactId
 * @param Email
 * @return
 */
@ResponseBody
@RequestMapping(value = { "email/update" }, method = RequestMethod.PUT)
public Email updateContactEmail(Long accountId, Long contactId, Email Email) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Delete email for contact
 * 
 * @param accountId
 * @param contactId
 * @param email
 * @return
 */
@ResponseBody
@RequestMapping(value = { "email/delete" }, method = RequestMethod.DELETE)
public Email deleteContactEmail(Long accountId, Long contactId, Email email) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Add im for contact
 * 
 * @param accountId
 * @param contactId
 * @param im
 * @return
 */
@ResponseBody
@RequestMapping(value = { "im/add" }, method = RequestMethod.POST)
public IM addContactIM(Long accountId, Long contactId, IM im) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Update im for contact
 * 
 * @param accountId
 * @param contactId
 * @param im
 * @return
 */
@ResponseBody
@RequestMapping(value = { "im/update" }, method = RequestMethod.PUT)
public IM updateContactIM(Long accountId, Long contactId, IM im) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Delete im for contact
 * 
 * @param accountId
 * @param contactId
 * @param im
 * @return
 */
@ResponseBody
@RequestMapping(value = { "im/delete" }, method = RequestMethod.DELETE)
public IM deleteContactIM(Long accountId, Long contactId, IM im) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Add phone to Contact
 * 
 * @param accountId
 * @param contactId
 * @param phone
 * @return
 */
@ResponseBody
@RequestMapping(value = { "phone/add" }, method = RequestMethod.POST)
public Phone addContactPhone(Long accountId, Long contactId, Phone phone) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Update phone of Contact
 * 
 * @param accountId
 * @param contactId
 * @param phone
 * @return
 */
@ResponseBody
@RequestMapping(value = { "phone/update" }, method = RequestMethod.PUT)
public Phone updateContactPhone(Long accountId, Long contactId, Phone phone) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Delete phone of Contact
 * 
 * @param accountId
 * @param contactId
 * @param phone
 * @return
 */
@ResponseBody
@RequestMapping(value = { "phone/delete" }, method = RequestMethod.DELETE)
public Phone deleteContactPhone(Long accountId, Long contactId, Phone phone) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Add record to contact
 * 
 * @param accountId
 * @param contactId
 * @param record
 * @return
 */
@ResponseBody
@RequestMapping(value = { "record/add" }, method = RequestMethod.POST)
public Record addContactRecord(Long accountId, Long contactId, Record record) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Update record of contact
 * 
 * @param accountId
 * @param contactId
 * @param record
 * @return
 */
@ResponseBody
@RequestMapping(value = { "record/update" }, method = RequestMethod.PUT)
public Record updateContactRecord(Long accountId, Long contactId, Record record) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Delete record of Contact
 * 
 * @param accountId
 * @param contactId
 * @param record
 * @return
 */
@ResponseBody
@RequestMapping(value = { "record/delete" }, method = RequestMethod.DELETE)
public Record deleteContactRecord(Long accountId, Long contactId, Record record) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Add remind date to Contact
 * 
 * @param accountId
 * @param contactId
 * @param remindDate
 * @return
 */
@ResponseBody
@RequestMapping(value = { "remind/add" }, method = RequestMethod.POST)
public RemindDate addContactRemindDate(Long accountId, Long contactId, RemindDate remindDate) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Update remind date of contact
 * 
 * @param accountId
 * @param contactId
 * @param remindDate
 * @return
 */
@ResponseBody
@RequestMapping(value = { "remind/update" }, method = RequestMethod.PUT)
public RemindDate updateContactRemindDate(Long accountId, Long contactId, RemindDate remindDate) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Delete remind date of contact
 * 
 * @param accountId
 * @param contactId
 * @param remindDate
 * @return
 */
@ResponseBody
@RequestMapping(value = { "remind/delete" }, method = RequestMethod.DELETE)
public RemindDate deleteContactRemindDate(Long accountId, Long contactId, RemindDate remindDate) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Add resource to Contact
 * 
 * @param accountId
 * @param contactId
 * @param resource
 * @return
 */
@ResponseBody
@RequestMapping(value = { "resource/add" }, method = RequestMethod.POST)
public Resource addContactResource(Long accountId, Long contactId, Resource resource) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Update Resource of contact
 * 
 * @param accountId
 * @param contactId
 * @param resource
 * @return
 */
@ResponseBody
@RequestMapping(value = { "resource/update" }, method = RequestMethod.PUT)
public Resource updateContactResource(Long accountId, Long contactId, Resource resource) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Delete resource of contact
 * 
 * @param accountId
 * @param contactId
 * @param resource
 * @return
 */
@ResponseBody
@RequestMapping(value = { "resource/delete" }, method = RequestMethod.DELETE)
public Resource deleteContactResource(Long accountId, Long contactId, Resource resource) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Add setting
 * 
 * @param accountId
 * @param contactId
 * @param setting
 * @return
 */
@ResponseBody
@RequestMapping(value = { "setting/add" }, method = RequestMethod.POST)
public Setting addContactSetting(Long accountId, Long contactId, Setting setting) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Update Setting
 * 
 * @param accountId
 * @param contactId
 * @param setting
 * @return
 */
@ResponseBody
@RequestMapping(value = { "setting/update" }, method = RequestMethod.PUT)
public Setting updateContactSetting(Long accountId, Long contactId, Setting setting) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Delete Setting
 * 
 * @param accountId
 * @param contactId
 * @param setting
 * @return
 */
@ResponseBody
@RequestMapping(value = { "setting/delete" }, method = RequestMethod.DELETE)
public Setting deleteContactSetting(Long accountId, Long contactId, Setting setting) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Add common info to contact
 * 
 * @param accountId
 * @param contactId
 * @param infoCommon
 * @return
 */
@ResponseBody
@RequestMapping(value = { "common/add" }, method = RequestMethod.POST)
public InfoCommon addContactInfoCommon(Long accountId, Long contactId, InfoCommon infoCommon) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Update Common info of contact
 * 
 * @param accountId
 * @param contactId
 * @param infoCommon
 * @return
 */
@ResponseBody
@RequestMapping(value = { "common/update" }, method = RequestMethod.PUT)
public InfoCommon updateContactInfoCommon(Long accountId, Long contactId, InfoCommon infoCommon) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Delete common info of contact
 * 
 * @param accountId
 * @param contactId
 * @param infoCommon
 * @return
 */
@ResponseBody
@RequestMapping(value = { "common/delete" }, method = RequestMethod.DELETE)
public InfoCommon deleteContactInfoCommon(Long accountId, Long contactId, InfoCommon infoCommon) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Add common info type
 * 
 * @param accountId
 * @param contactId
 * @param infoType
 * @return
 */
@ResponseBody
@RequestMapping(value = { "info/type/add" }, method = RequestMethod.POST)
public InfoType addContactInfoType(Long accountId, Long contactId, InfoType infoType) {
	throw new RuntimeException("Not Implemented Exception");
}

/**
 * Delete Common info type
 * 
 * @param accountId
 * @param contactId
 * @param infoType
 * @return
 */
@ResponseBody
@RequestMapping(value = { "info/type/delete" }, method = RequestMethod.DELETE)
public InfoType deleteContactInfoType(Long accountId, Long contactId, InfoType infoType) {
	throw new RuntimeException("Not Implemented Exception");
}

}
