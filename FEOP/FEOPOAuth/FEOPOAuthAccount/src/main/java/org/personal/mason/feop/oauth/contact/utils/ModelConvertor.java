package org.personal.mason.feop.oauth.contact.utils;

import org.personal.mason.feop.oauth.contact.domain.model.AccountBasic;
import org.personal.mason.feop.oauth.contact.domain.model.BindDevice;
import org.personal.mason.feop.oauth.contact.domain.model.Contact;
import org.personal.mason.feop.oauth.contact.domain.model.ContactAddress;
import org.personal.mason.feop.oauth.contact.domain.model.ContactEmail;
import org.personal.mason.feop.oauth.contact.domain.model.ContactInfoCommon;
import org.personal.mason.feop.oauth.contact.domain.model.ContactInfoType;
import org.personal.mason.feop.oauth.contact.domain.model.ContactInstantMessage;
import org.personal.mason.feop.oauth.contact.domain.model.ContactPhone;
import org.personal.mason.feop.oauth.contact.domain.model.ContactRecord;
import org.personal.mason.feop.oauth.contact.domain.model.ContactRemindDate;
import org.personal.mason.feop.oauth.contact.domain.model.ContactResource;
import org.personal.mason.feop.oauth.contact.domain.model.ContactSetting;
import org.personal.mason.feop.oauth.contact.mvc.model.AccountVO;
import org.personal.mason.feop.oauth.contact.mvc.model.AddressVO;
import org.personal.mason.feop.oauth.contact.mvc.model.ContactVO;
import org.personal.mason.feop.oauth.contact.mvc.model.DeviceVO;
import org.personal.mason.feop.oauth.contact.mvc.model.EmailVO;
import org.personal.mason.feop.oauth.contact.mvc.model.IMVO;
import org.personal.mason.feop.oauth.contact.mvc.model.InfoCommonVO;
import org.personal.mason.feop.oauth.contact.mvc.model.InfoTypeVO;
import org.personal.mason.feop.oauth.contact.mvc.model.PhoneVO;
import org.personal.mason.feop.oauth.contact.mvc.model.RecordVO;
import org.personal.mason.feop.oauth.contact.mvc.model.RemindDateVO;
import org.personal.mason.feop.oauth.contact.mvc.model.ResourceVO;
import org.personal.mason.feop.oauth.contact.mvc.model.SettingVO;

