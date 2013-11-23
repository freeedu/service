package org.personal.mason.feop.oauth.contact.mvc.model;

import org.codehaus.jackson.map.annotate.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName("account")
public class AccountVO {

    private Long id;
    private String accountUid;
    private String oauthUid;
    private ContactVO contact;
    private DeviceVO currentDevice;
    private List<DeviceVO> devices = new ArrayList<>();
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }

    public String getOauthUid() {
        return oauthUid;
    }

    public void setOauthUid(String oauthUid) {
        this.oauthUid = oauthUid;
    }

    public ContactVO getContact() {
        return contact;
    }

    public void setContact(ContactVO contact) {
        this.contact = contact;
    }

    public DeviceVO getCurrentDevice() {
        return currentDevice;
    }

    public void setCurrentDevice(DeviceVO currentDevice) {
        this.currentDevice = currentDevice;
    }

    public List<DeviceVO> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceVO> devices) {
        this.devices = devices;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
