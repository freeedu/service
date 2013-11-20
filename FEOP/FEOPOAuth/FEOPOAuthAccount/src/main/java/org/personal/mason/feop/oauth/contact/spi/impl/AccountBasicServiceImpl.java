package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.AccountBasic;
import org.personal.mason.feop.oauth.contact.domain.model.BindDevice;
import org.personal.mason.feop.oauth.contact.domain.model.Contact;
import org.personal.mason.feop.oauth.contact.domain.repository.AccountBasicRepository;
import org.personal.mason.feop.oauth.contact.domain.repository.BindDeviceRepository;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactRepository;
import org.personal.mason.feop.oauth.contact.mvc.model.AccountVO;
import org.personal.mason.feop.oauth.contact.mvc.model.ContactVO;
import org.personal.mason.feop.oauth.contact.mvc.model.DeviceVO;
import org.personal.mason.feop.oauth.contact.spi.AccountBasicService;
import org.personal.mason.feop.oauth.contact.utils.AccountUidGenerator;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBasicServiceImpl implements AccountBasicService {

private AccountBasicRepository accountBasicRepository;
private BindDeviceRepository bindDeviceRepository;
private ContactRepository contactRepository;

@Autowired
public void setAccountBasicRepository(AccountBasicRepository accountBasicRepository) {
	this.accountBasicRepository = accountBasicRepository;
}

@Autowired
public void setBindDeviceRepository(BindDeviceRepository bindDeviceRepository) {
	this.bindDeviceRepository = bindDeviceRepository;
}

@Autowired
public void setContactRepository(ContactRepository contactRepository) {
	this.contactRepository = contactRepository;
}

@Override
public AccountVO toViewObject(AccountBasic model) {
	AccountVO accountBasicView = ModelConvertor.toAccountBasicView(model);
	
	for (BindDevice device : model.getBindDevices()) {
		DeviceVO deviceView = ModelConvertor.toBindDeviceView(device);
		accountBasicView.getDevices().add(deviceView);
	}
	
	if (model.getContact() != null) {
		accountBasicView.setContact(ModelConvertor.toContactView(model.getContact()));
	}
	
	return accountBasicView;
}

@Override
public AccountBasic toModel(AccountVO view) {
	AccountBasic accountBasic;
	if (view.getId() == null) {
		accountBasic = new AccountBasic();
	} else {
		accountBasic = accountBasicRepository.findOne(view.getId());
	}
	ModelConvertor.mergeToAccountBasicModel(view, accountBasic);
	
	for (DeviceVO deviceVO : view.getDevices()) {
		BindDevice device;
		if (deviceVO.getId() == null) {
			device = new BindDevice();
		} else {
			device = bindDeviceRepository.findOne(deviceVO.getId());
		}
		device.setAccountBasic(accountBasic);
		accountBasic.getBindDevices().add(device);
	}
	
	if (view.getContact() != null) {
		accountBasic.setContact(contactRepository.findOne(view.getContact().getId()));
	}
	
	return accountBasic;
}

@Override
public boolean isExistAccountWithOauthUid(String oauthUid) {
	return accountBasicRepository.findByOauthUid(oauthUid).size() > 0;
}

@Override
public AccountVO registAccount(DeviceVO deviceView, String oauthUid) {
	AccountBasic accountBasic = new AccountBasic();
	accountBasic.setOauthUid(oauthUid);
	accountBasic.setAccountUid(AccountUidGenerator.generateUid());
	Contact contact = new Contact();
	Contact saved = contactRepository.save(contact);
	accountBasic.setContact(saved);
	AccountBasic savedAccountBasic = accountBasicRepository.save(accountBasic);
	BindDevice device = new BindDevice();
	ModelConvertor.mergeToBindDeviceModel(deviceView, device);
	device.setAccountBasic(savedAccountBasic);
	BindDevice savedDevice = bindDeviceRepository.saveAndFlush(device);
	AccountVO viewObject = toViewObject(accountBasicRepository.findOne(savedAccountBasic.getId()));
	viewObject.setCurrentDevice(ModelConvertor.toBindDeviceView(savedDevice));
	return viewObject;
}

@Override
public AccountVO findAccountWithOauthUidAndId(String oauthUid, Long accountId) {
	AccountBasic account = accountBasicRepository.findOne(accountId);
	if (account != null && account.getOauthUid().equals(oauthUid)) {
		return toViewObject(account);
	}
	return null;
}

@Override
public AccountVO findAccountWithId(Long accountId) {
	AccountBasic account = accountBasicRepository.findOne(accountId);
	if (account != null) {
		return toViewObject(account);
	}
	return null;
}

@Override
public ContactVO findMyContact(Long accountId) {
	AccountBasic account = accountBasicRepository.findOne(accountId);
	Contact contact = account.getContact();
	return ModelConvertor.toContactView(contact);
}

}
