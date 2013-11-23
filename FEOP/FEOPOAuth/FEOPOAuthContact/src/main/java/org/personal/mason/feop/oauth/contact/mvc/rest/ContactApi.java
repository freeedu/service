package org.personal.mason.feop.oauth.contact.mvc.rest;

import org.personal.mason.feop.oauth.common.protocol.AccountInterface;
import org.personal.mason.feop.oauth.common.protocol.AccountModel;
import org.personal.mason.feop.oauth.contact.exception.BindDeviceException;
import org.personal.mason.feop.oauth.contact.exception.RegistrationException;
import org.personal.mason.feop.oauth.contact.mvc.model.*;
import org.personal.mason.feop.oauth.contact.spi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.security.Principal;

/**
 * The ContactApi is using for all the non query operations for the whle contact
 * system.
 *
 * @author mason
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

    @Autowired
    public void setAccountBasicService(AccountBasicService accountBasicService) {
        this.accountBasicService = accountBasicService;
    }

    @Autowired
    public void setAccountInterface(AccountInterface accountInterface) {
        this.accountInterface = accountInterface;
    }

    @Autowired
    public void setBindDeviceService(BindDeviceService bindDeviceService) {
        this.bindDeviceService = bindDeviceService;
    }

    @Autowired
    public void setContactAddressService(ContactAddressService contactAddressService) {
        this.contactAddressService = contactAddressService;
    }

    @Autowired
    public void setContactEmailService(ContactEmailService contactEmailService) {
        this.contactEmailService = contactEmailService;
    }

    @Autowired
    public void setContactInfoCommonService(ContactInfoCommonService contactInfoCommonService) {
        this.contactInfoCommonService = contactInfoCommonService;
    }

    @Autowired
    public void setContactInfoTypeService(ContactInfoTypeService contactInfoTypeService) {
        this.contactInfoTypeService = contactInfoTypeService;
    }

    @Autowired
    public void setContactInstantMessageService(
            ContactInstantMessageService contactInstantMessageService) {
        this.contactInstantMessageService = contactInstantMessageService;
    }

    @Autowired
    public void setContactPhoneService(ContactPhoneService contactPhoneService) {
        this.contactPhoneService = contactPhoneService;
    }

    @Autowired
    public void setContactRecordService(ContactRecordService contactRecordService) {
        this.contactRecordService = contactRecordService;
    }

    @Autowired
    public void setContactRemindDateService(ContactRemindDateService contactRemindDateService) {
        this.contactRemindDateService = contactRemindDateService;
    }

    @Autowired
    public void setContactResourceService(ContactResourceService contactResourceService) {
        this.contactResourceService = contactResourceService;
    }

    @Autowired
    public void setContactSettingService(ContactSettingService contactSettingService) {
        this.contactSettingService = contactSettingService;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * This method will be invoke from the mobile device endpoint. Required props:
     * name the device name (such as 'abc's iphone') identifier the device
     * identifier (usually is the device uuid) machineAddress the device mac-address
     * type the device type (such as android-phone, iPhone, iPad etc.) oauthUser the
     * user oauth username oauthSecret the user oauth password
     * <p/>
     * System use username and password to verify information and retrieve account
     * oauth_id, use oauth_id to get the account or create a new account. One
     * identifier can bind only one account, if it bind to one exist account, it
     * cannot bind to a new account until it is unbind to the exist account.
     *
     * @param device
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"regist"}, method = RequestMethod.POST)
    public AccountVO registWithDevice(@RequestBody DeviceVO device) {
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

        AccountVO account = accountBasicService.registAccount(device, am.getAccountUid());

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
    @RequestMapping(value = {"web/regist"}, method = RequestMethod.POST)
    public AccountVO registWithOauth(DeviceVO device, Principal principal) {
        AccountModel am = accountInterface.findAccount(principal.getName());

        if (!am.isSuccess()) {
            throw new RegistrationException("invalid user status");
        }

        if (accountBasicService.isExistAccountWithOauthUid(am.getAccountUid())) {
            throw new RegistrationException("unknow data error");
        }

        AccountVO account = accountBasicService.registAccount(device, am.getAccountUid());
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
    @RequestMapping(value = {"device/bind"}, method = RequestMethod.POST)
    public AccountVO bindDevice(@RequestBody DeviceVO device) {
        if (device.getAccountId() == null || device.getOauthUid().isEmpty()) {
            throw new BindDeviceException("require binding information");
        }

        AccountVO account = accountBasicService.findAccountWithOauthUidAndId(device.getOauthUid(),
                device.getAccountId());

        if (account == null) {
            throw new BindDeviceException("invalid binding information");
        }

        DeviceVO d = bindDeviceService.createOrUpdateDevice(device);
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
    @RequestMapping(value = {"device/unbind"}, method = RequestMethod.POST)
    public AccountVO unbindDevice(@RequestBody DeviceVO device) {
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
    @RequestMapping(value = {"relation/me"}, method = RequestMethod.GET)
    public ContactVO getContact(@RequestParam("id") Long accountId) {
        ContactVO contact = accountBasicService.findMyContact(accountId);
        return contact;
    }

    /**
     * Find the contact with contact id
     *
     * @param contactId
     * @return
     */
    @RequestMapping(value = {"relation/find"}, method = RequestMethod.GET)
    @ResponseBody
    public ContactVO findContact(@PathParam("id") Long contactId) {
        return contactService.findWithId(contactId);
    }

    /**
     * Delete one of my contact
     *
     * @param accountId
     * @param contact
     */
    @ResponseBody
    @RequestMapping(value = {"relation/delete"}, method = RequestMethod.DELETE)
    public void deleteRelation(@RequestBody ContactVO contact) {
        contactService.deleteContact(contact);
    }

    /**
     * Add a contact
     *
     * @param accountId
     * @param contact
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"relation/add"}, method = RequestMethod.POST)
    public ContactVO newRelation(@RequestBody ContactVO contact) {
        ContactVO c = contactService.createContact(contact);
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
    @RequestMapping(value = {"relation/update"}, method = RequestMethod.PUT)
    public ContactVO updateRelation(@RequestBody ContactVO contact) {
        ContactVO c = contactService.updateContact(contact);
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
    @RequestMapping(value = {"address/add"}, method = RequestMethod.POST)
    public AddressVO addContactAddress(@RequestBody AddressVO address) {
        AddressVO a = contactAddressService.createAddress(address);
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
    @RequestMapping(value = {"address/update"}, method = RequestMethod.PUT)
    public AddressVO updateContactAddress(@RequestBody AddressVO address) {
        AddressVO a = contactAddressService.updateAddress(address);
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
    @RequestMapping(value = {"address/delete"}, method = RequestMethod.DELETE)
    public void deleteContactAddress(@RequestBody AddressVO address) {
        contactAddressService.deleteAddress(address);
    }

    /**
     * Find Contact Address with id
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"address/find"}, method = RequestMethod.GET)
    public AddressVO findContactAddress(@RequestParam("id") Long id) {
        return contactAddressService.findWithId(id);
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
    @RequestMapping(value = {"email/add"}, method = RequestMethod.POST)
    public EmailVO addContactEmail(@RequestBody EmailVO email) {
        EmailVO e = contactEmailService.createEmail(email);
        return e;
    }

    /**
     * Update email for contact
     *
     * @param accountId
     * @param contactId
     * @param EmailVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"email/update"}, method = RequestMethod.PUT)
    public EmailVO updateContactEmail(@RequestBody EmailVO email) {
        EmailVO e = contactEmailService.updateEmail(email);
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
    @RequestMapping(value = {"email/delete"}, method = RequestMethod.DELETE)
    public void deleteContactEmail(@RequestBody EmailVO email) {
        contactEmailService.deleteEmail(email);
    }

    /**
     * Find Contact Email with id
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"email/find"}, method = RequestMethod.GET)
    public EmailVO findContactEmail(@RequestParam("id") Long id) {
        return contactEmailService.findWithId(id);
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
    @RequestMapping(value = {"im/add"}, method = RequestMethod.POST)
    public IMVO addContactIM(@RequestBody IMVO im) {
        IMVO i = contactInstantMessageService.createIM(im);
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
    @RequestMapping(value = {"im/update"}, method = RequestMethod.PUT)
    public IMVO updateContactIM(@RequestBody IMVO im) {
        IMVO i = contactInstantMessageService.updateIM(im);
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
    @RequestMapping(value = {"im/delete"}, method = RequestMethod.DELETE)
    public void deleteContactIM(@RequestBody IMVO im) {
        contactInstantMessageService.deleteIM(im);
    }

    /**
     * Find Contact IM with id
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"im/find"}, method = RequestMethod.GET)
    public IMVO findContactIM(@RequestParam("id") Long id) {
        return contactInstantMessageService.findWithId(id);
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
    @RequestMapping(value = {"phone/add"}, method = RequestMethod.POST)
    public PhoneVO addContactPhone(@RequestBody PhoneVO phone) {
        PhoneVO p = contactPhoneService.createPhone(phone);
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
    @RequestMapping(value = {"phone/update"}, method = RequestMethod.PUT)
    public PhoneVO updateContactPhone(@RequestBody PhoneVO phone) {
        PhoneVO p = contactPhoneService.updatePhone(phone);
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
    @RequestMapping(value = {"phone/delete"}, method = RequestMethod.DELETE)
    public void deleteContactPhone(@RequestBody PhoneVO phone) {
        contactPhoneService.deletePhone(phone);
    }

    /**
     * Find Contact Phone with id
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"phone/find"}, method = RequestMethod.GET)
    public PhoneVO findContactPhone(@RequestParam("id") Long id) {
        return contactPhoneService.findWithId(id);
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
    @RequestMapping(value = {"record/add"}, method = RequestMethod.POST)
    public RecordVO addContactRecord(@RequestBody RecordVO record) {
        RecordVO r = contactRecordService.createRecord(record);
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
    @RequestMapping(value = {"record/update"}, method = RequestMethod.PUT)
    public RecordVO updateContactRecord(@RequestBody RecordVO record) {
        RecordVO r = contactRecordService.updateRecord(record);
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
    @RequestMapping(value = {"record/delete"}, method = RequestMethod.DELETE)
    public void deleteContactRecord(@RequestBody RecordVO record) {
        contactRecordService.deleteRecord(record);
    }

    /**
     * Find Contact Record with id
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"record/find"}, method = RequestMethod.GET)
    public RecordVO findContactRecord(@RequestParam("id") Long id) {
        return contactRecordService.findWithId(id);
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
    @RequestMapping(value = {"remind/add"}, method = RequestMethod.POST)
    public RemindDateVO addContactRemindDate(@RequestBody RemindDateVO remindDate) {
        RemindDateVO r = contactRemindDateService.createRemindDate(remindDate);
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
    @RequestMapping(value = {"remind/update"}, method = RequestMethod.PUT)
    public RemindDateVO updateContactRemindDate(@RequestBody RemindDateVO remindDate) {
        RemindDateVO r = contactRemindDateService.updateRemindDate(remindDate);
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
    @RequestMapping(value = {"remind/delete"}, method = RequestMethod.DELETE)
    public void deleteContactRemindDate(@RequestBody RemindDateVO remindDate) {
        contactRemindDateService.deleteRemindDate(remindDate);
    }

    /**
     * Find Contact Remind Date with id
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"remind/find"}, method = RequestMethod.GET)
    public RemindDateVO findContactRemindDate(@RequestParam("id") Long id) {
        return contactRemindDateService.findWithId(id);
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
    @RequestMapping(value = {"resource/add"}, method = RequestMethod.POST)
    public ResourceVO addContactResource(@RequestBody ResourceVO resource) {
        ResourceVO r = contactResourceService.createResource(resource);
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
    @RequestMapping(value = {"resource/update"}, method = RequestMethod.PUT)
    public ResourceVO updateContactResource(@RequestBody ResourceVO resource) {
        ResourceVO r = contactResourceService.updateResource(resource);
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
    @RequestMapping(value = {"resource/delete"}, method = RequestMethod.DELETE)
    public void deleteContactResource(@RequestBody ResourceVO resource) {
        contactResourceService.deleteResource(resource);
    }

    /**
     * Find Contact Resource with id.
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"resource/find"}, method = RequestMethod.GET)
    public ResourceVO findContactResouce(@RequestParam("id") Long id) {
        return contactResourceService.findWithId(id);
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
    @RequestMapping(value = {"setting/add"}, method = RequestMethod.POST)
    public SettingVO addContactSetting(@RequestBody SettingVO setting) {
        SettingVO s = contactSettingService.createSetting(setting);
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
    @RequestMapping(value = {"setting/update"}, method = RequestMethod.PUT)
    public SettingVO updateContactSetting(@RequestBody SettingVO setting) {
        SettingVO s = contactSettingService.updateSetting(setting);
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
    @RequestMapping(value = {"setting/delete"}, method = RequestMethod.DELETE)
    public void deleteContactSetting(@RequestBody SettingVO setting) {
        contactSettingService.deleteSetting(setting);
    }

    /**
     * Find Contact Setting with id
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"setting/find"}, method = RequestMethod.GET)
    public SettingVO findContactSetting(@RequestParam("id") Long id) {
        return contactSettingService.findWithId(id);
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
    @RequestMapping(value = {"common/add"}, method = RequestMethod.POST)
    public InfoCommonVO addContactInfoCommon(@RequestBody InfoCommonVO infoCommon) {
        InfoCommonVO i = contactInfoCommonService.createInfoCommon(infoCommon);
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
    @RequestMapping(value = {"common/update"}, method = RequestMethod.PUT)
    public InfoCommonVO updateContactInfoCommon(@RequestBody InfoCommonVO infoCommon) {
        InfoCommonVO i = contactInfoCommonService.updateInfoCommon(infoCommon);
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
    @RequestMapping(value = {"common/delete"}, method = RequestMethod.DELETE)
    public void deleteContactInfoCommon(@RequestBody InfoCommonVO infoCommon) {
        contactInfoCommonService.deleteInfoCommon(infoCommon);
    }

    /**
     * Find Contact Common with id
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"common/find"}, method = RequestMethod.GET)
    public InfoCommonVO findContactInfoCommon(@RequestParam("id") Long id) {
        return contactInfoCommonService.findWithId(id);
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
    @RequestMapping(value = {"info/type/add"}, method = RequestMethod.POST)
    public InfoTypeVO addContactInfoType(@RequestBody InfoTypeVO infoType) {
        InfoTypeVO it = contactInfoTypeService.createInfoType(infoType);
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
    @RequestMapping(value = {"info/type/delete"}, method = RequestMethod.DELETE)
    public void deleteContactInfoType(@RequestParam Long id) {
        contactInfoTypeService.deleteInfoType(id);
    }

    /**
     * Find InfoType with id
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"info/type/find"}, method = RequestMethod.GET)
    public InfoTypeVO findContactInfoType(@RequestParam("id") Long id) {
        return contactInfoTypeService.findWithId(id);
    }

}
