package org.personal.mason.feop.oauth.contact.mvc.rest;

import org.personal.mason.feop.oauth.contact.exception.BindDeviceException;
import org.personal.mason.feop.oauth.contact.exception.RegistrationException;
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
import org.personal.mason.feop.oauth.contact.protocol.AccountInterface;
import org.personal.mason.feop.oauth.contact.protocol.AccountModel;
import org.personal.mason.feop.oauth.contact.spi.AccountBasicService;
import org.personal.mason.feop.oauth.contact.spi.BindDeviceService;
import org.personal.mason.feop.oauth.contact.spi.ContactAddressService;
import org.personal.mason.feop.oauth.contact.spi.ContactEmailService;
import org.personal.mason.feop.oauth.contact.spi.ContactInfoCommonService;
import org.personal.mason.feop.oauth.contact.spi.ContactInfoTypeService;
import org.personal.mason.feop.oauth.contact.spi.ContactInstantMessageService;
import org.personal.mason.feop.oauth.contact.spi.ContactPhoneService;
import org.personal.mason.feop.oauth.contact.spi.ContactRecordService;
import org.personal.mason.feop.oauth.contact.spi.ContactRemindDateService;
import org.personal.mason.feop.oauth.contact.spi.ContactResourceService;
import org.personal.mason.feop.oauth.contact.spi.ContactService;
import org.personal.mason.feop.oauth.contact.spi.ContactSettingService;
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

private AccountInterface accountInterface;

private AccountBasicService accountBasicService;
private BindDeviceService bindDeviceService;
private ContactService contactService;
private ContactSettingService contactSettingService;
private ContactAddressService contactAddressService;
private ContactEmailService contactEmailService;
private ContactInstantMessageService contactInstantMessageService;
private ContactPhoneService contactPhoneService;
private ContactRecordService contactRecordService;
private ContactRemindDateService contactRemindDateService;
private ContactResourceService contactResourceService;
private ContactInfoCommonService contactInfoCommonService;
private ContactInfoTypeService contactInfoTypeService;

/**
 * This method will be invoke from the mobile device endpoint. Required props:
 * name the device name (such as 'abc's iphone') identifier the device
 * identifier (usually is the device uuid) machineAddress the device mac-address
 * type the device type (such as android-phone, iPhone, iPad etc.) oauthUser the
 * user oauth username oauthSecret the user oauth password
 * 
 * System use username and password to verify information and retrieve account
 * oauth_id, use oauth_id to get the account or create a new account. One
 * identifier can bind only one account, if it bind to one exist account, it
 * cannot bind to a new account until it is unbind to the exist account.
 * 
 * @param device
 * @return
 */
@ResponseBody
@RequestMapping(value = { "regist" }, method = RequestMethod.POST)
public Account registWithDevice(Device device) {
	if (device.getOauthUser().isEmpty() || device.getOauthSecret().isEmpty()) {
		throw new RegistrationException("no authention info provided");
	}
	
	AccountModel am = accountInterface.register(device.getOauthUser(), device.getOauthSecret(),
			device.getPhoneNumber());
	
	if (!am.isSuccess()) {
		throw new RegistrationException(am.getMessage());
	}
	
	if (accountBasicService.isExistAccountWithOauthUid(am.getAccountUid())) {
		throw new RegistrationException("unknow data error");
	}
	
	Account account = accountBasicService.registAccount(device, am.getAccountUid());
	
	return account;
}

/**
 * this is used for web base registration. Use this method to create an account
 * from the web interface. User first login to the oauth system and then get the
 * information access token, use the token and the oauth_uid to create an
 * account.
 * 
 * @param oauthuid
 * @param token
 * @return
 */
@ResponseBody
@RequestMapping(value = { "web/regist" }, method = RequestMethod.POST)
public Account registWithOauth(String oauthuid, String token, Device device) {
	if (!accountInterface.validate(oauthuid, token)) {
		throw new RegistrationException("invalid registration information");
	}
	
	if (accountBasicService.isExistAccountWithOauthUid(oauthuid)) {
		throw new RegistrationException("unknow data error");
	}
	
	Account account = accountBasicService.registAccount(device, oauthuid);
	return account;
}

/**
 * After the account login success, use can use the bind this device to my
 * account operation to bind the device and account, then user can synthonize
 * different device through bind different to one account.
 * 
 * @param device
 * @return
 */
@ResponseBody
@RequestMapping(value = { "device/bind" }, method = RequestMethod.POST)
public Account bindDevice(Device device) {
	if (device.getAccountId() == null || device.getOauthUid().isEmpty()) {
		throw new BindDeviceException("require binding information");
	}
	
	Account account = accountBasicService.findAccountWithOauthUidAndId(device.getOauthUid(),
			device.getAccountId());
	
	if (account == null) {
		throw new BindDeviceException("invalid binding information");
	}
	
	Device d = bindDeviceService.createOrUpdateDevice(device);
	return accountBasicService.findAccountWithId(d.getAccountId());
}