public class ModelConvertor {

public static void mergeToSettingModel(SettingVO setingVO, ContactSetting setting) {
	setting.setSettingKey(setingVO.getKey());
	setting.setSettingValue(setingVO.getValue());
	setting.setVersion(setingVO.getVersion());
}

public static SettingVO toSettingView(ContactSetting setting) {
	SettingVO view = new SettingVO();
	if (setting.getContact() != null) {
		view.setContact(setting.getContact().getId());
	}
	view.setId(setting.getId());
	view.setKey(setting.getSettingKey());
	view.setValue(setting.getSettingValue());
	view.setVersion(setting.getVersion());
	return view;
}

public static void mergeToContactModel(ContactVO view, Contact contact) {
	contact.setCompanyName(view.getCompany());
	contact.setDepartmentName(view.getDepartment());
	contact.setJobTitleName(view.getJobTitle());
	contact.setFirstName(view.getFirstName());
	contact.setMiddleName(view.getMiddleName());
	contact.setLastName(view.getLastName());
	contact.setContactName(view.getName());
	contact.setNickName(view.getNickName());
	contact.setNote(view.getNote());
	contact.setLinkToContactId(view.getLinkTo());
}

public static ContactVO toContactView(Contact contact) {
	ContactVO view = new ContactVO();
	view.setId(contact.getId());
	view.setCompany(contact.getCompanyName());
	view.setDepartment(contact.getDepartmentName());
	view.setFirstName(contact.getFirstName());
	view.setJobTitle(contact.getJobTitleName());
	view.setLastName(contact.getLastName());
	view.setLinkTo(contact.getLinkToContactId());
	view.setMiddleName(contact.getMiddleName());
	view.setName(contact.getContactName());
	view.setNickName(contact.getNickName());
	view.setNote(contact.getNote());
	view.setPhotoUrl(contact.getPhotoUrl());
	view.setVersion(contact.getVersion());
	if (contact.getContact() != null) {
		view.setBelongTo(contact.getContact().getId());
	}
	return view;
}

public static void mergeToResourceModel(ResourceVO view, ContactResource resource) {
	resource.setBuildDate(view.getBuildDay());
	resource.setDescription(view.getDescription());
	resource.setResourceName(view.getResourceName());
}

public static ResourceVO toResourceView(ContactResource resource) {
	ResourceVO view = new ResourceVO();
	view.setBuildDay(resource.getBuildDate());
	if (resource.getContact() != null) {
		view.setContact(resource.getContact().getId());
	}
	view.setDescription(resource.getDescription());
	view.setId(resource.getId());
	view.setResourceName(resource.getResourceName());
	view.setVersion(resource.getVersion());
	return view;
}

public static void mergeToRemindDateModel(RemindDateVO view, ContactRemindDate remindDate) {
	remindDate.setLabel(view.getLabel());
	remindDate.setRemind(view.getRemind());
	remindDate.setRemindDate(view.getRemindDate());
}

public static RemindDateVO toRemindDateView(ContactRemindDate remindDate) {
	RemindDateVO view = new RemindDateVO();
	
	if (remindDate.getContact() != null) {
		view.setContact(remindDate.getContact().getId());
	}
	view.setId(remindDate.getId());
	view.setLabel(remindDate.getLabel());
	view.setRemind(remindDate.getRemind());
	view.setRemindDate(remindDate.getRemindDate());
	view.setVersion(remindDate.getVersion());
	return view;
}

public static void mergeToRecordModel(RecordVO view, ContactRecord record) {
	record.setDescription(view.getDescription());
	record.setAccomplishment(view.getAccomplishment());
	record.setEndDate(view.getEndDay());
	record.setStartDate(view.getStartDay());
	record.setType(view.getType());
}

public static RecordVO toRecordView(ContactRecord record) {
	RecordVO view = new RecordVO();
	if (record.getContact() != null) {
		view.setContact(record.getContact().getId());
	}
	view.setId(record.getId());
	view.setAccomplishment(record.getAccomplishment());
	view.setDescription(record.getDescription());
	view.setStartDay(record.getStartDate());
	view.setEndDay(record.getEndDate());
	view.setType(record.getType());
	view.setVersion(record.getVersion());
	return view;
}

public static void mergeToPhoneModel(PhoneVO view, ContactPhone phone) {
	phone.setPhoneLabel(view.getLabel());
	phone.setPhoneNumber(view.getNumber());
	phone.setPriority(view.getPriority());
}

public static PhoneVO toPhoneView(ContactPhone phone) {
	PhoneVO view = new PhoneVO();
	if (phone.getContact() != null) {
		view.setContact(phone.getContact().getId());
	}
	view.setId(phone.getId());
	view.setLabel(phone.getPhoneLabel());
	view.setNumber(phone.getPhoneNumber());
	view.setPriority(phone.getPriority());
	view.setVersion(phone.getVersion());
	return view;
}

public static void mergeToIMModel(IMVO view, ContactInstantMessage im) {
	im.setImLabel(view.getLabel());
	im.setImAddress(view.getImAddress());
	im.setPriority(view.getPriority());
}

public static IMVO toIMView(ContactInstantMessage im) {
	IMVO view = new IMVO();
	if (im.getContact() != null) {
		view.setContact(im.getContact().getId());
	}
	view.setId(im.getId());
	view.setLabel(im.getImLabel());
	view.setImAddress(im.getImAddress());
	view.setPriority(im.getPriority());
	view.setVersion(im.getVersion());
	return view;
}

public static void mergeToInfoTypeModel(InfoTypeVO view, ContactInfoType infoType) {
	infoType.setName(view.getName());
	infoType.setDescription(view.getDescription());
}

public static InfoTypeVO toInfoTypeView(ContactInfoType infoType) {
	InfoTypeVO view = new InfoTypeVO();
	view.setId(infoType.getId());
	view.setName(infoType.getName());
	view.setDescription(infoType.getDescription());
	view.setVersion(infoType.getVersion());
	return view;
}

public static void mergeToInfoCommonModel(InfoCommonVO view, ContactInfoCommon infoCommon) {
	infoCommon.setLabel(view.getLabel());
	infoCommon.setValue(view.getValue());
	infoCommon.setPriority(view.getPriority());
}

public static InfoCommonVO toInfoCommonView(ContactInfoCommon infoCommon) {
	InfoCommonVO view = new InfoCommonVO();
	if (infoCommon.getContact() != null) {
		view.setContact(infoCommon.getContact().getId());
	}
	view.setId(infoCommon.getId());
	view.setInfoType(infoCommon.getInfoType().getId());
	view.setLabel(infoCommon.getLabel());
	view.setPriority(infoCommon.getPriority());
	view.setValue(infoCommon.getValue());
	view.setVersion(infoCommon.getVersion());
	return view;
}

public static void mergeToEmailModel(EmailVO view, ContactEmail email) {
	email.setEmailLabel(view.getLabel());
	email.setEmailAddress(view.getEmail());
	email.setPriority(view.getPriority());
}

public static EmailVO toEmailView(ContactEmail email) {
	EmailVO view = new EmailVO();
	if (email.getContact() != null) {
		view.setContact(email.getContact().getId());
	}
	view.setId(email.getId());
	view.setLabel(email.getEmailLabel());
	view.setEmail(email.getEmailAddress());
	view.setPriority(email.getPriority());
	view.setVersion(email.getVersion());
	return view;
}

public static void mergeToAddressModel(AddressVO view, ContactAddress address) {
	address.setAddressCountry(view.getCountry());
	address.setLabel(view.getLabel());
	address.setPostcode(view.getPostcode());
	address.setAddressLine1(view.getLine1());
	address.setAddressLine2(view.getLine2());
	address.setAddressLine3(view.getLine3());
	address.setAddressLine4(view.getLine4());
	address.setAddressLine5(view.getLine5());
	address.setAddressLine6(view.getLine6());
	address.setAddressLine7(view.getLine7());
	address.setAddressLine8(view.getLine8());
}

public static AddressVO toAddressView(ContactAddress address) {
	AddressVO view = new AddressVO();
	if (address.getContact() != null) {
		view.setContact(address.getContact().getId());
	}
	view.setId(address.getId());
	view.setCountry(address.getAddressCountry());
	view.setPostcode(address.getPostcode());
	view.setLabel(address.getLabel());
	view.setLine1(address.getAddressLine1());
	view.setLine2(address.getAddressLine3());
	view.setLine3(address.getAddressLine3());
	view.setLine4(address.getAddressLine4());
	view.setLine5(address.getAddressLine5());
	view.setLine6(address.getAddressLine6());
	view.setLine7(address.getAddressLine7());
	view.setLine8(address.getAddressLine8());
	view.setVersion(address.getVersion());
	return view;
}

public static void mergeToBindDeviceModel(DeviceVO view, BindDevice device) {
	device.setDeviceIdentifier(view.getIdentifier());
	device.setDeviceType(view.getType());
	device.setMacAddress(view.getMachineAddress());
	device.setDeviceName(view.getName());
	device.setPhoneNumber(view.getPhoneNumber());
	device.setDeviceType(view.getType());
}

public static DeviceVO toBindDeviceView(BindDevice device) {
	DeviceVO view = new DeviceVO();
	
	if (device.getAccountBasic() != null) {
		view.setAccountId(device.getAccountBasic().getId());
	}
	view.setId(device.getId());
	view.setIdentifier(device.getDeviceIdentifier());
	view.setMachineAddress(device.getMacAddress());
	view.setName(device.getDeviceName());
	view.setPhoneNumber(device.getPhoneNumber());
	view.setType(device.getDeviceType());
	
	view.setVersion(device.getVersion());
	return view;
}

public static void mergeToAccountBasicModel(AccountVO view, AccountBasic basic) {
	basic.setAccountUid(view.getAccountUid());
	basic.setOauthUid(view.getOauthUid());
}

public static AccountVO toAccountBasicView(AccountBasic basic) {
	AccountVO view = new AccountVO();
	view.setId(basic.getId());
	view.setAccountUid(basic.getAccountUid());
	view.setOauthUid(basic.getOauthUid());
	view.setVersion(basic.getVersion());
	return view;
}

}
