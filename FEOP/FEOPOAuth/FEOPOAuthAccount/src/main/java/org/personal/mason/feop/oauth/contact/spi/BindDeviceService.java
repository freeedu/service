package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.BindDevice;
import org.personal.mason.feop.oauth.contact.mvc.model.DeviceVO;

public interface BindDeviceService extends BaseService<DeviceVO, BindDevice> {

DeviceVO createOrUpdateDevice(DeviceVO view);

void unbindAccount(DeviceVO view);

}
