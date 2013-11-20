package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.BindDevice;
import org.personal.mason.feop.oauth.contact.domain.repository.AccountBasicRepository;
import org.personal.mason.feop.oauth.contact.domain.repository.BindDeviceRepository;
import org.personal.mason.feop.oauth.contact.mvc.model.DeviceVO;
import org.personal.mason.feop.oauth.contact.spi.BindDeviceService;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BindDeviceServiceImpl implements BindDeviceService {

private BindDeviceRepository bindDeviceRepository;
private AccountBasicRepository accountBasicRepository;

@Autowired
public void setBindDeviceRepository(BindDeviceRepository bindDeviceRepository) {
	this.bindDeviceRepository = bindDeviceRepository;
}

@Autowired
public void setAccountBasicRepository(AccountBasicRepository accountBasicRepository) {
	this.accountBasicRepository = accountBasicRepository;
}

@Override
public DeviceVO toViewObject(BindDevice model) {
	DeviceVO bindDeviceView = ModelConvertor.toBindDeviceView(model);
	return bindDeviceView;
}

@Override
public BindDevice toModel(DeviceVO view) {
	BindDevice device;
	if (view.getId() == null) {
		device = new BindDevice();
		if (view.getAccountId() != null) {
			device.setAccountBasic(accountBasicRepository.findOne(view.getAccountId()));
		}
	} else {
		device = bindDeviceRepository.findOne(view.getId());
	}
	ModelConvertor.mergeToBindDeviceModel(view, device);
	
	return device;
}

@Override
public DeviceVO createOrUpdateDevice(DeviceVO view) {
	BindDevice model = toModel(view);
	BindDevice updated = bindDeviceRepository.saveAndFlush(model);
	return toViewObject(updated);
}

@Override
public void unbindAccount(DeviceVO view) {
	BindDevice model = toModel(view);
	model.setAccountBasic(null);
	bindDeviceRepository.saveAndFlush(model);
}

}