/**
 * Unbind the current device to the login account, after this operation the Data
 * is not longer synthonize any more.
 * 
 * @param device
 * @return
 */
@ResponseBody
@RequestMapping(value = { "device/unbind" }, method = RequestMethod.POST)
public Account unbindDevice(Device device) {
	boolean validated = accountInterface.validateSecret(device.getOauthUid(),
			device.getOauthSecret());
	
	if (device.getAccountId() == null) {
		throw new BindDeviceException("invalid unbinding information");
	}
	
	if (validated) {
		bindDeviceService.unbindAccount(device);
	}
	
	return accountBasicService.findAccountWithId(device.getAccountId());
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
public Contact getContact(Long accountId) {
	Contact contact = accountBasicService.findMyContact(accountId);
	return contact;
}

/**
 * Delete one of my contact
 * 
 * @param accountId
 * @param contact
 */
@ResponseBody
@RequestMapping(value = { "relation/delete" }, method = RequestMethod.DELETE)
public void deleteRelation(Long accountId, Contact contact) {
	contactService.deleteContact(accountId, contact);
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
public Contact newRelation(Long accountId, Contact contact) {
	Contact c = contactService.createContact(accountId, contact);
	return c;
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
public Contact updateRelation(Long accountId, Contact contact) {
	Contact c = contactService.updateContact(accountId, contact);
	return c;
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
	Address a = contactAddressService.createAddress(accountId, contactId, address);
	return a;
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
	Address a = contactAddressService.updateAddress(accountId, contactId, address);
	return a;
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
public void deleteContactAddress(Long accountId, Long contactId, Address address) {
	contactAddressService.deleteAddress(accountId, contactId, address);
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
	Email e = contactEmailService.createEmail(accountId, contactId, email);
	return e;
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
public Email updateContactEmail(Long accountId, Long contactId, Email email) {
	Email e = contactEmailService.updateEmail(accountId, contactId, email);
	return e;
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
public void deleteContactEmail(Long accountId, Long contactId, Email email) {
	contactEmailService.deleteEmail(accountId, contactId, email);
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
	IM i = contactInstantMessageService.createIM(accountId, contactId, im);
	return i;
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
	IM i = contactInstantMessageService.updateIM(accountId, contactId, im);
	return i;
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
public void deleteContactIM(Long accountId, Long contactId, IM im) {
	contactInstantMessageService.deleteIM(accountId, contactId, im);
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
	Phone p = contactPhoneService.createPhone(accountId, contactId, phone);
	return p;
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
	Phone p = contactPhoneService.updatePhone(accountId, contactId, phone);
	return p;
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
public void deleteContactPhone(Long accountId, Long contactId, Phone phone) {
	contactPhoneService.deletePhone(accountId, contactId, phone);
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
	Record r = contactRecordService.createRecord(accountId, contactId, record);
	return r;
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
	Record r = contactRecordService.updateRecord(accountId, contactId, record);
	return r;
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
public void deleteContactRecord(Long accountId, Long contactId, Record record) {
	contactRecordService.deleteRecord(accountId, contactId, record);
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
	RemindDate r = contactRemindDateService.createRemindDate(accountId, contactId, remindDate);
	return r;
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
	RemindDate r = contactRemindDateService.updateRemindDate(accountId, contactId, remindDate);
	return r;
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
public void deleteContactRemindDate(Long accountId, Long contactId, RemindDate remindDate) {
	contactRemindDateService.deleteRemindDate(accountId, contactId, remindDate);
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
	Resource r = contactResourceService.createResource(accountId, contactId, resource);
	return r;
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
	Resource r = contactResourceService.updateResource(accountId, contactId, resource);
	return r;
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
public void deleteContactResource(Long accountId, Long contactId, Resource resource) {
	contactResourceService.deleteResource(accountId, contactId, resource);
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
	Setting s = contactSettingService.createSetting(accountId, contactId, setting);
	return s;
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
	Setting s = contactSettingService.updateSetting(accountId, contactId, setting);
	return s;
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
public void deleteContactSetting(Long accountId, Long contactId, Setting setting) {
	contactSettingService.deleteSetting(accountId, contactId, setting);
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
	InfoCommon i = contactInfoCommonService.createInfoCommon(accountId, contactId, infoCommon);
	return i;
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
	InfoCommon i = contactInfoCommonService.updateInfoCommon(accountId, contactId, infoCommon);
	return i;
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
public void deleteContactInfoCommon(Long accountId, Long contactId, InfoCommon infoCommon) {
	contactInfoCommonService.deleteInfoCommon(accountId, contactId, infoCommon);
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
	InfoType it = contactInfoTypeService.createInfoType(accountId, contactId, infoType);
	return it;
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
public void deleteContactInfoType(Long accountId, Long contactId, InfoType infoType) {
	contactInfoTypeService.deleteInfoType(accountId, contactId, infoType);
}

}
