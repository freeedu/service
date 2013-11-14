package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.Device;

public interface BindDeviceService {

	Device createOrUpdateDevice(Device device);

	void unbindAccount(Device device);

}
